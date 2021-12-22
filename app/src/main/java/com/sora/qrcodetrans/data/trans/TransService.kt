package com.sora.qrcodetrans.data.trans

import retrofit2.http.Body
import retrofit2.http.POST

interface TransService {
    @POST("transactions")
    suspend fun createTransaction(@Body transReq: TransReq): TransRes
}