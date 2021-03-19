package com.example.newsappcleanarch.ui.search

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.entity.Article
import com.example.newsappcleanarch.base.BaseFragment
import com.example.newsappcleanarch.databinding.FragmentSearchNewsBinding
import com.example.newsappcleanarch.ui.NewsAdapter
import com.example.newsappcleanarch.util.PagingLoadStateAdapter
import com.example.newsappcleanarch.util.onTextChange
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class SearchNewsFragment: BaseFragment<FragmentSearchNewsBinding>() ,NewsAdapter.OnBreakingListener{

    private val viewModel: SearchNewsViewModel by viewModels()
    private lateinit var adapter: NewsAdapter

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentSearchNewsBinding.inflate(inflater ,container ,false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = NewsAdapter(this)
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
                },2000)
            }
        }

    }

    private fun subscribeOnNewsList(){
        viewModel.searchNews.observe(viewLifecycleOwner){
            adapter.submitData(viewLifecycleOwner.lifecycle ,it)
        }
    }

    override fun onBreakingItemClick(item: Article) {

    }

}