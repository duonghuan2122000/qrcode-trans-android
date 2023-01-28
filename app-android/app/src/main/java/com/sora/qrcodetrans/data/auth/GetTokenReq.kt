package com.sora.qrcodetrans.data.auth

data class GetTokenReq(
    val client_id: String,
    val client_secret: String,
    val grant_type: String? = "client_credentials"
)