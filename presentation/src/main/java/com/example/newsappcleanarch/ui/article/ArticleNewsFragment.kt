package com.example.newsappcleanarch.ui.article

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.newsappcleanarch.base.BaseFragment
import com.example.newsappcleanarch.databinding.FragmentArticleBinding
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class ArticleNewsFragment: BaseFragment<FragmentArticleBinding>() {

    private val viewModel: ArticleNewsViewModel by viewModels()
    private val args: ArticleNewsFragmentArgs by navArgs()

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentArticleBinding.inflate(inflater ,container ,false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.apply {

            webView.apply {
                webViewClient = WebViewClient()
                loadUrl(args.article.url)
            }

            fab.setOnClickListener {
                viewModel.upsertArticle(args.article)
            }


        }

    }



}