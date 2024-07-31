package com.example.lab_ta.common.extention

import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.databinding.BindingAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.lab_ta.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

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

inline fun Fragment.launchAndRepeatWithViewLifecycle(
    minActiveState: Lifecycle.State = Lifecycle.State.STARTED,
    crossinline block: suspend CoroutineScope.() -> Unit
) {
    viewLifecycleOwner.lifecycleScope.launch {
        viewLifecycleOwner.repeatOnLifecycle(minActiveState) {
            block()
        }
    }
}
inline fun Fragment.launchWhenCreatedLifecycle(
    minActiveState: Lifecycle.State = Lifecycle.State.CREATED,
    crossinline block: suspend CoroutineScope.() -> Unit
) {
    viewLifecycleOwner.lifecycleScope.launch {
        viewLifecycleOwner.repeatOnLifecycle(minActiveState) {
            block()
        }
    }
}

