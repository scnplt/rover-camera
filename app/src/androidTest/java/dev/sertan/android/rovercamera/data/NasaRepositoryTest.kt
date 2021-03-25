package dev.sertan.android.rovercamera.data

import androidx.test.filters.SmallTest
import com.google.common.truth.Truth
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dev.sertan.android.rovercamera.util.State
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject

@SmallTest
@HiltAndroidTest
class NasaRepositoryTest {
    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var nasaRepository: NasaRepository

    @Before
    fun setup() {
        hiltRule.inject()
    }

    @Test
    fun getPhotos_LoadingState() = runBlocking {
        val state = nasaRepository.getPhotos().first()
        Truth.assertThat(state).isInstanceOf(State.LOADING::class.java)
    }

    @Test
    fun getPhotos_LoadedState() = runBlocking {
        val state = nasaRepository.getPhotos().toList().last()
        Truth.assertThat(state).isInstanceOf(State.LOADED::class.java)
    }

    @Test
    fun getPhotos_ErrorState() = runBlocking {
        val state = nasaRepository.getPhotos(earthDate = "").toList().last()
        Truth.assertThat(state).isInstanceOf(State.ERROR::class.java)
    }
}