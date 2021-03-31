package dev.sertan.android.rovercamera.ui.result

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.View
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import dev.sertan.android.rovercamera.R
import dev.sertan.android.rovercamera.data.model.Photo
import dev.sertan.android.rovercamera.databinding.FragmentResultRecyclerItemBinding
import dev.sertan.android.rovercamera.ui.base.BaseAdapter
import dev.sertan.android.rovercamera.util.extensions.loadAndStartAnimation
import javax.inject.Inject

class ResultAdapter @Inject
constructor() : BaseAdapter<FragmentResultRecyclerItemBinding>(), ResultRecyclerListener {

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
        listener = this@ResultAdapter
        resultFragmentRecyclerItemImg.loadAndStartAnimation(R.anim.recycler_item)
    }

    override fun onViewDetachedFromWindow(holder: BaseViewHolder<FragmentResultRecyclerItemBinding>) {
        super.onViewDetachedFromWindow(holder)
        holder.bind.resultFragmentRecyclerItemImg.clearAnimation()
    }

    override fun getItemCount(): Int = photos.size

    override fun getItemLayoutId(): Int = R.layout.fragment_result_recycler_item

    override fun photoClick(view: View, photo: Photo) {
        val title = view.context.getString(R.string.recycler_item_alert_title)
        val negativeText = view.context.getString(R.string.recycler_item_alert_negative_btn)
        val positiveText = view.context.getString(R.string.recycler_item_alert_positive_btn)
        val message = view.context.getString(
            R.string.recycler_item_alert_message,
            photo.camera.fullName,
            photo.earthDate,
            photo.rover.name
        )

        val alertDialog = AlertDialog.Builder(view.context)
            .setTitle(title)
            .setMessage(message)
            .setNegativeButton(negativeText) { dialog, _ -> dialog.cancel() }
            .setPositiveButton(positiveText) { dialog, _ ->
                openWithBrowser(view.context, photo.imgSrc)
                dialog.cancel()
            }

        alertDialog.create().show()
    }

    private fun openWithBrowser(context: Context, url: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        context.startActivity(intent)
    }
}