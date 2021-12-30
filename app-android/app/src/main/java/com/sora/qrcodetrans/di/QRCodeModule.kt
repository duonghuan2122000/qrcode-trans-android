package com.sora.qrcodetrans.di

import com.sora.qrcodetrans.data.qrcode.QRCodeService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Object định nghĩa dependency injection cho toàn bộ app
 * CreatedBy: dbhuan 30/12/2021
 */
@Module
@InstallIn(SingletonComponent::class)
object QRCodeModule {

    private const val BASE_URL = "http://192.168.68.113:3000/"

    /**
     * DI cho retrofit
     * CreatedBy: dbhuan 30/12/2021
     */
    @Provides
    fun provideRetrofit(): Retrofit {
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        val client = OkHttpClient.Builder().addInterceptor(interceptor).build();
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    /**
     * DI cho dịch vụ QRCode
     * CreatedBy: dbhuan 30/12/2021
     */
    @Provides
    fun provideQRCodeService(retrofit: Retrofit): QRCodeService =
        retrofit.create(QRCodeService::class.java)
}