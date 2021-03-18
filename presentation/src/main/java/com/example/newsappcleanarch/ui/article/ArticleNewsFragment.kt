package com.example.newsappcleanarch.ui.article

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.newsappcleanarch.base.BaseFragment
import com.example.newsappcleanarch.databinding.FragmentArticleBinding

class ArticleNewsFragment: BaseFragment<FragmentArticleBinding>() {


    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentArticleBinding.inflate(inflater ,container ,false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }



}