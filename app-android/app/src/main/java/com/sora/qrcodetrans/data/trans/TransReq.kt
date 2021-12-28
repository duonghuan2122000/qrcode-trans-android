package com.sora.qrcodetrans.data.trans

import java.text.SimpleDateFormat
import java.util.*


data class TransReq(
    val code: String = "00",
    val message: String = "Thanh cong",
    val msgType: String = "1",
    val txnId: String,
    val qrTrace: String = "",
    val bankCode: String = "JETPAY",
    val mobile: String = "",
    val accountNo: String = "",
    val amount: String = "",
    val payDate: String = SimpleDateFormat("yyyyMMddHHmmss").format(Date()),
    val masterMerCode: String = "",
    val merchantCode: String = "",
    val terminalId: String = "",
    val name: String = "",
    val phone: String = "",
    val province_id: String = "",
    val district_id: String = "",
    val address: String = "",
    val email: String = "",
    val addData: List<TransReqAddData> = mutableListOf()
)
