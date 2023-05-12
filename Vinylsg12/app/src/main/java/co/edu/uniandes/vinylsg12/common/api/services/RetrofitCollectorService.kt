package co.edu.uniandes.vinylsg12.common.api.services

import co.edu.uniandes.vinylsg12.common.api.interfaces.CollectorService
import co.edu.uniandes.vinylsg12.common.api.models.Album
import co.edu.uniandes.vinylsg12.common.api.models.Collector
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

class RetrofitCollectorService: CollectorService {

    private val api: CollectorApi

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        api = retrofit.create(CollectorApi::class.java)
    }

    override fun collectors(
        onComplete: (resp: List<Collector>) -> Unit,
        onError: (error: Exception) -> Unit
    ) {
        val call = api.collectors()
        call.enqueue(object : Callback<List<Collector>> {
            override fun onResponse(call: Call<List<Collector>>, response: Response<List<Collector>>) {
                if (response.isSuccessful) {
                    val collectors = response.body() ?: emptyList()
                    onComplete(collectors)
                } else {
                    onError(Exception("Request failed with HTTP ${response.code()}"))
                }
            }

            override fun onFailure(call: Call<List<Collector>>, t: Throwable) {
                onError(Exception("Request failed: ${t.message}"))
            }
        })
    }

    override fun collector(
        id: Int,
        onComplete: (resp: Collector?) -> Unit,
        onError: (error: Exception) -> Unit
    ) {
        val call = api.collector(id)
        call.enqueue(object : Callback<Collector?> {
            override fun onResponse(call: Call<Collector?>, response: Response<Collector?>) {
                if (response.isSuccessful) {
                    val collector = response.body()
                    onComplete(collector)
                } else {
                    onError(Exception("Request failed with HTTP ${response.code()}"))
                }
            }

            override fun onFailure(call: Call<Collector?>, t: Throwable) {
                onError(Exception("Request failed: ${t.message}"))
            }
        })
    }

    override fun create(
        name: String,
        telephone: String,
        email: String,
        onComplete: (resp: Collector?) -> Unit,
        onError: (error: Exception) -> Unit
    ) {
        val collector = Collector(name = name, telephone = telephone, email = email)
        val call = api.create(collector)
        call.enqueue(object : Callback<Collector?> {
            override fun onResponse(call: Call<Collector?>, response: Response<Collector?>) {
                if (response.isSuccessful) {
                    val savedCollector = response.body()
                    onComplete(savedCollector)
                } else {
                    onError(Exception("Request failed with HTTP ${response.code()}"))
                }
            }

            override fun onFailure(call: Call<Collector?>, t: Throwable) {
                onError(Exception("Request failed: ${t.message}"))
            }
        })
    }

    interface CollectorApi {
        @GET("collectors")
        fun collectors(): Call<List<Collector>>

        @GET("collectors/{id}")
        fun collector(@Path("id") collectorId: Int): Call<Collector?>

        @POST("collectors")
        fun create(@Body collector: Collector): Call<Collector?>
    }
}