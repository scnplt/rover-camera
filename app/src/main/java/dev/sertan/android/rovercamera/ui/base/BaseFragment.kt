package dev.sertan.android.rovercamera.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

abstract class BaseFragment<DB : ViewDataBinding> : Fragment() {
    var binding: DB? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false)
        binding!!.lifecycleOwner = viewLifecycleOwner
        return binding!!.root
    }

    @LayoutRes
    abstract fun getLayoutId(): Int

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }
}