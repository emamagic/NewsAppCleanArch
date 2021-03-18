package com.example.newsappcleanarch

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.plusAssign
import androidx.navigation.ui.setupWithNavController
import com.example.newsappcleanarch.databinding.ActivityMainBinding
import com.example.newsappcleanarch.util.KeepStateNavigator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        setUpSaveStateBottomSheet()

    }

    @SuppressLint("RestrictedApi")
    private fun setUpSaveStateBottomSheet(){
        val navController = findNavController(R.id.news_nav_host_fragment)
        // get fragment
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.news_nav_host_fragment)  as NavHostFragment
        // setup custom navigator
        val navigator = KeepStateNavigator(this, navHostFragment.childFragmentManager, R.id.news_nav_host_fragment)
        navController.navigatorProvider += navigator
        // set navigation graph
        navController.setGraph(R.navigation.nav_graph)
        binding.newsBottomNav.setupWithNavController(navController)
    }

}