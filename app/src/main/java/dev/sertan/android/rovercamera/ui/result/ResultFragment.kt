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
import javax.inject.Inject

@AndroidEntryPoint
class ResultFragment : BaseFragment<FragmentResultBinding>() {
    @Inject
    lateinit var adapter: ResultAdapter
    private val args: ResultFragmentArgs by navArgs()
    private val viewModel: ResultViewModel by viewModels()

    override fun getLayoutId(): Int = R.layout.fragment_result

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.model = viewModel

        search()
        setupListeners()
        setupRecyclerViewAdapter()
    }

    private fun search() = viewModel.search(args.sol, args.earthDate, args.camera, args.dateType)

    private fun setupRecyclerViewAdapter() = with(binding?.resultFragmentRecyclerView) {
        this?.layoutManager = GridLayoutManager(requireContext(), 3)
        this?.adapter = adapter
    }

    private fun setupListeners() {
        viewModel.stateLiveData.observe(viewLifecycleOwner) {
            if (it is State.LOADED<*>) adapter.photos = (it.data as Node).photos
        }
        binding?.resultFragmentSwipeRefresh?.setOnRefreshListener { search() }
    }
}