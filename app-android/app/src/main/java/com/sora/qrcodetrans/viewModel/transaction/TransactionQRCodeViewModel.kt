package com.sora.qrcodetrans.viewModel.transaction

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.sora.qrcodetrans.const.ErrorInfo
import com.sora.qrcodetrans.const.TransactionQRCodeErrorInfo
import com.sora.qrcodetrans.data.Resource
import com.sora.qrcodetrans.data.transaction.TransactionQRCodeRepository
import com.sora.qrcodetrans.data.transaction.TransactionQRCodeReq
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import java.io.IOException
import java.net.SocketTimeoutException
import javax.inject.Inject

@HiltViewModel
class TransactionQRCodeViewModel @Inject constructor(
    private val transactionQRCodeRepository: TransactionQRCodeRepository
) : ViewModel() {
    /**
     * Hàm thanh toán QR
     */
    fun createTransaction(url: String, token: String, input: TransactionQRCodeReq) =
        liveData(Dispatchers.IO) {
            // emit loading
            emit(Resource.loading(null))

            try {
                val res = transactionQRCodeRepository.createTransaction(url, "Bearer $token", input)
                if (res.isSuccessful) {
                    val resData = res.body()!!
                    when (resData.code) {
                        TransactionQRCodeErrorInfo.Code.Success -> {
                            emit(Resource.success(TransactionQRCodeErrorInfo.Message.Success))
                        }
                        TransactionQRCodeErrorInfo.Code.Paid -> {
                            emit(Resource.error(null, TransactionQRCodeErrorInfo.Message.Paid))
                        }
                        TransactionQRCodeErrorInfo.Code.Expired -> {
                            emit(Resource.error(null, TransactionQRCodeErrorInfo.Message.Expired))
                        }
                        else -> {
                            emit(Resource.error(null, TransactionQRCodeErrorInfo.Message.Error))
                        }
                    }
                } else {
                    emit(Resource.error(null, TransactionQRCodeErrorInfo.Message.Error))
                }
            } catch (ex: Exception) {
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