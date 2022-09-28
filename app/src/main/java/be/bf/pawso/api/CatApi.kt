package be.bf.pawso.api

import be.bf.pawso.models.Cat
import be.bf.pawso.models.Shelter
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface CatApi {
    @GET("cat")
    fun cats(): Call<List<Cat>>

    @GET("shelter")
    fun shelterById(@Query("shelterId") shelterId : String) : Call<Shelter>
}