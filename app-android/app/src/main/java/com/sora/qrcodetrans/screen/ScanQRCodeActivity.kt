package com.sora.qrcodetrans.screen

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Size
import android.widget.Toast
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner
import com.google.common.util.concurrent.ListenableFuture
import com.google.mlkit.vision.barcode.BarcodeScannerOptions
import com.google.mlkit.vision.barcode.BarcodeScanning
import com.google.mlkit.vision.barcode.common.Barcode
import com.google.mlkit.vision.common.InputImage
import com.sora.qrcodetrans.R
import dagger.hilt.android.AndroidEntryPoint
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

@AndroidEntryPoint
class ScanQRCodeActivity : AppCompatActivity() {

    /**
     * CreatedBy: dbhuan 21/12/2021
     */
    private lateinit var cameraProviderFuture: ListenableFuture<ProcessCameraProvider>
    private lateinit var cameraExecutor: ExecutorService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scan_qrcode)

        showPreviewCamera();
    }

    private fun showPreviewCamera() {
        cameraExecutor = Executors.newSingleThreadExecutor()
        cameraProviderFuture = ProcessCameraProvider.getInstance(this)

        cameraProviderFuture.addListener(Runnable {
            val cameraProvider = cameraProviderFuture.get()
            bindPreview(cameraProvider)
        }, ContextCompat.getMainExecutor(this))
    }

    /**
     * Hiển thị camera
     * CreatedBy: dbhuan 21/12/2021
     */
    @SuppressLint("UnsafeExpreimentalUsageError")
    private fun bindPreview(cameraProvider: ProcessCameraProvider?) {
        val preview: Preview = Preview.Builder()
            .build()

        val cameraSelector: CameraSelector = CameraSelector.Builder()
            .requireLensFacing(CameraSelector.LENS_FACING_BACK)
            .build()
        val previewView: PreviewView = findViewById(R.id.previewView)
        preview.setSurfaceProvider(previewView.surfaceProvider)

        val imageAnalysis = ImageAnalysis.Builder()
            .setTargetResolution(Size(1280, 720))
            .build()

        imageAnalysis.setAnalyzer(cameraExecutor, ImageAnalysis.Analyzer { imageProxy ->
            scanBarcode(imageProxy, cameraProvider)
        })

        cameraProvider?.bindToLifecycle(
            this as LifecycleOwner,
            cameraSelector,
            imageAnalysis,
            preview
        )
    }

    @SuppressLint("UnsafeOptInUsageError")
    private fun scanBarcode(imageProxy: ImageProxy, cameraProvider: ProcessCameraProvider?) {
        val options = BarcodeScannerOptions.Builder()
            .setBarcodeFormats(
                Barcode.FORMAT_QR_CODE)
            .build()
        imageProxy.image?.let { img ->
            val inputImage = InputImage.fromMediaImage(img, imageProxy.imageInfo.rotationDegrees)
            val scanner = BarcodeScanning.getClient(options)
            scanner.process(inputImage)
                .addOnSuccessListener { barcodes ->
                    readBarcodeData(barcodes)
                }
                .addOnFailureListener {
                    Toast.makeText(this, "QRCode không hợp lệ", Toast.LENGTH_SHORT).show()
                }
                .addOnCompleteListener {
                    imageProxy.close()
                }
        }
    }

    private fun readBarcodeData(barcodes: List<Barcode>) {
        var data: String = ""
        for(barcode in barcodes){
            when(barcode.valueType){
                Barcode.TYPE_TEXT -> {
                    data = barcode.displayValue
                    if(!data.isEmpty()){
                        cameraExecutor.shutdown()

                        val intent = Intent().apply {
                            putExtra(MainActivity.SCANQRCODEDATA, data)
                        }

                        setResult(Activity.RESULT_OK, intent)

                        finish()
                        break
                    }
                }
            }
        }
    }
}