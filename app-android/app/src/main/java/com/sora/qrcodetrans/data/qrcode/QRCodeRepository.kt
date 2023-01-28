package com.sora.qrcodetrans.data.qrcode

import com.sora.qrcodetrans.data.auth.ConfigQRCode
import retrofit2.Response
import javax.inject.Inject

/**
 * repository của qrcode
 * Createdby: dbhuan 30/12/2021
 */
class QRCodeRepository @Inject constructor(private val qrCodeService: QRCodeService) {
    /**
     * hàm parse thông tin qrcode
     * CreatedBy: dbhuan 30/12/2021
     */
    suspend fun parseQRCode(url: String, data: String, token: String): Response<DataQRCode> =
        qrCodeService.parseQRCode(url, data, token)

}