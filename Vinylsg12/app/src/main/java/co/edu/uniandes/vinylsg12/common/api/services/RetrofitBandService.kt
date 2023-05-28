package co.edu.uniandes.vinylsg12.common.api.services

import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.viewModelFactory
import co.edu.uniandes.vinylsg12.common.api.interfaces.BandService
import co.edu.uniandes.vinylsg12.common.api.models.Band
import co.edu.uniandes.vinylsg12.common.api.models.CollectedAlbum
import co.edu.uniandes.vinylsg12.common.api.models.Musician
import co.edu.uniandes.vinylsg12.common.constants.BASE_URL
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

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

    override  fun band(
        id: Int,
        onComplete: (resp: Band?) -> Unit,
        onError: (error: Exception) -> Unit
    ) {
        val call = api.band(id)
        call.enqueue(object: Callback<Band?> {
            override fun onResponse(call: Call<Band?>, response: Response<Band?>) {
                if (response.isSuccessful) {
                    val musician = response.body()
                    onComplete(musician)
                } else {
                    onError(Exception("Request failed with HTTP ${response.code()}"))
                }
            }

            override fun onFailure(call: Call<Band?>, t: Throwable) {
                onError(Exception("Request failed: ${t.message}"))
            }
        })
    }

    override fun add(
        musicianId: Int,
        bandId: Int,
        onComplete: (resp: Musician?) -> Unit,
        onError: (error: Exception) -> Unit
    ) {
        val call = api.add(
            bandId = bandId,
            musicianId = musicianId
        )

        call.enqueue(object : Callback<Musician?> {
            override fun onResponse(call: Call<Musician?>, response: Response<Musician?>) {
                if (response.isSuccessful) {
                    val savedMusician = response.body()
                    onComplete(savedMusician)
                } else {
                    onError(Exception("Request failed with HTTP ${response.code()}"))
                }
            }

            override fun onFailure(call: Call<Musician?>, t: Throwable) {
                onError(Exception("Request failed: ${t.message}"))
            }
        })
    }

    interface BandApi {
        @GET("bands")
        fun bands(): Call<List<Band>>

        @GET("bands/{id}")
        fun band(@Path("id") bandId: Int): Call<Band?>

        @POST("bands/{bandId}/musicians/{musicianId}")
        fun add(@Path("bandId") bandId: Int, @Path("musicianId") musicianId: Int): Call<Musician?>
    }
}