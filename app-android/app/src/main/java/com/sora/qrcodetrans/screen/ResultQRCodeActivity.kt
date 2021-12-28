package com.sora.qrcodetrans.screen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.sora.qrcodetrans.R
import com.sora.qrcodetrans.data.Status
import com.sora.qrcodetrans.viewModel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ResultQRCodeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result_qrcode)

        val data = intent.getStringExtra(MainActivity.SCANQRCODEDATA)
        Toast.makeText(this, data, Toast.LENGTH_SHORT).show()

        val viewModel: MainViewModel by viewModels()

        findViewById<Button>(R.id.btnTrans).setOnClickListener {
            viewModel.getUsers().observe(this, Observer {
                it?.let { resource ->
                    when(resource.status){
                        Status.SUCCESS -> {
                            val users = resource.data
                            if (users != null) {
                                for (user in users){
                                    Log.d("user", user.id.toString() + " " + user.name.toString() + " " + user.username)
                                }
                            }
                        }
                    }
                }
            })
        }
    }
}