package co.edu.uniandes.vinylsg12.common.api.services

import co.edu.uniandes.vinylsg12.common.api.interfaces.AlbumService
import co.edu.uniandes.vinylsg12.common.api.models.Album
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path


class RetrofitAlbumService : AlbumService {

    private val api: AlbumApi

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://vynils-back-heroku.herokuapp.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        api = retrofit.create(AlbumApi::class.java)
    }

    override fun albums(
        onComplete: (resp: List<Album>) -> Unit,
        onError: (error: Exception) -> Unit
    ) {
        val call = api.getAlbums()
        call.enqueue(object : Callback<List<Album>> {
            override fun onResponse(call: Call<List<Album>>, response: Response<List<Album>>) {
                if (response.isSuccessful) {
                    val albums = response.body() ?: emptyList()
                    onComplete(albums)
                } else {
                    onError(Exception("Request failed with HTTP ${response.code()}"))
                }
            }

            override fun onFailure(call: Call<List<Album>>, t: Throwable) {
                onError(Exception("Request failed: ${t.message}"))
            }
        })
    }

    override fun album(
        id: Int,
        onComplete: (resp: Album?) -> Unit,
        onError: (error: Exception) -> Unit
    ) {
        val call = api.getAlbum(id)
        call.enqueue(object : Callback<Album?> {
            override fun onResponse(call: Call<Album?>, response: Response<Album?>) {
                if (response.isSuccessful) {
                    val album = response.body()
                    onComplete(album)
                } else {
                    onError(Exception("Request failed with HTTP ${response.code()}"))
                }
            }

            override fun onFailure(call: Call<Album?>, t: Throwable) {
                onError(Exception("Request failed: ${t.message}"))
            }
        })
    }

    interface AlbumApi {
        @GET("albums")
        fun getAlbums(): Call<List<Album>>

        @GET("albums/{id}")
        fun getAlbum(@Path("id") albumId: Int): Call<Album?>

    }
}