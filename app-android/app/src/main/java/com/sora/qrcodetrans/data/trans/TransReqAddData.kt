package com.sora.qrcodetrans.data.trans

data class TransReqAddData(
    val productId: String,
    val amount: String,
    val tipAndFee: String,
    val ccy: String,
    val qty: String,
    val note: String
)
