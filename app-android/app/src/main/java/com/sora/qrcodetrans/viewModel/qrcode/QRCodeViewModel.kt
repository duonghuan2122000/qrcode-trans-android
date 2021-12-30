package com.sora.qrcodetrans.viewModel.qrcode

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.sora.qrcodetrans.data.ErrorRes
import com.sora.qrcodetrans.data.Resource
import com.sora.qrcodetrans.data.qrcode.QRCodeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

/**
 * view model qrcode: cầu nối giữa repo và activity
 * CreatedBy: dbhuan 30/12/2021
 */
@HiltViewModel
class QRCodeViewModel @Inject constructor(private val qrCodeRepository: QRCodeRepository): ViewModel() {
    /**
     * hàm parse qrcode
     * CreatedBy: dbhuan 30/12/2021
     */
    fun parseQRCode(data: String) = liveData(Dispatchers.IO){
        emit(Resource.loading(null))
        try {
            val res = qrCodeRepository.parseQRCode(data)
            if(res.isSuccessful){
                emit(Resource.success(res.body()))
            } else {
                val gson = Gson()
                val errRes = gson.fromJson(res.errorBody()?.string(), ErrorRes::class.java)
                emit(Resource.error(null, errRes.error?.message ?: "Có lỗi xảy ra"))
            }
        } catch (ex: Exception){
            emit(Resource.error(null, ex.message ?: "Có lỗi xảy ra"))
        }
    }
}