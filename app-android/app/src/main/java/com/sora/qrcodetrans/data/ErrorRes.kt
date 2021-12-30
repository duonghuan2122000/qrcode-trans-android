package com.sora.qrcodetrans.data

/**
 * Res lỗi
 * CreatedBy: dbhuan 30/12/2021
 */
data class ErrorRes(
    val error: ErrorResBody?
)

data class ErrorResBody(
    val code: String,
    val message: String = "Có lỗi xảy ra"
)
