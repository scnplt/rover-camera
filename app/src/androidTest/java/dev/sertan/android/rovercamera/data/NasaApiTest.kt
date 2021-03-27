package dev.sertan.android.rovercamera.data

import androidx.test.filters.SmallTest
import com.google.common.truth.Truth
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject

@SmallTest
@HiltAndroidTest
class NasaApiTest {
    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var nasaApi: NasaApi

    @Before
    fun setup() {
        hiltRule.inject()
    }

    @Test
    fun getNode() {
        val call = nasaApi.getNode(1, "0001-01-01", 1, "fhaz")
        val response = call.execute()
        Truth.assertThat(response.body()).isNotNull()
    }

    @Test
    fun getNodeWithoutParameters() {
        val call = nasaApi.getNode()
        val response = call.execute()
        Truth.assertThat(response.body()).isNotNull()
    }

    @Test
    fun getNodeWithSol() {
        val call = nasaApi.getNode(0)
        val response = call.execute()
        Truth.assertThat(response.body()).isNotNull()
    }

    @Test
    fun getNodeWithEarthDate() {
        val call = nasaApi.getNode(earthDate = "0001-01-01")
        val response = call.execute()
        Truth.assertThat(response.body()).isNotNull()
    }

    @Test
    fun getNodeWithPage() {
        val call = nasaApi.getNode(page = 0)
        val response = call.execute()
        Truth.assertThat(response.body()).isNotNull()
    }

    @Test
    fun getNodeWithCamera() {
        val call = nasaApi.getNode(camera = "fhaz")
        val response = call.execute()
        Truth.assertThat(response.body()).isNotNull()
    }
}