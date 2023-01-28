package com.sora.qrcodetrans.di

import com.sora.qrcodetrans.data.auth.GetTokenService
import com.sora.qrcodetrans.data.qrcode.QRCodeService
import com.sora.qrcodetrans.data.transaction.TransactionQRCodeService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

/**
 * Object định nghĩa dependency injection cho toàn bộ app
 * CreatedBy: dbhuan 30/12/2021
 */
@Module
@InstallIn(SingletonComponent::class)
object QRCodeModule {

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
            .client(client)
            .baseUrl("http://localhost")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }

    /**
     * DI cho dịch vụ QRCode
     * CreatedBy: dbhuan 30/12/2021
     */
    @Provides
    fun provideQRCodeService(retrofit: Retrofit): QRCodeService =
        retrofit.create(QRCodeService::class.java)

    @Provides
    fun provideGetTokenService(retrofit: Retrofit): GetTokenService =
        retrofit.create(GetTokenService::class.java)

    @Provides
    fun provideCreateTransactionService(retrofit: Retrofit): TransactionQRCodeService =
        retrofit.create(TransactionQRCodeService::class.java)

}