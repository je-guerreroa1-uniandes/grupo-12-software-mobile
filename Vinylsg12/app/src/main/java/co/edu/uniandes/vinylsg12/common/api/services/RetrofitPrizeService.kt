package co.edu.uniandes.vinylsg12.common.api.services

import co.edu.uniandes.vinylsg12.common.api.interfaces.PrizeService
import co.edu.uniandes.vinylsg12.common.api.models.Album
import co.edu.uniandes.vinylsg12.common.api.models.Prize
import co.edu.uniandes.vinylsg12.common.constants.BASE_URL
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

class RetrofitPrizeService: PrizeService {

    private val api: PrizeApi

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        api = retrofit.create(PrizeApi::class.java)
    }

    override fun prizes(
        onComplete: (resp: List<Prize>) -> Unit,
        onError: (error: Exception) -> Unit
    ) {
        val call = api.prizes()
        call.enqueue(object : Callback<List<Prize>> {
            override fun onResponse(call: Call<List<Prize>>, response: Response<List<Prize>>) {
                if (response.isSuccessful) {
                    val prizes = response.body() ?: emptyList()
                    onComplete(prizes)
                } else {
                    onError(Exception("Request failed with HTTP ${response.code()}"))
                }
            }
            override fun onFailure(call: Call<List<Prize>>, t: Throwable) {
                onError(Exception("Request failed: ${t.message}"))
            }
        })
    }

    override fun create(
        name: String,
        description: String,
        organization: String,
        onComplete: (resp: Prize?) -> Unit,
        onError: (error: Exception) -> Unit
    ) {
        val prize = Prize(
            name = name,
            description = description,
            organization = organization
        ).copy(id = null)
        val call = api.create(prize)
        call.enqueue(object : Callback<Prize?> {
            override fun onResponse(call: Call<Prize?>, response: Response<Prize?>) {
                if (response.isSuccessful) {
                    val prize = response.body()
                    onComplete(prize)
                } else {
                    val errorResponse = response.errorBody()?.string()
                    val errorMessage = try {
                        val json = JSONObject(errorResponse)
                        json.getString("message")
                    } catch (e: Exception) {
                        "Request failed with HTTP ${response.code()}"
                    }
                    onError(Exception(errorMessage))
                }
            }

            override fun onFailure(call: Call<Prize?>, t: Throwable) {
                onError(Exception("Request failed: ${t.message}"))
            }
        })
    }

    interface PrizeApi {
        @GET("prizes")
        fun prizes(): Call<List<Prize>>

        @Headers("Content-Type: application/json")
        @POST("prizes")
        fun create(@Body prize: Prize): Call<Prize?>
    }
}