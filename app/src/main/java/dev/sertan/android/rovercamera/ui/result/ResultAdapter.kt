package dev.sertan.android.rovercamera.ui.result

import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import dev.sertan.android.rovercamera.R
import dev.sertan.android.rovercamera.data.model.Photo
import dev.sertan.android.rovercamera.databinding.FragmentResultRecyclerItemBinding
import dev.sertan.android.rovercamera.ui.base.BaseAdapter
import dev.sertan.android.rovercamera.util.extensions.loadAndStartAnimation
import javax.inject.Inject

class ResultAdapter @Inject
constructor() : BaseAdapter<FragmentResultRecyclerItemBinding>() {

    private val diffCallback = object : DiffUtil.ItemCallback<Photo>() {
        override fun areItemsTheSame(oldItem: Photo, newItem: Photo): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Photo, newItem: Photo): Boolean =
            oldItem.imgSrc == newItem.imgSrc
    }

    private val recyclerListDiffer = AsyncListDiffer(this, diffCallback)

    var photos: List<Photo>
        get() = recyclerListDiffer.currentList
        set(value) = recyclerListDiffer.submitList(value)

    override fun onBindViewHolder(
        holder: BaseViewHolder<FragmentResultRecyclerItemBinding>,
        position: Int
    ) = with(holder.bind) {
        photo = photos[position]
        listener = ResultListener
        resultFragmentRecyclerItemImg.loadAndStartAnimation(R.anim.recycler_item)
    }

    override fun onViewDetachedFromWindow(holder: BaseViewHolder<FragmentResultRecyclerItemBinding>) {
        super.onViewDetachedFromWindow(holder)
        holder.bind.resultFragmentRecyclerItemImg.clearAnimation()
    }

    override fun getItemCount(): Int = photos.size

    override fun getItemLayoutId(): Int = R.layout.fragment_result_recycler_item
}