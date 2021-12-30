package com.sora.qrcodetrans.data.qrcode

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * Thông tin qrcode
 * CreatedBy: dbhuan 30/12/2021
 */
@Parcelize
data class DataQRCode (
    /**
     * mã đơn hàng
     */
    val txnId: String,
    /**
     * Số điện thoại
     */
    val mobile: String? = "",
    /**
     * Số tài khoản
     */
    val accountNo: String? = "",
    /**
     * Số tiền
     */
    val amount: String,
    /**
     * Mã quy định của ngân hàng
     */
    val masterMerCode: String? = "",
    /**
     * Mã merchant ngân hàng cung cấp
     */
    val merchantCode: String,
    /**
     * Tên merchant ngân hàng cung cấp
     */
    val merchantName: String?,
    /**
     * Mã điểm thu
     */
    val terminalId: String,
    /**
     * Tên điểm thu
     */
    val terminalName: String?,
    /**
     * Tên
     */
    val name: String = "",
    /**
     * Số điện thoại
     */
    val phone: String = "",
    /**
     * Mã tỉnh
     */
    val provinceId: String = "",
    /**
     * mã huyện
     */
    val districtId: String = "",
    /**
     * địa chỉ
     */
    val address: String = "",
    /**
     * email
     */
    val email: String = "",
    /**
     * thông tin bổ sung
     */
    val addData: List<AddDatum>? = mutableListOf()
): Parcelable

@Parcelize
data class AddDatum (
    val productId: String,
    val amount: String,
    val tipAndFee: String,
    val ccy: String,
    val qty: String,
    val note: String
): Parcelable

