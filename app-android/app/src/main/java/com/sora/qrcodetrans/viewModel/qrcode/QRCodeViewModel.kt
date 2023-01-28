package com.sora.qrcodetrans.viewModel.qrcode

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.sora.qrcodetrans.const.ErrorInfo
import com.sora.qrcodetrans.const.QRCodeParseErrorInfo
import com.sora.qrcodetrans.data.Resource
import com.sora.qrcodetrans.data.qrcode.DataQRCode
import com.sora.qrcodetrans.data.qrcode.QRCodeRepository
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import okio.IOException
import java.net.SocketTimeoutException
import javax.inject.Inject

/**
 * view model qrcode: cầu nối giữa repo và activity
 * CreatedBy: dbhuan 30/12/2021
 */
@HiltViewModel
class QRCodeViewModel @Inject constructor(private val qrCodeRepository: QRCodeRepository) :
    ViewModel() {
    /**
     * hàm parse qrcode
     * CreatedBy: dbhuan 30/12/2021
     */
    fun parseQRCode(url: String, data: String, token: String) = liveData(Dispatchers.IO) {
        // emit loading
        emit(Resource.loading(null))

        try {
            // gọi api parse qrcode
            val res = qrCodeRepository.parseQRCode(url, data, "Bearer $token")

            // nếu thành công
            if (res.isSuccessful) {

                val resData = res.body() as DataQRCode
                val moshi: Moshi = Moshi.Builder().build()
                val jsonAdapter: JsonAdapter<DataQRCode> = moshi.adapter(DataQRCode::class.java)
                Log.d("QRCode-Data", jsonAdapter.toJson(resData))
                Log.d("QRCode-Res", "Parse")
                Log.d("QRCode-Amount", resData?.amount)
                Log.d("QRCode-IsSuccess", resData?.isSuccess.toString())
                if (resData.errorCode == null) {
                    // qrcode hợp lệ
                    emit(Resource.success(resData))
                } else {
                    // qrcode có lỗi
                    when (resData!!.errorCode) {
                        // qrcode không hợp lệ
                        QRCodeParseErrorInfo.Code.QRCodeInValid -> {
                            emit(Resource.error(null, QRCodeParseErrorInfo.Message.QRCodeInValid))
                        }

                        // qrcode đã thanh toán
                        QRCodeParseErrorInfo.Code.QRCodePaid -> {
                            emit(Resource.error(null, QRCodeParseErrorInfo.Message.QRCodePaid))
                        }

                        // lỗi chung
                        else -> {
                            emit(Resource.error(null, QRCodeParseErrorInfo.Message.Error))
                        }
                    }
                }
            } else {
                // emit lỗi chung
                emit(Resource.error(null, ErrorInfo.Message.Error))
            }
        } catch (ex: Exception) {
            Log.d("QRCode-Ex", ex.message.toString())
            // có exception
            when (ex) {
                // ex do kết nối
                is IOException,
                is SocketTimeoutException -> {
                    emit(Resource.error(null, ErrorInfo.Message.Timeout))
                }

                // ex chung
                else -> {
                    emit(Resource.error(null, ErrorInfo.Message.Error))
                }
            }
        }
    }
}