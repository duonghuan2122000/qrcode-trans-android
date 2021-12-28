package com.sora.qrcodetrans.data.trans

import javax.inject.Inject

class TransRepository @Inject constructor(private val transHelper: TransHelper)
{
    suspend fun createTransaction(transReq: TransReq) = transHelper.createTransaction(transReq)
}