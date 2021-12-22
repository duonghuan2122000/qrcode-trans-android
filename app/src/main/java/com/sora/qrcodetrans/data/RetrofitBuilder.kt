package com.sora.qrcodetrans.data

import com.sora.qrcodetrans.data.trans.TransService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitBuilder {
    private const val BASE_URL = "https://jsonplaceholder.typicode.com/"

    private fun getRetrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    val apiService: ApiService = getRetrofit().create(ApiService::class.java)

    val transService: TransService = getRetrofit().create(TransService::class.java)
}