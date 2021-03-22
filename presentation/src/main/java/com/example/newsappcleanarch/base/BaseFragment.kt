package com.example.newsappcleanarch.base

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.viewbinding.ViewBinding
import com.example.commen.ApiWrapper
import com.example.newsappcleanarch.R
import com.example.newsappcleanarch.util.ConnectionLiveData
import com.example.newsappcleanarch.util.toasty
import java.util.*

abstract class BaseFragment<VB: ViewBinding>: Fragment() {

  //  protected val baseViewModel: VM by lazy { ViewModelProvider(this).get(getMyViewModel()) }
    private  var _binding: VB? = null
    protected val binding get() = _binding
    private lateinit var loading: FrameLayout
    private var callback: OnBackPressedCallback? = null
    lateinit var connectionLiveData: ConnectionLiveData

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = getFragmentBinding(inflater ,container)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loading = binding?.root?.rootView?.findViewById(R.id.my_loading)!!
    }

    protected fun showLoading(isDim: Boolean = false){
        if (loading.visibility != View.VISIBLE){
            if (isDim) loading.setBackgroundColor(Color.parseColor("#cc000000"))
            loading.visibility = View.VISIBLE
        }
    }

    protected fun hideLoading(){
        if (loading.visibility != View.GONE){
            loading.visibility = View.GONE
        }
    }

    protected inline fun<T> api(response: ApiWrapper<T>, hideable: Boolean = true, call: (T?) -> Unit){
        if (hideable)hideLoading()
        when(response){
            is ApiWrapper.Success -> { call(response.data) }
            is ApiWrapper.ApiError -> {
                Log.e("TAG", "onViewCreated: ${response.message}", )
                toasty(R.string.toasty_error)
            }
            is ApiWrapper.UnknownError -> {
                Log.e("TAG", "onViewCreated: ${response.message}", )
                toasty(R.string.toasty_error)
            }
            is ApiWrapper.NetworkError -> {
                Log.e("TAG", "onViewCreated: net ${response.message}", )
                toasty(R.string.toasty_net)
            }
            else -> {
                // is ApiWrapper.Loading
                if (response.isDim) showLoading(true)
                else showLoading()
            }
        }
    }

    protected inline fun subscribeOnNetwork(crossinline call: (Boolean) -> Unit){
        connectionLiveData = ConnectionLiveData(requireContext())
        connectionLiveData.observe(viewLifecycleOwner){ isNetworkAvailable ->
            call(isNetworkAvailable)
        }
    }

    abstract fun getFragmentBinding(inflater: LayoutInflater ,container: ViewGroup?): VB

 //   abstract fun getMyViewModel(): Class<VM>

    fun onMyBackPressed(owner: LifecycleOwner, call: () -> Unit) {
        callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                call()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(owner, callback!!)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        if (callback != null){
            callback?.isEnabled = false
            callback?.remove()
        }
    }

}

