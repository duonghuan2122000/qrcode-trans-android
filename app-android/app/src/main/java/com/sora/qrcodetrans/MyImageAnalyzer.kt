package com.sora.qrcodetrans

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import com.google.mlkit.vision.barcode.BarcodeScannerOptions
import com.google.mlkit.vision.barcode.BarcodeScanning
import com.google.mlkit.vision.barcode.common.Barcode
import com.google.mlkit.vision.common.InputImage

class MyImageAnalyzer(val context: Context): ImageAnalysis.Analyzer {


    override fun analyze(image: ImageProxy) {
        scanBarcode(image)
    }

    /**
     * quét qrcode
     * CreatedBy: dbhuan 21/12/2021
     */
    @SuppressLint("UnsafeOptInUsageError")
    private fun scanBarcode(imageProxy: ImageProxy) {
        val options = BarcodeScannerOptions.Builder()
            .setBarcodeFormats(
                Barcode.FORMAT_QR_CODE)
            .build()
        imageProxy.image?.let { img ->
            val inputImage = InputImage.fromMediaImage(img, imageProxy.imageInfo.rotationDegrees)
            val scanner = BarcodeScanning.getClient(options)
            scanner.process(inputImage)
                .addOnSuccessListener { barcodes ->
                    Log.d("MyImageAnalyzer-success", "success")
                    readBarcodeData(barcodes)
                }
                .addOnFailureListener {
                    Toast.makeText(context, "QRCode không hợp lệ", Toast.LENGTH_SHORT).show()
                }
                .addOnCompleteListener {
                    Log.d("MyImageAnalyzer", "finish")
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
                    break
                }
            }
        }
//        val intent = Intent(context, ResultQRCodeActivity::class.java).apply {
//            putExtra("data", data)
//        }
//        context.startActivity(intent)
    }
}