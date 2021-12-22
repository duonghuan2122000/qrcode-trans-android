package com.sora.qrcodetrans.data.trans

class TransRepository(private val transHelper: TransHelper)
{
    suspend fun createTransaction(transReq: TransReq) = transHelper.createTransaction(transReq)
}