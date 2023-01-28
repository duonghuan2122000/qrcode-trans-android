package com.sora.qrcodetrans.screen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.viewModels
import com.google.android.material.card.MaterialCardView
import com.sora.qrcodetrans.R
import com.sora.qrcodetrans.data.Status
import com.sora.qrcodetrans.data.transaction.TransactionQRCodeReq
import com.sora.qrcodetrans.viewModel.auth.GetTokenViewModel
import com.sora.qrcodetrans.viewModel.transaction.TransactionQRCodeViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.text.DecimalFormat

@AndroidEntryPoint
class ResultPaymentActivity : AppCompatActivity() {

    companion object {
        const val QRCodeDataObj = "QRCodeDataObj"
    }

    private val transactionQRCodeViewModel: TransactionQRCodeViewModel by viewModels()
    private val getTokenViewModel: GetTokenViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result_payment)

        // data req thanh toán QRCode
        val input = intent.getBundleExtra(QRCodeDataObj)?.getParcelable<TransactionQRCodeReq>(
            QRCodeDataObj
        )!!

        // config
        val config = getTokenViewModel.getConfig()

        // token
        val token = getTokenViewModel.getToken()

        val layoutLoading = findViewById<LinearLayout>(R.id.layout_loading)
        val layoutResult = findViewById<LinearLayout>(R.id.layout_result)
        val txtMsg = findViewById<TextView>(R.id.txtMsg)
        val iconResult = findViewById<ImageView>(R.id.icon_result)
        val txtAmount = findViewById<TextView>(R.id.txtAmount)
        val merchantName = findViewById<TextView>(R.id.merchantName)
        val terminalName = findViewById<TextView>(R.id.terminalName)
        val layoutAddInfo = findViewById<MaterialCardView>(R.id.layout_add_info)
        val btnGoHome = findViewById<Button>(R.id.btnGoHome)

        btnGoHome.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        // thực hiện gọi api thanh toán
        transactionQRCodeViewModel.createTransaction(config.urlTransactionQRCode, token, input)
            .observe(this, { resource ->
                when (resource.status) {
                    Status.LOADING -> {
                        layoutLoading.visibility = View.VISIBLE
                        layoutResult.visibility = View.GONE
                    }
                    Status.SUCCESS -> {
                        layoutLoading.visibility = View.GONE
                        layoutResult.visibility = View.VISIBLE
                        txtMsg.text = resource.data
                        iconResult.setImageResource(R.drawable.ic_tickmark)
                        txtAmount.text = DecimalFormat("#,###").format(input.amount.toLong()) + " VND"
                        merchantName.text = input.merchantCode
                        terminalName.text = input.terminalId
                    }
                    Status.ERROR -> {
                        layoutLoading.visibility = View.GONE
                        layoutResult.visibility = View.VISIBLE
                        txtMsg.text = resource.message
                        iconResult.setImageResource(R.drawable.ic_error)
                        txtAmount.visibility = View.GONE
                        layoutAddInfo.visibility = View.GONE
                    }
                }
            })
    }
}