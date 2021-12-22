package com.sora.qrcodetrans.viewModel.trans

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.sora.qrcodetrans.data.Resource
import com.sora.qrcodetrans.data.trans.TransRepository
import com.sora.qrcodetrans.data.trans.TransReq
import kotlinx.coroutines.Dispatchers
import java.lang.Exception

class TransViewModel(private val transRepository: TransRepository): ViewModel() {
    fun createTransaction(transReq: TransReq) = liveData(Dispatchers.IO){
        emit(Resource.loading(null))
        try {
            emit(Resource.success(transRepository.createTransaction(transReq)))
        } catch (ex: Exception){
            emit(Resource.error(null, ex.message ?: "error"))
        }
    }
}