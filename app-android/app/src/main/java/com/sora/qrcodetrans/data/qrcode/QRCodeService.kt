package com.sora.qrcodetrans.data.qrcode

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

/**
 * Interface service của qrcode
 * CreatedBy: dbhuan 30/12/2021
 */
interface QRCodeService {
    /**
     * Hàm lấy thông tin qrcode từ data QRCode
     * CreatedBy: dbhuan 30/12/2021
     */
    @POST("qrcodes/parse")
    suspend fun parseQRCode(@Body dataQRCodeReq: DataQRCodeReq): Response<DataQRCode>
}