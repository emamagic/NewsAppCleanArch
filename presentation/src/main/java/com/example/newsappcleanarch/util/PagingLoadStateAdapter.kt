package com.example.newsappcleanarch.util

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.newsappcleanarch.databinding.PagingLoadStateBinding

class PagingLoadStateAdapter(private val retry: ()-> Unit): LoadStateAdapter<PagingLoadStateAdapter.LoadStateViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoadStateViewHolder {
        val binding = PagingLoadStateBinding.inflate(LayoutInflater.from(parent.context) ,parent ,false)
        return LoadStateViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LoadStateViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    inner class LoadStateViewHolder(private val binding: PagingLoadStateBinding):
        RecyclerView.ViewHolder(binding.root){

            init {
                binding.buttonRetry.setOnClickListener {
                    retry.invoke()
                }
            }

            fun bind(loadState: LoadState){
                binding.apply {
                    progressBar.isVisible = loadState is LoadState.Loading
                    buttonRetry.isVisible = loadState !is LoadState.Loading
                    textViewError.isVisible = loadState !is LoadState.Loading
                }
            }
        }

}