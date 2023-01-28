package com.sora.qrcodetrans.data.transaction

import com.squareup.moshi.Json

class TransactionQRCodeRes(
    @field:Json(name = "Code") val code: String,

    @field:Json(name = "Message") val message: String
)