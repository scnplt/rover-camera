package dev.sertan.android.rovercamera.ui.result

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import dev.sertan.android.rovercamera.R
import dev.sertan.android.rovercamera.data.model.Node
import dev.sertan.android.rovercamera.databinding.FragmentResultBinding
import dev.sertan.android.rovercamera.ui.base.BaseFragment
import dev.sertan.android.rovercamera.util.State

@AndroidEntryPoint
class ResultFragment(private val adapter: ResultAdapter) : BaseFragment<FragmentResultBinding>() {
    private val args: ResultFragmentArgs by navArgs()
    private val viewModel: ResultViewModel by viewModels()

    override fun getLayoutId(): Int = R.layout.fragment_result

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.model = viewModel

        search()
        setupListener()
        setupRecyclerViewAdapter()
    }

    private fun search() = viewModel.search(args.sol, args.earthDate, camera = args.camera)

    private fun setupRecyclerViewAdapter() {
        binding?.resultFragmentRecyclerView?.layoutManager = GridLayoutManager(requireContext(), 3)
        binding?.resultFragmentRecyclerView?.adapter = adapter

    }

    private fun setupListener() = viewModel.stateLiveData.observe(viewLifecycleOwner) {
        if (it is State.LOADED<*>) adapter.photos = (it.data as Node).photos
    }

}