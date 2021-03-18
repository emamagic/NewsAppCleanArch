package com.example.newsappcleanarch.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.domain.entity.Article
import com.example.newsappcleanarch.databinding.ItemArticlePreviewBinding

class NewsAdapter(private val interaction: OnBreakingListener? = null) :
    PagingDataAdapter<Article, NewsAdapter.BreakingNewsViewHolder>(DIFF_CALLBACK) {

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Article>() {

            override fun areItemsTheSame(oldItem: Article, newItem: Article) = oldItem.url == newItem.url

            override fun areContentsTheSame(oldItem: Article, newItem: Article) = oldItem == newItem

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BreakingNewsViewHolder {
        val binding =
            ItemArticlePreviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BreakingNewsViewHolder(binding ,interaction)
    }

    override fun onBindViewHolder(holder: BreakingNewsViewHolder, position: Int) {
        val currentItem = getItem(position)
        if (currentItem != null) holder.bind(currentItem)
    }

    class BreakingNewsViewHolder
    constructor(
        private val binding: ItemArticlePreviewBinding,
        private val interaction: NewsAdapter.OnBreakingListener?
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Article) = binding.apply {
            root.setOnClickListener {
                interaction?.onBreakingItemClick(adapterPosition, item)
            }
            Glide.with(root).load(item.urlToImage).into(ivArticleImage)
            tvSource.text = item.source.name
            tvTitle.text = item.title
            tvDescription.text = item.description
            tvPublishedAt.text = item.publishedAt
        }
    }


//    private var onItemClickListener: ((Article) -> Unit)? = null
//    fun setOnItemClickListener(listener: (Article) -> Unit){
//        onItemClickListener = listener
//    }


    interface OnBreakingListener {
        fun onBreakingItemClick(position: Int, item: Article)
    }


}
