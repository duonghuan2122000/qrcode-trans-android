package com.sora.qrcodetrans.screen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.sora.qrcodetrans.R
import com.sora.qrcodetrans.const.TransactionQRCodeErrorInfo
import com.sora.qrcodetrans.data.qrcode.DataQRCode
import com.sora.qrcodetrans.data.transaction.TransactionQRCodeReq
import dagger.hilt.android.AndroidEntryPoint
import java.text.DecimalFormat

@AndroidEntryPoint
class ResultQRCodeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result_qrcode)

        // view
        val tvMerchantName = findViewById<TextView>(R.id.merchantName)
        val tvTerminalName = findViewById<TextView>(R.id.terminalName)
        val tvTxnId = findViewById<TextView>(R.id.txnId)
        val tvAmount = findViewById<TextView>(R.id.amount)
        val btnPayment = findViewById<Button>(R.id.btnPayment)

        // thông tin QRCode
        var mDataQRCode: DataQRCode? = null

        // bind thông tin QRCode lên view
        intent.getBundleExtra(MainActivity.ScanQRCodeData)
            ?.getParcelable<DataQRCode>(MainActivity.ScanQRCodeData)?.let { dataQRCode ->
            mDataQRCode = dataQRCode
            tvMerchantName.text = dataQRCode.merchantName
            tvTerminalName.text = dataQRCode.terminalId
            tvTxnId.text = dataQRCode.txnId
            tvAmount.text = DecimalFormat("#,###").format(dataQRCode.amount.toLong()) + " VND"
        }

        // sự kiện click btn thanh toán QRCode
        btnPayment.setOnClickListener {
            val dataQR = mDataQRCode!!

            // tạo req thanh toán QR -> chuyển sang màn hình nhận kết quả thanh toán
            val input = TransactionQRCodeReq(
                code = TransactionQRCodeErrorInfo.Code.Success,
                message = "Đặt hàng thành công",
                msgType = "1",
                txnId = dataQR.txnId,
                amount = dataQR.amount,
                merchantCode = dataQR.merchantCode,
                terminalId = dataQR.terminalId
            )
            val bundle = Bundle().apply {
                putParcelable(ResultPaymentActivity.QRCodeDataObj, input)
            }
            val intent = Intent(this, ResultPaymentActivity::class.java).apply {
                putExtra(ResultPaymentActivity.QRCodeDataObj, bundle)
            }
            startActivity(intent)
        }
    }
}