package dev.sertan.android.rovercamera.data

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import dev.sertan.android.rovercamera.R
import dev.sertan.android.rovercamera.util.State
import kotlinx.coroutines.flow.flow
import retrofit2.await
import javax.inject.Inject

class NasaRepository @Inject constructor(
    @ApplicationContext private val context: Context,
    private val nasaApi: NasaApi
) {

    fun getPhotos(
        sol: Int? = null,
        earthDate: String? = null,
        page: Int? = null,
        camera: String? = null
    ) = flow {
        emit(State.LOADING)
        try {
            val node = nasaApi.getNode(
                sol,
                earthDate,
                page,
                if (camera == context.getString(R.string.all)) null else camera
            ).await()
            emit(if (node == null) State.ERROR else State.LOADED(node))
        } catch (e: Exception) {
            emit(State.ERROR)
        }
    }
}