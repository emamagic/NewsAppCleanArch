package com.example.newsappcleanarch.ui.save

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.entity.Article
import com.example.newsappcleanarch.base.BaseFragment
import com.example.newsappcleanarch.databinding.FragmentSavedNewsBinding
import com.example.newsappcleanarch.ui.NewsAdapterPaging
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SavedNewsFragment: BaseFragment<FragmentSavedNewsBinding>() ,SavedNewsAdapter.Interaction {

    private val viewModel: SaveNewsViewModel by viewModels()
    private lateinit var adapter: SavedNewsAdapter

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentSavedNewsBinding.inflate(inflater ,container ,false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = SavedNewsAdapter(this)
        subscribeOnSavedNews()

        val itemTouchHelperCallback = object : ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.UP or ItemTouchHelper.DOWN ,
            ItemTouchHelper.LEFT or ItemTouchHelper.DOWN){
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ) = true

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.bindingAdapterPosition
                val article = adapter.differ.currentList[position]
                viewModel.deleteNewsArticle(article)
                Snackbar.make(view ,"Successfully deleted article" ,Snackbar.LENGTH_LONG).apply {
                    setAction("Undo"){
                        viewModel.upsertNewsArticle(article)
                    }
                    show()
                }

            }

        }

        ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(binding?.rvSavedNews)

    }

    private fun subscribeOnSavedNews(){
        viewModel.getAllNews().observe(viewLifecycleOwner){
            adapter.submitList(it)
        }
    }

    override fun onItemSelected(item: Article) {
        val action = SavedNewsFragmentDirections.actionSavedNewsFragment2ToArticleNewsFragment(item)
        findNavController().navigate(action)
    }


}