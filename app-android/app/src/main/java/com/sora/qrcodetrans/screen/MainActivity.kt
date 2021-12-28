package com.sora.qrcodetrans.screen

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.widget.Button
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.content.ContextCompat
import com.google.common.util.concurrent.ListenableFuture
import com.sora.qrcodetrans.R
import dagger.hilt.android.AndroidEntryPoint
import java.util.concurrent.ExecutorService

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    companion object {
        val SCANQRCODE = 1
        val SCANQRCODEDATA = "SCANQRCODEDATA"
    }

    /**
     * CreatedBy: dbhuan 21/12/2021
     */
    private lateinit var cameraProviderFuture: ListenableFuture<ProcessCameraProvider>
    private lateinit var cameraExecutor: ExecutorService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        checkCameraPermission()

        findViewById<Button>(R.id.btnScanQRCode).setOnClickListener {
            val intent = Intent(this, ScanQRCodeActivity::class.java)
            startActivityForResult(intent, SCANQRCODE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == SCANQRCODE){
            if(resultCode == Activity.RESULT_OK){
                val data = data?.getStringExtra(SCANQRCODEDATA)
                Log.d("MainActivity", data!!)
                val intent = Intent(this, ResultQRCodeActivity::class.java).apply {
                    putExtra(SCANQRCODEDATA, data)
                }
                startActivity(intent)
            }
        }
    }

    /**
     * hàm kiểm tra quyền truy cập máy ảnh
     * CreatedBy: dbhuan 21/12/2021
     */
    private fun checkCameraPermission(){
        if(ContextCompat.checkSelfPermission(
                this,
                android.Manifest.permission.CAMERA,
        ) != PackageManager.PERMISSION_GRANTED){
            Intent().also {
                it.action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
                it.data = Uri.fromParts("package", packageName, null)
                startActivity(it)
                finish()
            }
        }
    }
}