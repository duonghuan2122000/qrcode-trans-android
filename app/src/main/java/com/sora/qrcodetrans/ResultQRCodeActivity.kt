package com.sora.qrcodetrans

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.sora.qrcodetrans.data.ApiHelper
import com.sora.qrcodetrans.data.RetrofitBuilder
import com.sora.qrcodetrans.data.Status
import com.sora.qrcodetrans.viewModel.MainViewModel
import com.sora.qrcodetrans.viewModel.MainViewModelFactory

class ResultQRCodeActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result_qrcode)

        val data = intent.getStringExtra(MainActivity.SCANQRCODEDATA)
        Toast.makeText(this, data, Toast.LENGTH_SHORT).show()

        setUpViewModel()

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

    private fun setUpViewModel() {
        viewModel = ViewModelProvider(this, MainViewModelFactory(ApiHelper(RetrofitBuilder.apiService)))
            .get(MainViewModel::class.java)
    }
}