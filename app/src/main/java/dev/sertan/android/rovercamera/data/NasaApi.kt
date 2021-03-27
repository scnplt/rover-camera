package dev.sertan.android.rovercamera.data

import dev.sertan.android.rovercamera.data.model.Node
import dev.sertan.android.rovercamera.util.NasaApiUtils
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * This interface is for get data with Retrofit.
 */
interface NasaApi {
    @GET("photos")
    fun getNode(
        @Query("sol") sol: Int? = null,
        @Query("earth_date") earthDate: String? = null,
        @Query("page") page: Int? = null,
        @Query("camera") camera: String? = null,
        @Query("api_key") apiKey: String = NasaApiUtils.API_KEY
    ): Call<Node?>
}