package com.example.lab_ta.presentations.fragments.dashboard.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.lab_ta.databinding.ItemContentBinding
import com.example.lab_ta.presentations.fragments.dashboard.domain.model.Content
import com.example.lab_ta.presentations.fragments.dashboard.domain.model.Contents
import kotlinx.coroutines.ExperimentalCoroutinesApi
import timber.log.Timber

@OptIn(ExperimentalCoroutinesApi::class)
class ContentAdapter() :
    ListAdapter<Contents, ContentAdapter.ContentViewHolder>(DiffCallBack()) {
    class ContentViewHolder(val binding: ItemContentBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind(data: Contents) {
            binding.item = data.content
        }

    }

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): ContentViewHolder {
        return ContentViewHolder(
            ItemContentBinding.inflate(
                LayoutInflater.from(parent.context)
            )
        )
    }

    override fun onBindViewHolder(holder: ContentViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }


    private class DiffCallBack : DiffUtil.ItemCallback<Contents>() {
        override fun areItemsTheSame(oldItem: Contents, newItem: Contents): Boolean =
            oldItem == newItem

        override fun areContentsTheSame(oldItem: Contents, newItem: Contents): Boolean =
            oldItem == newItem
    }
}

@OptIn(ExperimentalCoroutinesApi::class)
@BindingAdapter(value = ["bindContent"], requireAll = true)
fun RecyclerView.bindContent(
    data: List<Contents>?
) {
    if (adapter == null) adapter = ContentAdapter()
    val value = data ?: emptyList()
    val adapter = adapter as? ContentAdapter
    adapter?.submitList(value)

}