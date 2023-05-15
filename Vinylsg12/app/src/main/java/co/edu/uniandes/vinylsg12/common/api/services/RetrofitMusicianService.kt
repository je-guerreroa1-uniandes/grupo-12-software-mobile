package co.edu.uniandes.vinylsg12.common.api.services

import co.edu.uniandes.vinylsg12.common.api.interfaces.MusicianService
import co.edu.uniandes.vinylsg12.common.api.models.Band
import co.edu.uniandes.vinylsg12.common.api.models.Musician
import co.edu.uniandes.vinylsg12.common.constants.BASE_URL
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import java.lang.Exception

class RetrofitMusicianService: MusicianService {

    private val api: MusicianApi

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        api = retrofit.create(MusicianApi::class.java)
    }

    override fun musicians(
        onComplete: (resp: List<Musician>) -> Unit,
        onError: (error: Exception) -> Unit
    ) {
        val call = api.musicians()
        call.enqueue(object : Callback<List<Musician>> {
            override fun onResponse(call: Call<List<Musician>>, response: Response<List<Musician>>) {
                if (response.isSuccessful) {
                    val bands = response.body() ?: emptyList()
                    onComplete(bands)
                } else {
                    onError(Exception("Request failed with HTTP ${response.code()}"))
                }
            }

            override fun onFailure(call: Call<List<Musician>>, t: Throwable) {
                onError(Exception("Request failed: ${t.message}"))
            }
        })
    }

    interface MusicianApi {
        @GET("musicians")
        fun musicians(): Call<List<Musician>>
    }
}