package com.sora.qrcodetrans.data.trans

import javax.inject.Inject

class TransHelper @Inject constructor(private val transService: TransService) {
    suspend fun createTransaction(transReq: TransReq) = transService.createTransaction(transReq)
}