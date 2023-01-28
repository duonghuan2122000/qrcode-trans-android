package com.sora.qrcodetrans.viewModel.auth

import android.content.Context
import androidx.lifecycle.*
import com.sora.qrcodetrans.const.ErrorInfo
import com.sora.qrcodetrans.data.Resource
import com.sora.qrcodetrans.data.Status
import com.sora.qrcodetrans.data.auth.ConfigQRCode
import com.sora.qrcodetrans.data.auth.GetTokenRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import java.io.ByteArrayInputStream
import java.io.File
import java.io.ObjectInput
import java.io.ObjectInputStream
import java.io.*
import java.math.BigInteger
import java.net.SocketTimeoutException
import java.security.MessageDigest
import java.util.*
import javax.inject.Inject

@HiltViewModel
class GetTokenViewModel @Inject constructor(
    private val getTokenRepository: GetTokenRepository,
    @ApplicationContext private val context: Context,
) : ViewModel() {

    /**
     * Tên file lưu token (cache)
     */
    private val fileToken = "com.dbhuan.qrcode_test.token"

    /**
     * Tên file lưu config
     */
    private val fileConfig = "com.dbhuan.qrcode_test.config"

    /**
     * url lấy config
     */
    private val urlGetConfig = "http://dbhuan.tk:5001/config"

    /**
     * Secret key tạo signature cho api lấy config
     */
    private val secretKey = "dbhuan"

    /**
     * Thời gian token có hiệu lực (tính bằng giây)
     */
    private val tokenExpireIn = 12 * 60 * 60

    /**
     * Hàm kiểm tra token trong cache và gọi api lấy token nếu cần
     * Token được lưu trong cache 12h
     * Hàm này sẽ được gọi tại màn hình khởi động app (splash screen)
     */
    private fun storeToken(urlCreateToken: String, clientId: String, clientSecret: String) =
        liveData(Dispatchers.IO) {
            // emit loading
            emit(Resource.loading(null))

            // file cache lưu token
            var tokenFile = File(context.cacheDir, fileToken)

            // nếu ko có cache
            if (!tokenFile.exists()) {

                // gọi api lấy token và luwuu vào cache
                val res = getTokenRepository.getToken(urlCreateToken, clientId, clientSecret)
                val token = res.body()?.access_token
                if (res.isSuccessful && token != null) {
                    // lưu token
                    tokenFile.createNewFile()
                    tokenFile.writeText(token)
                    emit(Resource.success(true))
                } else {
                    emit(Resource.error(false, ErrorInfo.Message.Error))
                }

            } else {
                // đã tồn tại token trong cache và kiểm tra token còn hạn
                val lastModifiedFile = Date(tokenFile.lastModified())

                if (Date().time - lastModifiedFile.time > tokenExpireIn * 1000) {
                    // nếu token đã hết hạn
                    val res = getTokenRepository.getToken(urlCreateToken, clientId, clientSecret)

                    val token = res.body()?.access_token

                    if (res.isSuccessful && token != null) {
                        // luu token
                        tokenFile.createNewFile()
                        tokenFile.writeText(token)
                        emit(Resource.success(true))
                    } else {
                        emit(Resource.error(false, ErrorInfo.Message.Error))
                    }
                } else {
                    // token vẫn còn hiệu lực
                    emit(Resource.success(true))
                }
            }
        }

    /**
     * Hàm lấy token từ cache
     */
    fun getToken(): String {
        var tokenFile = File(context.cacheDir, fileToken)
        val token = tokenFile.readText()
        return token
    }

    /**
     * Hàm lấy thông tin config từ cache
     */
    fun getConfig(): ConfigQRCode {
        var configQRCode: ConfigQRCode
        var configFile = File(context.cacheDir, fileConfig)

        val configBytes = configFile.readBytes();
        configQRCode = fromByteArray<ConfigQRCode>(configBytes)
        return configQRCode
    }

    /**
     * hàm lấy thông tin config từ api
     * Hàm được gọi mỗi lần khi mở app tài màn hình khởi động (splash screen)
     */
    fun fetchConfig() = liveData(Dispatchers.IO) {
        // khởi tạo đối tượng config
        var configQRCode: ConfigQRCode

        // emit loading
        emit(Resource.loading(null))

        // file cache config
        var configFile = File(context.cacheDir, fileConfig)

        // goi api lấy config
        var time = Date().time

        // tạo signature cho api lấy config
        var signature = md5(time.toString() + secretKey)

        // gọi api lấy config
        try {
            var res = getTokenRepository.getConfig(urlGetConfig, time, signature)

            if (!res.isSuccessful || res.body() == null) {
                emit(Resource.error(null, ErrorInfo.Message.Error))
            } else {
                if (!configFile.exists()) {
                    configFile.createNewFile()
                }
                configQRCode = res.body()!!
                configFile.writeBytes(configQRCode.toByteArray())

                emit(Resource.success(configQRCode))
            }
        } catch (ex: Exception) {
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

    /**
     * Hàm khởi tạo config và kiểm tra token khi khởi động app
     * GetConfig -> StoreToken
     */
    fun initOnStart() = Transformations.switchMap(fetchConfig()) { resource ->
        when (resource.status) {
            Status.LOADING -> {
                return@switchMap liveData(Dispatchers.IO) {
                    emit(Resource.loading(null))
                }
            }
            Status.SUCCESS -> {
                val config = resource.data!!
                return@switchMap storeToken(
                    config.urlGetToken,
                    config.clientId,
                    config.clientSecret
                )
            }
            Status.ERROR -> {
                return@switchMap liveData(Dispatchers.IO) {
                    emit(Resource.error(null, resource.message ?: ErrorInfo.Message.Error))
                }
            }
        }
    }

    /**
     * Hàm chuyển byte arr thành obj
     */
    private fun <T : Serializable> fromByteArray(byteArray: ByteArray): T {
        val byteArrayInputStream = ByteArrayInputStream(byteArray)
        val objectInput: ObjectInput = ObjectInputStream(byteArrayInputStream)
        val result = objectInput.readObject() as T
        objectInput.close()
        byteArrayInputStream.close()
        return result
    }

    /**
     * Hàm chuyển obj thành byte arr
     */
    private fun Serializable.toByteArray(): ByteArray {
        val byteArrayOutputStream = ByteArrayOutputStream()
        val objectOutputStream = ObjectOutputStream(byteArrayOutputStream)
        objectOutputStream.writeObject(this)
        objectOutputStream.flush()
        val result = byteArrayOutputStream.toByteArray()
        byteArrayOutputStream.close()
        objectOutputStream.close()
        return result
    }

    /**
     * Hàm mã hóa md5
     */
    private fun md5(input: String): String {
        val md = MessageDigest.getInstance("MD5")
        return BigInteger(1, md.digest(input.toByteArray())).toString(16).padStart(32, '0')
    }
}