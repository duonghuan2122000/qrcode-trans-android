package com.sora.qrcodetrans.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.sora.qrcodetrans.data.MainRepository
import com.sora.qrcodetrans.data.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val mainRepository: MainRepository): ViewModel() {
    fun getUsers() = liveData(Dispatchers.IO){
        emit(Resource.loading(null))
        try {
            emit(Resource.success(mainRepository.getUsers()))
        } catch (ex: Exception){
            emit(Resource.error(null, ex.message ?: "Error"))
        }
    }
}