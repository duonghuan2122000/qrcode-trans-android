package com.sora.qrcodetrans.data.transaction

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.text.SimpleDateFormat
import java.util.*

@Parcelize
data class TransactionQRCodeReq(
    /**
     * Mã lỗi trả về
     */
    val code: String,

    /**
     * Mô tả mã lỗi
     */
    val message: String,

    /**
     * MsgType
     */
    val msgType: String? = "1",

    /**
     * Mã đơn hàng
     */
    val txnId: String,

    /**
     * Số trace giao dịch
     */
    val qrTrace: String? = UUID.randomUUID().toString(),

    /**
     * Mã ngân hàng thanh toán
     */
    val bankCode: String? = "JETPAYTEST",

    /**
     * Số điện thoại
     */
    val mobile: String? = "",

    /**
     * Số tài khoản
     */
    val accountNo: String? = "",

    /**
     * số tiền thanh toán
     */
    val amount: String,

    /**
     * Ngày thanh toán, định dạng: yyyyMMddHHmm
     */
    val payDate: String? = SimpleDateFormat("yyyyMMddHHmm").format(Date()),

    /**
     * MasterMerchantCode
     */
    val masterMerCode: String? = "9704136",

    /**
     * MerchantCode
     */
    val merchantCode: String,

    /**
     * mã điểm thu
     */
    val terminalId: String,

    /**
     * Tên
     */
    val name: String? = "",

    /**
     * Số điện thoại
     */
    val phone: String? = "",

    /**
     * Mã tỉnh
     */
    val province_id: String? = "",

    /**
     * Mã huyện
     */
    val district_id: String? = "",

    /**
     * Địa chỉ
     */
    val address: String? = "",

    /**
     * Email
     */
    val email: String? = ""
): Parcelable