package dev.sertan.android.rovercamera.ui.base

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

abstract class BaseAdapter<DB : ViewDataBinding> :
    RecyclerView.Adapter<BaseAdapter.BaseViewHolder<DB>>() {

    class BaseViewHolder<VDB : ViewDataBinding>(val bind: VDB) : RecyclerView.ViewHolder(bind.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<DB> {
        val inflater = LayoutInflater.from(parent.context)
        val bind = DataBindingUtil.inflate<DB>(inflater, getItemLayoutId(), parent, false)
        return BaseViewHolder(bind)
    }

    @LayoutRes
    abstract fun getItemLayoutId(): Int
}