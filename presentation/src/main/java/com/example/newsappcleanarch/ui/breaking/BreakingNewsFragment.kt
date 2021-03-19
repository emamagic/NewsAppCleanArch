package com.example.newsappcleanarch.ui.breaking

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.domain.entity.Article
import com.example.newsappcleanarch.R
import com.example.newsappcleanarch.base.BaseFragment
import com.example.newsappcleanarch.databinding.FragmentBreakingNewsBinding
import com.example.newsappcleanarch.ui.NewsAdapter
import com.example.newsappcleanarch.util.PagingLoadStateAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BreakingNewsFragment: BaseFragment<FragmentBreakingNewsBinding>() ,NewsAdapter.OnBreakingListener{

    private val viewModel: BreakingNewsViewModel by viewModels()
    private lateinit var adapter: NewsAdapter

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentBreakingNewsBinding.inflate(inflater ,container ,false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = NewsAdapter(this)
        subscribeOnBreakingNews()
        binding?.apply {
            rvBreakingNews.setHasFixedSize(true)
            rvBreakingNews.adapter = adapter.withLoadStateHeaderAndFooter(
                header = PagingLoadStateAdapter { adapter.retry() },
                footer = PagingLoadStateAdapter { adapter.retry() }
            )
        }

    }

    private fun subscribeOnBreakingNews(){
        viewModel.breakingNewsList.observe(viewLifecycleOwner){
            adapter.submitData(viewLifecycleOwner.lifecycle ,it)
        }
    }

    override fun onBreakingItemClick(item: Article) {
        val action = BreakingNewsFragmentDirections.actionBreakingNewsFragmentToArticleNewsFragment(item)
        findNavController().navigate(action)
    }


}