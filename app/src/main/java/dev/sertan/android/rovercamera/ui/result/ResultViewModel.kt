package dev.sertan.android.rovercamera.ui.result

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.sertan.android.rovercamera.data.NasaRepository
import dev.sertan.android.rovercamera.util.State
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ResultViewModel @Inject constructor(private val nasaRepo: NasaRepository) : ViewModel() {
    val stateLiveData = MutableLiveData<State>()
    val isStateLoadedLiveData = MutableLiveData(stateLiveData.value is State.LOADED<*>)
    val isStateErrorLiveData = MutableLiveData(stateLiveData.value is State.ERROR)
    val isStateLoadingLiveData = MutableLiveData(stateLiveData.value is State.LOADING)

    private fun setState(value: State) {
        isStateLoadedLiveData.value = value is State.LOADED<*>
        isStateErrorLiveData.value = value is State.ERROR
        isStateLoadingLiveData.value = value is State.LOADING
        stateLiveData.value = value
    }

    fun search(sol: Int? = null, earthDate: String? = null, page: Int? = null, camera: String? = null) =
        viewModelScope.launch { nasaRepo.getPhotos(sol, earthDate, page, camera).collect { setState(it) } }

}