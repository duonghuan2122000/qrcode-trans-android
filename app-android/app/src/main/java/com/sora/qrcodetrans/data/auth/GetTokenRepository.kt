package com.sora.qrcodetrans.data.auth

import retrofit2.Response
import javax.inject.Inject

class GetTokenRepository @Inject constructor(private val getTokenService: GetTokenService) {
    /**
     * Hàm lấy token
     */
    suspend fun getToken(url: String, clientId: String, clientSecret: String) =
        getTokenService.GetToken(url, clientId, clientSecret)

    /**
     * Hàm lấy config
     */
    suspend fun getConfig(url: String, time: Long, token: String) =
        getTokenService.getConfig(url, time, token)
}