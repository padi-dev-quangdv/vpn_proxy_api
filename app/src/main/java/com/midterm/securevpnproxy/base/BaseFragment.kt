package com.midterm.securevpnproxy.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.reflect.ParameterizedType

abstract class BaseFragment<BINDING : ViewDataBinding, VM : ViewModel>(val layoutId: Int) : Fragment() {

    protected lateinit var binding: BINDING

    protected val viewModel: VM by lazy { initViewModel() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        return binding.root
    }

    private fun initViewModel(): VM {
        return ViewModelProvider(this)[getViewModelClass()]
    }

    protected open fun getViewModelClass(): Class<VM> {
        var genericSuperClass = javaClass.genericSuperclass
        var parametrizedType: ParameterizedType? = null

        //Try to find generic from current class first, if can not find up into super class to find
        while (parametrizedType == null) {
            if (genericSuperClass is ParameterizedType) {
                parametrizedType = genericSuperClass
            } else {
                genericSuperClass = (genericSuperClass as Class<*>).genericSuperclass
            }
        }

        return parametrizedType.actualTypeArguments[1] as Class<VM>
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initObserver()
        initViewListener()
        initData()
    }

    abstract fun initData()

    abstract fun initViewListener()

    abstract fun initObserver()

    abstract fun initView()

    protected fun showKeyBoard(view: View) {
        val manager: InputMethodManager =
            activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        manager.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT)
    }

    protected fun hideKeyBoard(view: View) {
        val manager: InputMethodManager =
            activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        manager.hideSoftInputFromWindow(view.applicationWindowToken, 0)
    }
}