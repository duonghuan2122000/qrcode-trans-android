package com.sora.qrcodetrans.data.qrcode

import retrofit2.Response
import retrofit2.http.*

/**
 * Interface service của qrcode
 * CreatedBy: dbhuan 30/12/2021
 */
interface QRCodeService {
    /**
     * Hàm lấy thông tin qrcode từ data QRCode
     * CreatedBy: dbhuan 30/12/2021
     */
    @GET
    suspend fun parseQRCode(
        @Url url: String,
        @Query("data") data: String,
        @Header("Authorization") token: String
    ): Response<DataQRCode>
}