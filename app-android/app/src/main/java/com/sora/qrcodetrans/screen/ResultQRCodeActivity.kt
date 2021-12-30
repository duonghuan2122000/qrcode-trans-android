package com.sora.qrcodetrans.screen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.sora.qrcodetrans.R
import com.sora.qrcodetrans.data.qrcode.DataQRCode
import dagger.hilt.android.AndroidEntryPoint
import java.text.DecimalFormat

@AndroidEntryPoint
class ResultQRCodeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result_qrcode)

        val tvMerchantName = findViewById<TextView>(R.id.merchantName)
        val tvTerminalName = findViewById<TextView>(R.id.terminalName)
        val tvTxnId = findViewById<TextView>(R.id.txnId)
        val tvAmount = findViewById<TextView>(R.id.amount)
        val btnPayment = findViewById<Button>(R.id.btnPayment)

        intent.getBundleExtra(MainActivity.SCANQRCODEDATA)?.getParcelable<DataQRCode>(MainActivity.SCANQRCODEDATA)?.let { dataQRCode ->
            tvMerchantName.text = dataQRCode.merchantName
            tvTerminalName.text = dataQRCode.terminalName
            tvTxnId.text = dataQRCode.txnId
            tvAmount.text = DecimalFormat("#,###").format(dataQRCode.amount.toLong()) + " VND"
        }

        btnPayment.setOnClickListener {
            val intent = Intent(this, ResultPaymentActivity::class.java)
            startActivity(intent)
        }
    }
}