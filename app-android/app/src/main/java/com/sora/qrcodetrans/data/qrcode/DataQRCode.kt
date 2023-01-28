package com.sora.qrcodetrans.data.qrcode

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

@Parcelize
data class ResponseQRCode(
    val isSuccess: Boolean,
    val data: DataQRCode
) : Parcelable

/**
 * Thông tin qrcode
 * CreatedBy: dbhuan 30/12/2021
 */
@Parcelize
data class DataQRCode(
    @field:Json( name = "IsSuccess") val isSuccess: Boolean = true,

    @field:Json( name = "ErrorCode") val errorCode: String? = null,

    /**
     * mã đơn hàng
     */
    @field:Json( name = "TxnId") val txnId: String,
    /**
     * Số tiền
     */

    @field:Json( name = "Amount") val amount: String,
    /**
     * Mã quy định của ngân hàng
     */
    @field:Json( name = "masterMerCode") val masterMerCode: String? = "",
    /**
     * Mã merchant ngân hàng cung cấp
     */
    @field:Json( name = "MerchantCode") val merchantCode: String,
    /**
     * Tên merchant ngân hàng cung cấp
     */
    @field:Json( name = "MerchantName") val merchantName: String?,
    /**
     * Mã điểm thu
     */
    @field:Json( name = "TerminalId") val terminalId: String,
    /**
     * Tên điểm thu
     */
    @field:Json( name = "TerminalName") val terminalName: String?,
) : Parcelable

