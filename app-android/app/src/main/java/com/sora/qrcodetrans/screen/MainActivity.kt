package com.sora.qrcodetrans.screen

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.sora.qrcodetrans.R
import com.sora.qrcodetrans.data.qrcode.DataQRCode
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    companion object {
        /**
         * Key của obj data QRCode
         */
        val ScanQRCodeData = "SCANQRCODEDATA"

        /**
         * Key nhận biết thông báo lỗi
         */
        val ScanQRCodeDataErr = "SCANQRCODEDATAERR"

        /**
         * Key Msg lỗi qrcode
         */
        val ScanQRCodeDataErrMsg = "SCANQRCODEDATAERRMESS"
    }

    /**
     * khởi tạo acitivity đợi kết quả quét mã QRCode
     * CreatedBy: dbhuan 30/12/2021
     */
    var scanQRCodeActivity =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            // nếu có kết quả trả về
            if (result.resultCode == Activity.RESULT_OK) {
                // lấy data từ intent
                val intentResult = result.data

                // kiểm tra có data qrcode có lỗi
                val err = intentResult?.getBooleanExtra(ScanQRCodeDataErr, false)
                if (err == null || err == false) {
                    // qrcode hợp lệ -> tạo intent và chuyển hướng sang màn thông tin QRCode
                    val data = intentResult?.getBundleExtra(ScanQRCodeData)?.getParcelable<DataQRCode>(
                        ScanQRCodeData
                    )
                    val bundle = Bundle().apply {
                        putParcelable(ScanQRCodeData, data)
                    }
                    val intent = Intent(this, ResultQRCodeActivity::class.java).apply {
                        putExtra(ScanQRCodeData, bundle)
                    }
                    startActivity(intent)
                } else {
                    // qrcode không hợp lệ -> hiển thị thông báo lỗi
                    val message = intentResult?.getStringExtra(ScanQRCodeDataErrMsg)
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