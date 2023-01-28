package com.sora.qrcodetrans.data.transaction

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Url

interface TransactionQRCodeService {
    @POST
    suspend fun createTransaction(
        @Url url: String,
        @Header("Authorization") token: String,
        @Body input: TransactionQRCodeReq
    ): Response<TransactionQRCodeRes>
}