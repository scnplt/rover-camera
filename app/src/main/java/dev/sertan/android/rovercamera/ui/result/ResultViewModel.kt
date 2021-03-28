package dev.sertan.android.rovercamera.ui.result

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.sertan.android.rovercamera.data.NasaRepository
import dev.sertan.android.rovercamera.data.model.Node
import dev.sertan.android.rovercamera.util.State
import dev.sertan.android.rovercamera.util.extensions.toIntOrZero
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ResultViewModel @Inject constructor(private val nasaRepo: NasaRepository) : ViewModel() {
    val stateLiveData = MutableLiveData<State>()
    val listSize = MutableLiveData(0)

    fun search(sol: String, earthDate: String, camera: String, isDateSol: Boolean) =
        viewModelScope.launch {
            val flow = if (isDateSol) nasaRepo.getPhotos(sol.toIntOrZero(), null, camera)
            else nasaRepo.getPhotos(null, earthDate, camera)
            flow.collect {
                stateLiveData.value = it
                listSize.value = if (it is State.LOADED<*>) (it.data as Node).photos.size
                else 0
            }
        }
}