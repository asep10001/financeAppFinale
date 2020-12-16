package id.kotlin.anggota.Config

import id.kotlin.financeapp.Config.ApiServices
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkModule {

    fun getRetrofit(): Retrofit{
        return Retrofit.Builder().baseUrl("http://10.0.2.2/finalprojectkotlin/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun service() : ApiServices = getRetrofit().create(ApiServices::class.java)
}