package dev.sertan.android.rovercamera.ui.base

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

abstract class BaseAdapter<VDB : ViewDataBinding> :
    RecyclerView.Adapter<BaseAdapter.BaseViewHolder<VDB>>(){

    class BaseViewHolder<VDB: ViewDataBinding>(val bind: VDB) : RecyclerView.ViewHolder(bind.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<VDB> {
        val inflater = LayoutInflater.from(parent.context)
        val bind = DataBindingUtil.inflate<VDB>(inflater, getItemLayoutId(), parent, false)
        return BaseViewHolder(bind)
    }

    @LayoutRes
    abstract fun getItemLayoutId(): Int
}