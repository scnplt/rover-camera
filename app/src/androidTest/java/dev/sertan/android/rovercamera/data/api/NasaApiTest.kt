package dev.sertan.android.rovercamera.data.api

import androidx.test.filters.SmallTest
import com.google.common.truth.Truth
import dev.sertan.android.rovercamera.data.NasaApi
import dev.sertan.android.rovercamera.util.NasaApiUtils
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@SmallTest
class NasaApiTest {
    private lateinit var nasaApi: NasaApi

    @Before
    fun setup() {
        nasaApi = Retrofit.Builder()
            .baseUrl(NasaApiUtils.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NasaApi::class.java)
    }

    @Test
    fun getNode(){
        val call = nasaApi.getNode(1,"2015-01-01",1,"fhaz")
        val response = call.execute()
        Truth.assertThat(response.body()).isNotNull()
    }

    @Test
    fun getNodeWithoutParameters(){
        val call = nasaApi.getNode()
        val response = call.execute()
        Truth.assertThat(response.body()).isNotNull()
    }

    @Test
    fun getNodeWithSol(){
        val call = nasaApi.getNode(1)
        val response = call.execute()
        Truth.assertThat(response.body()).isNotNull()
    }

    @Test
    fun getNodeWithEarthDate(){
        val call = nasaApi.getNode(earthDate = "2015-01-01")
        val response = call.execute()
        Truth.assertThat(response.body()).isNotNull()
    }

    @Test
    fun getNodeWithPage(){
        val call = nasaApi.getNode(page = 1)
        val response = call.execute()
        Truth.assertThat(response.body()).isNotNull()
    }

    @Test
    fun getNodeWithCamera(){
        val call = nasaApi.getNode(camera = "fhaz")
        val response = call.execute()
        Truth.assertThat(response.body()).isNotNull()
    }
}