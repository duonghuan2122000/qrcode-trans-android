package com.sora.qrcodetrans.screen

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.content.ContextCompat
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.common.util.concurrent.ListenableFuture
import com.sora.qrcodetrans.R
import com.sora.qrcodetrans.data.qrcode.DataQRCode
import dagger.hilt.android.AndroidEntryPoint
import java.util.concurrent.ExecutorService

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    companion object {
        // key cho data qrcode
        val SCANQRCODEDATA = "SCANQRCODEDATA"
        val SCANQRCODEDATAERR = "SCANQRCODEDATAERR"
        val SCANQRCODEDATAERRMESS = "SCANQRCODEDATAERRMESS"
    }

    /**
     * khởi tạo acitivity đợi kết quả quét mã QRCode
     * CreatedBy: dbhuan 30/12/2021
     */
    var scanQRCodeActivity =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val intentResult = result.data
                val err = intentResult?.getBooleanExtra(SCANQRCODEDATAERR, false)
                if (err == null || err == false) {
                    val data = intentResult?.getBundleExtra(SCANQRCODEDATA)?.getParcelable<DataQRCode>(
                        SCANQRCODEDATA
                    )
                    val bundle = Bundle().apply {
                        putParcelable(SCANQRCODEDATA, data)
                    }
                    val intent = Intent(this, ResultQRCodeActivity::class.java).apply {
                        putExtra(SCANQRCODEDATA, bundle)
                    }
                    startActivity(intent)
                } else {
                    val message = intentResult?.getStringExtra(SCANQRCODEDATAERRMESS)
                    MaterialAlertDialogBuilder(this)
                        .setTitle(message)
                        .setNegativeButton("Đóng") { dialog, which ->
                            dialog.cancel()
                        }
                        .setPositiveButton("Thử lại") { dialog, which ->
                            dialog.cancel()
                            startActivityScanQRCodeForResult()
                        }
                        .show()
                }
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        checkCameraPermission()

        handleTopAppBar()

        /**
         * Sự kiện click button quét qrcode
         * 1. Chuyển hướng sang màn quét qrcode
         * 2. Đợi kết quả quét qrcode từ activity trên
         */
        findViewById<Button>(R.id.btnScanQRCode).setOnClickListener {
            startActivityScanQRCodeForResult()
        }
    }

    /**
     * Hàm chuyển hướng sang màn hình quét qrcode
     * CreatedBy: dbhuan 30/12/2021
     */
    private fun startActivityScanQRCodeForResult() {
        val intent = Intent(this, ScanQRCodeActivity::class.java)
        scanQRCodeActivity.launch(intent)
    }

    /**
     * Hàm xử lý top app bar
     * CreatedBy: dbhuan 30/12/2021
     */
    private fun handleTopAppBar() {
        val topAppBar = findViewById<MaterialToolbar>(R.id.topAppBar)

        topAppBar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.menu_settings -> {
                    val settingIntent = Intent(this, SettingActivity::class.java)
                    startActivity(settingIntent)
                    true
                }
                else -> false
            }
        }
    }

    /**
     * Hàm kiểm tra quyền truy cập camera
     * CreatedBy: dbhuan 30/12/2021
     */
    private fun checkCameraPermission() {
        val requestPermissionLauncher =
            registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
                if (!isGranted) {
                    // yêu cầu cấp quyền lại
                    checkCameraPermission()
                }
            }

        when {
            ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_GRANTED -> {
                // quyền đã được cấp
            }
            else -> {
                // chưa được cấp quyền, khởi chạy launcher yêu cầu quyền
                requestPermissionLauncher.launch(
                    Manifest.permission.CAMERA
                )
            }
        }
    }
}