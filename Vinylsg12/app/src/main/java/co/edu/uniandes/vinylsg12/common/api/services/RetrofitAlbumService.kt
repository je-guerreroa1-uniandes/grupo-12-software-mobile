package co.edu.uniandes.vinylsg12.common.api.services

import co.edu.uniandes.vinylsg12.common.api.interfaces.AlbumService
import co.edu.uniandes.vinylsg12.common.api.models.Album
import co.edu.uniandes.vinylsg12.common.constants.BASE_URL
import com.google.gson.Gson
import okhttp3.MediaType
import okhttp3.RequestBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path


class RetrofitAlbumService : AlbumService {

    private val api: AlbumApi

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
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

    override fun create(
        name: String,
        cover: String,
        releaseDate: String,
        description: String,
        genre: String,
        recordLabel: String,
        onComplete: (resp: Album?) -> Unit,
        onError: (error: Exception) -> Unit
    ) {
        val album = Album(name = name, cover = cover, releaseDate = releaseDate, description = description, genre = genre, recordLabel = recordLabel)
        val call = api.create(album)
        call.enqueue(object : Callback<Album?> {
            override fun onResponse(call: Call<Album?>, response: Response<Album?>) {
                if (response.isSuccessful) {
                    val savedCollector = response.body()
                    onComplete(savedCollector)
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

        @Headers("Content-Type: application/json")
        @POST("albums")
        fun create(@Body album: Album): Call<Album?>
    }
}