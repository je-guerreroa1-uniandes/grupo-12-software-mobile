package co.edu.uniandes.vinylsg12.common.api.services

import co.edu.uniandes.vinylsg12.common.api.interfaces.CollectorAlbumService
import co.edu.uniandes.vinylsg12.common.api.models.Album
import co.edu.uniandes.vinylsg12.common.api.models.CollectedAlbum
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

class RetrofitCollectorAlbumService: CollectorAlbumService {

    private val api: RetrofitCollectorAlbumService.CollectorAlbumApi

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        api = retrofit.create(RetrofitCollectorAlbumService.CollectorAlbumApi::class.java)
    }

    override fun albums(
        collectorId: Int,
        onComplete: (resp: List<CollectedAlbum>) -> Unit,
        onError: (error: Exception) -> Unit
    ) {
        val call = api.albums(collectorId)
        call.enqueue(object : Callback<List<CollectedAlbum>> {
            override fun onResponse(call: Call<List<CollectedAlbum>>, response: Response<List<CollectedAlbum>>) {
                if (response.isSuccessful) {
                    val albums = response.body() ?: emptyList()
                    onComplete(albums)
                } else {
                    onError(Exception("Request failed with HTTP ${response.code()}"))
                }
            }

            override fun onFailure(call: Call<List<CollectedAlbum>>, t: Throwable) {
                onError(Exception("Request failed: ${t.message}"))
            }
        })
    }

    override fun add(
        albumId: Int,
        collectorId: Int,
        price: Int,
        status: String,
        onComplete: (resp: CollectedAlbum?) -> Unit,
        onError: (error: Exception) -> Unit
    ) {
        val collectedAlbum = CollectedAlbum(price = price, status = status)
        val call = api.add(collectorId, albumId, collectedAlbum)
        call.enqueue(object : Callback<CollectedAlbum?> {
            override fun onResponse(call: Call<CollectedAlbum?>, response: Response<CollectedAlbum?>) {
                if (response.isSuccessful) {
                    val savedCollector = response.body()
                    onComplete(savedCollector)
                } else {
                    onError(Exception("Request failed with HTTP ${response.code()}"))
                }
            }

            override fun onFailure(call: Call<CollectedAlbum?>, t: Throwable) {
                onError(Exception("Request failed: ${t.message}"))
            }
        })
    }

    interface CollectorAlbumApi {
        @GET("collectors/{id}/albums")
        fun albums(@Path("id") collectorId: Int): Call<List<CollectedAlbum>>

        @POST("collectors/{collectorId}/albums/{albumId}")
        fun add(@Path("collectorId") collectorId: Int, @Path("albumId") albumId: Int, @Body collectedAlbum: CollectedAlbum): Call<CollectedAlbum?>
    }
}