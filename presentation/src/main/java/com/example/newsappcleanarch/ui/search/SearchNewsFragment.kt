package com.example.newsappcleanarch.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.newsappcleanarch.base.BaseFragment
import com.example.newsappcleanarch.databinding.FragmentSearchNewsBinding

class SearchNewsFragment: BaseFragment<FragmentSearchNewsBinding>() {

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentSearchNewsBinding.inflate(inflater ,container ,false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }


}