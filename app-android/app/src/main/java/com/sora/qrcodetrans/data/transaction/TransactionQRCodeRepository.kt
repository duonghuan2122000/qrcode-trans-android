package com.sora.qrcodetrans.data.transaction

import javax.inject.Inject

class TransactionQRCodeRepository @Inject constructor(
    private val transactionQRCodeService: TransactionQRCodeService
) {
    suspend fun createTransaction(url: String, token: String, input: TransactionQRCodeReq) =
        transactionQRCodeService.createTransaction(url, token, input)
}