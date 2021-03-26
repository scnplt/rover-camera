package dev.sertan.android.rovercamera.ui.result

import android.content.Intent
import android.net.Uri
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
    ) {
        val photo = photos[position]
        holder.bind.photo = photo
        holder.bind.resultFragmentRecyclerItemImg.loadAndStartAnimation(R.anim.recycler_item)
        holder.bind.resultFragmentRecyclerItemImg.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(photo.imgSrc))
            it.context.startActivity(intent)
        }
    }

    override fun onViewDetachedFromWindow(holder: BaseViewHolder<FragmentResultRecyclerItemBinding>) {
        super.onViewDetachedFromWindow(holder)
        holder.bind.resultFragmentRecyclerItemImg.clearAnimation()
    }

    override fun getItemCount(): Int = photos.size

    override fun getItemLayoutId(): Int = R.layout.fragment_result_recycler_item
}