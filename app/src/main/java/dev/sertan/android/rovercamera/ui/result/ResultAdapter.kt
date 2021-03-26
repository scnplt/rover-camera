package dev.sertan.android.rovercamera.ui.result

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import dev.sertan.android.rovercamera.R
import dev.sertan.android.rovercamera.data.model.Photo
import dev.sertan.android.rovercamera.databinding.FragmentResultRecyclerItemBinding
import javax.inject.Inject

class ResultAdapter @Inject constructor(): RecyclerView.Adapter<ResultAdapter.ResultViewHolder>() {

    private val diffUtil = object : DiffUtil.ItemCallback<Photo>() {
        override fun areItemsTheSame(oldItem: Photo, newItem: Photo): Boolean = oldItem == newItem
        override fun areContentsTheSame(oldItem: Photo, newItem: Photo): Boolean =
            oldItem.imgSrc == newItem.imgSrc
    }

    private val recyclerListDiffer = AsyncListDiffer(this, diffUtil)

    var photos: List<Photo>
        get() = recyclerListDiffer.currentList
        set(value) = recyclerListDiffer.submitList(value)

    class ResultViewHolder(val bind: FragmentResultRecyclerItemBinding) :
        RecyclerView.ViewHolder(bind.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResultViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil
            .inflate<FragmentResultRecyclerItemBinding>(
                inflater,
                R.layout.fragment_result_recycler_item,
                parent,
                false
            )
        return ResultViewHolder(view)
    }

    override fun onBindViewHolder(holder: ResultViewHolder, position: Int) {
        holder.bind.photo = photos[position]
    }

    override fun getItemCount(): Int = photos.size
}