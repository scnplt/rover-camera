package dev.sertan.android.rovercamera.ui.search

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import dev.sertan.android.rovercamera.R
import dev.sertan.android.rovercamera.databinding.FragmentSearchBinding
import dev.sertan.android.rovercamera.ui.base.BaseFragment

@AndroidEntryPoint
class SearchFragment : BaseFragment<FragmentSearchBinding>() {
    private val viewModel: SearchViewModel by viewModels()

    override fun getLayoutId(): Int = R.layout.fragment_search

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.model = viewModel
    }
}