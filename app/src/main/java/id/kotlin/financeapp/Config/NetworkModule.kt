package id.kotlin.anggota.Config

import id.kotlin.financeapp.Config.ApiServices
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkModule {

    private val interceptor : Interceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
    private val client : OkHttpClient = OkHttpClient.Builder().addInterceptor(interceptor).build();
    private fun getRetrofit(): Retrofit{
        return Retrofit.Builder().baseUrl("https://finance-uda-coding.herokuapp.com/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun service() : ApiServices = getRetrofit().create(ApiServices::class.java)
}