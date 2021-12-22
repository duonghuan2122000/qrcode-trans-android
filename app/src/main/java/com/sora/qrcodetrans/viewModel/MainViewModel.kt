package com.sora.qrcodetrans.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.sora.qrcodetrans.data.MainRepository
import com.sora.qrcodetrans.data.Resource
import kotlinx.coroutines.Dispatchers
import java.lang.Exception

class MainViewModel(private val mainRepository: MainRepository): ViewModel() {
    fun getUsers() = liveData(Dispatchers.IO){
        emit(Resource.loading(null))
        try {
            emit(Resource.success(mainRepository.getUsers()))
        } catch (ex: Exception){
            emit(Resource.error(null, ex.message ?: "Error"))
        }
    }
}