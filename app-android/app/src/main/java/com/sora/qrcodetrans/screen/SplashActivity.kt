package com.sora.qrcodetrans.screen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.sora.qrcodetrans.R
import com.sora.qrcodetrans.data.Status
import com.sora.qrcodetrans.viewModel.auth.GetTokenViewModel
import com.sora.qrcodetrans.viewModel.qrcode.QRCodeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {

    private val getTokenViewModel: GetTokenViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        getTokenViewModel.initOnStart().observe(this, Observer { resource ->
            when(resource.status){
                Status.LOADING -> {}
                Status.SUCCESS -> {
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }
                Status.ERROR -> {
                    MaterialAlertDialogBuilder(this)
                        .setTitle("Có lỗi xảy ra")
                        .setPositiveButton("Thử lại") { dialog, which ->
                            dialog.cancel()
                            finish()
                        }
                        .show()
                }

            }
        })
    }
}