package com.sora.qrcodetrans.screen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sora.qrcodetrans.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ResultTransActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result_trans)
    }
}