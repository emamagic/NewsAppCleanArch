package com.example.newsappcleanarch.ui.`break`

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.newsappcleanarch.base.BaseFragment
import com.example.newsappcleanarch.databinding.FragmentBreakingNewsBinding

class BreakingNewsFragment: BaseFragment<FragmentBreakingNewsBinding>() {

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentBreakingNewsBinding.inflate(inflater ,container ,false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }


}