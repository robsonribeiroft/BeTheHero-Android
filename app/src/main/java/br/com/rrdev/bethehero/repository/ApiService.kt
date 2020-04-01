package br.com.rrdev.bethehero.repository

import br.com.rrdev.bethehero.models.Incident
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET


object Api{

    fun getApiService(): ApiService {
        val retrofit =  Retrofit
            .Builder()
            .baseUrl(ApiService.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val apiService = retrofit.create(ApiService::class.java)
        return apiService
    }

}


interface ApiService {

    companion object{
        const val BASE_URL = "http://localhost:3333/"
    }

    @GET("incidents")
    fun getIncidents(): Call<List<Incident>>
}