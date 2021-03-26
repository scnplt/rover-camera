package dev.sertan.android.rovercamera.ui

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import dev.sertan.android.rovercamera.ui.result.ResultAdapter
import dev.sertan.android.rovercamera.ui.result.ResultFragment
import dev.sertan.android.rovercamera.ui.search.SearchFragment
import javax.inject.Inject

class MainFragmentFactory @Inject constructor(
    private val adapter: ResultAdapter
) : FragmentFactory() {
    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        return when (className) {
            SearchFragment::class.java.name -> SearchFragment()
            ResultFragment::class.java.name -> ResultFragment(adapter)
            else -> super.instantiate(classLoader, className)
        }
    }
}