package dev.sertan.android.rovercamera.data

import dev.sertan.android.rovercamera.util.State
import kotlinx.coroutines.flow.flow
import retrofit2.await
import javax.inject.Inject

class NasaRepository @Inject constructor(private val nasaApi: NasaApi) {

    fun getPhotos(
        sol: Int? = null,
        earthDate: String? = null,
        page: Int? = null,
        camera: String? = null
    ) = flow {
        emit(State.LOADING)
        val node = nasaApi.getNode(sol, earthDate, page, camera).await()
        emit(if (node == null) State.ERROR else State.LOADED(node))
    }
}