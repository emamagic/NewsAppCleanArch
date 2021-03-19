package com.example.newsappcleanarch.base

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.viewbinding.ViewBinding
import com.example.commen.ApiWrapper
import com.example.newsappcleanarch.R
import java.util.*

abstract class BaseFragment<VB: ViewBinding>: Fragment() {

  //  protected val baseViewModel: VM by lazy { ViewModelProvider(this).get(getMyViewModel()) }
    private  var _binding: VB? = null
    protected val binding get() = _binding
    private lateinit var loading: FrameLayout
    private var callback: OnBackPressedCallback? = null
    private var timer: Timer? = null

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
                toastError()
            }
            is ApiWrapper.UnknownError -> {
                Log.e("TAG", "onViewCreated: ${response.message}", )
                toastError()
            }
            is ApiWrapper.NetworkError -> {
                Log.e("TAG", "onViewCreated: net ${response.message}", )
                toastNet()
            }
            else -> {
                // is ApiWrapper.Loading
                if (response.isDim) showLoading(true)
                else showLoading()
            }
        }
    }

    @SuppressLint("ResourceAsColor")
    protected fun toasty(title: String, selectedMode: Int? = null) {
        val layout = layoutInflater.inflate(
            R.layout.toast_layout,
            requireView().findViewById(R.id.toast_root)
        )
        when (selectedMode) {

            MODE_TOAST_SUCCESS -> {
                layout.findViewById<ImageView>(R.id.toast_img)
                    .setImageResource(R.drawable.ic_corroct_toast)
                layout.findViewById<ConstraintLayout>(R.id.toast_root)
                    .setBackgroundResource(R.drawable.bg_corroct_toast)
            }
            MODE_TOAST_WARNING -> {
                layout.findViewById<ImageView>(R.id.toast_img)
                    .setImageResource(R.drawable.ic_warning_toast)
                layout.findViewById<ConstraintLayout>(R.id.toast_root)
                    .setBackgroundResource(R.drawable.bg_warning_toast)
                layout.findViewById<TextView>(R.id.toast_txt).setTextColor(R.color.black)
            }
            MODE_TOAST_ERROR -> {
                layout.findViewById<ImageView>(R.id.toast_img)
                    .setImageResource(R.drawable.ic_error_toast)
                layout.findViewById<ConstraintLayout>(R.id.toast_root)
                    .setBackgroundResource(R.drawable.bg_error_toast)
            }
            else -> {
                Toast.makeText(requireContext(), title, Toast.LENGTH_LONG).show()
            }

        }

        layout.findViewById<TextView>(R.id.toast_txt).text = title
        if(selectedMode!=null){
            Toast(requireActivity()).apply {
                setGravity(Gravity.BOTTOM, 0, 100)
                duration = Toast.LENGTH_LONG
                view = layout
            }.show()
        }
    }

    protected fun toastNet(text: String = getString(R.string.toasty_net)) {
        toasty(text , MODE_TOAST_WARNING)
    }

    protected fun toastError(text: String = getString(R.string.toasty_error)) {
        toasty(text , MODE_TOAST_ERROR)
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

    companion object {
        const val MODE_TOAST_SUCCESS = 1
        const val MODE_TOAST_WARNING = 2
        const val MODE_TOAST_ERROR = 3
    }

}

