package co.edu.uniandes.vinylsg12.common.api.services

import co.edu.uniandes.vinylsg12.common.api.interfaces.BandService
import co.edu.uniandes.vinylsg12.common.api.models.Band
import co.edu.uniandes.vinylsg12.common.constants.BASE_URL
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

class RetrofitBandService: BandService {

    private val api: BandApi

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        api = retrofit.create(BandApi::class.java)
    }

    override fun bands(
        onComplete: (resp: List<Band>) -> Unit,
        onError: (error: Exception) -> Unit
    ) {
        val call = api.bands()
        call.enqueue(object : Callback<List<Band>> {
            override fun onResponse(call: Call<List<Band>>, response: Response<List<Band>>) {
                if (response.isSuccessful) {
                    val bands = response.body() ?: emptyList()
                    onComplete(bands)
                } else {
                    onError(Exception("Request failed with HTTP ${response.code()}"))
                }
            }

            override fun onFailure(call: Call<List<Band>>, t: Throwable) {
                onError(Exception("Request failed: ${t.message}"))
            }
        })
    }

    interface BandApi {
        @GET("bands")
        fun bands(): Call<List<Band>>
    }
}