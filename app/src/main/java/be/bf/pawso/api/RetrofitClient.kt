package be.bf.pawso.api

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RequestInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request();
        return chain.proceed(request)
    }
}
object RetrofitClient {
    private const val BASE_URL = "http://10.0.2.2:3000/"

    private val client = OkHttpClient().newBuilder()
        .addInterceptor(RequestInterceptor)
        .build();

    fun getClient(): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}
