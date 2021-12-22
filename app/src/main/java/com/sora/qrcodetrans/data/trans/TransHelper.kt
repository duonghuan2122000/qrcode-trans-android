package com.sora.qrcodetrans.data.trans

class TransHelper(private val transService: TransService) {
    suspend fun createTransaction(transReq: TransReq) = transService.createTransaction(transReq)
}