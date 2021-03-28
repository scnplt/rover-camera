package dev.sertan.android.rovercamera.ui.search

import android.content.Context
import android.view.View
import android.widget.PopupMenu
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.findNavController
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import dev.sertan.android.rovercamera.R
import dev.sertan.android.rovercamera.util.extensions.showToast
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(@ApplicationContext context: Context) : ViewModel() {
    private var cameraPopupMenu: PopupMenu? = null
    val isDateTypeSol = MutableLiveData(true)
    val selectedCamera = MutableLiveData(context.getString(R.string.all))
    val earthDate = MutableLiveData("")
    val sol = MutableLiveData("")

    fun changeDateType() = isDateTypeSol.value?.let {
        isDateTypeSol.value = !it
    }

    fun showCameraPopupMenu(view: View) {
        if (cameraPopupMenu == null) cameraPopupMenu = createCameraPopupMenu(view)
        cameraPopupMenu!!.show()
    }

    fun search(view: View) {
        if (earthDate.value!!.isBlank() && sol.value!!.isBlank()) {
            view.context.showToast(view.context.getString(R.string.sol_and_earth_date_are_blank))
            return
        }

        val action = SearchFragmentDirections.actionSearchFragmentToResultFragment(
            earthDate.value!!,
            sol.value!!,
            selectedCamera.value!!,
            isDateTypeSol.value!!
        )
        view.findNavController().navigate(action)
    }

    private fun createCameraPopupMenu(view: View): PopupMenu =
        PopupMenu(view.context, view).apply {
            menuInflater.inflate(R.menu.fragment_search_camera_menu, this.menu)
            setOnMenuItemClickListener {
                selectedCamera.value = it.title.toString()
                true
            }
        }
}