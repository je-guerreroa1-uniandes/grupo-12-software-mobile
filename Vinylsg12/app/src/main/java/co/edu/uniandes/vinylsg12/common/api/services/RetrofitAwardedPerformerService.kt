package co.edu.uniandes.vinylsg12.common.api.services

import co.edu.uniandes.vinylsg12.common.api.interfaces.AwardedPerformerService
import co.edu.uniandes.vinylsg12.common.api.models.AwardedPerformer
import co.edu.uniandes.vinylsg12.common.constants.BASE_URL
import com.google.gson.JsonObject
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path

class RetrofitAwardedPerformerService: AwardedPerformerService {

    private val api: RetrofitAwardedPerformerService.AwardPerformerApi

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        api = retrofit.create(RetrofitAwardedPerformerService.AwardPerformerApi::class.java)
    }

    override fun add(
        musicianId: Int,
        prizeId: Int,
        awardDate: String,
        onComplete: (resp: AwardedPerformer?) -> Unit,
        onError: (error: Exception) -> Unit
    ) {
        val requestBody = JsonObject().apply {
            addProperty("premiationDate", awardDate)
        }
        val call = api.addPrizeTo(musicianId, prizeId, requestBody)
        call.enqueue(object : Callback<AwardedPerformer?> {
            override fun onResponse(
                call: Call<AwardedPerformer?>,
                response: Response<AwardedPerformer?>
            ) {
                if (response.isSuccessful) {
                    val savedAwardedPerformer = response.body()
                    onComplete(savedAwardedPerformer)
                } else {
                    val errorResponse = response.errorBody()?.string()
                    val errorMessage = try {
                        val json = JSONObject(errorResponse)
                        json.getString("message")
                    } catch (e: Exception) {
                        "Request failed with HTTP ${response.code()}"
                    }
                }
            }

            override fun onFailure(call: Call<AwardedPerformer?>, t: Throwable) {
                onError(Exception("Request failed: ${t.message}"))
            }
        })
    }

    interface AwardPerformerApi {
        @Headers("Content-Type: application/json")
        @POST("prizes/{prizeId}/musicians/{musicianId}")
        fun addPrizeTo(@Path("musicianId") musicianId: Int, @Path("prizeId") prizeId: Int, @Body requestBody: JsonObject): Call<AwardedPerformer?>
    }
}