package com.sora.qrcodetrans.data.auth

import retrofit2.Response
import retrofit2.http.*

interface GetTokenService {
    @FormUrlEncoded
    @POST
    suspend fun GetToken(
        @Url url: String,
        @Field("client_id") clientId: String,
        @Field("client_secret") clientSecret: String,
        @Field("grant_type") grant_type: String? = "client_credentials"
    ): Response<GetTokenRes>

    @GET
    suspend fun getConfig(
        @Url url: String,
        @Query("time") time: Long,
        @Query("signature") signature: String
    ): Response<ConfigQRCode>
}