package com.example.newsappcleanarch.ui.save

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.newsappcleanarch.base.BaseFragment
import com.example.newsappcleanarch.databinding.FragmentSavedNewsBinding

class SavedNewsFragment: BaseFragment<FragmentSavedNewsBinding>() {

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentSavedNewsBinding.inflate(inflater ,container ,false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }


}