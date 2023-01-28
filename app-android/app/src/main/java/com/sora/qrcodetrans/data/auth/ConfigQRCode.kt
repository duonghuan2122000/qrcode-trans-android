package com.sora.qrcodetrans.data.auth

import java.io.Serializable

data class ConfigQRCode(
    val clientId: String,
    val clientSecret: String,
    val urlParseQRCode: String,
    val urlTransactionQRCode: String,
    val urlGetToken: String
): Serializable
