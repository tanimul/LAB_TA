package com.example.lab_ta.common.extention

import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.lab_ta.R

fun ImageView.loadImageFromDrawable(@DrawableRes aPlaceHolderImage: Int) {
    Glide.with(this.context).load(aPlaceHolderImage).diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
        .into(this)
}

@BindingAdapter("imageUrl")
fun ImageView.loadImageUrl(imageUrl: String?) {
    try {
        if (imageUrl != null) {
            Glide.with(this.context).load(imageUrl).placeholder(R.drawable.logo_placeholder)
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC).error(R.drawable.logo_placeholder)
                .into(this)
        } else {
            loadImageFromDrawable(R.drawable.logo_placeholder)
        }
    } catch (e: Exception) {
        e.printStackTrace()
    }

}
