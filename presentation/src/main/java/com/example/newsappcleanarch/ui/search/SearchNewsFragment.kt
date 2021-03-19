package com.example.newsappcleanarch.ui.search

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.domain.entity.Article
import com.example.newsappcleanarch.base.BaseFragment
import com.example.newsappcleanarch.databinding.FragmentSearchNewsBinding
import com.example.newsappcleanarch.ui.NewsAdapterPaging
import com.example.newsappcleanarch.util.PagingLoadStateAdapter
import com.example.newsappcleanarch.util.onTextChange
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class SearchNewsFragment: BaseFragment<FragmentSearchNewsBinding>() ,NewsAdapterPaging.OnBreakingListener{

    private val viewModel: SearchNewsViewModel by viewModels()
    private lateinit var adapter: NewsAdapterPaging

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentSearchNewsBinding.inflate(inflater ,container ,false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = NewsAdapterPaging(this)
        subscribeOnNewsList()
        binding?.apply {
            rvSearchNews.setHasFixedSize(true)
            rvSearchNews.adapter = adapter.withLoadStateHeaderAndFooter(
                header = PagingLoadStateAdapter { adapter.retry() },
                footer = PagingLoadStateAdapter { adapter.retry() }
            )
            etSearch.onTextChange {
                Timer().schedule(object : TimerTask(){
                    override fun run() {
                        Handler(Looper.getMainLooper()).post {
                            viewModel.searchQuery(it)
                        }
                    }
                },1000)
            }
        }

    }

    private fun subscribeOnNewsList(){
        viewModel.searchNews.observe(viewLifecycleOwner){
            adapter.submitData(viewLifecycleOwner.lifecycle ,it)
        }
    }

    override fun onBreakingItemClick(item: Article) {
        val action = SearchNewsFragmentDirections.actionSearchNewsFragment2ToArticleNewsFragment(item)
        findNavController().navigate(action)
    }

}