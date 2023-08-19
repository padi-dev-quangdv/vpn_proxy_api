package com.midterm.securevpnproxy.base

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.reflect.ParameterizedType

abstract class BaseActivity<BINDING : ViewDataBinding, VM : ViewModel>(val layoutId: Int) :
    AppCompatActivity(), View.OnClickListener {

    protected lateinit var binding: BINDING

    protected val viewModel: VM by lazy { initViewModel() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, layoutId)
        initView()
        initObserver()
        initViewListener()
        initData()
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

    override fun onClick(v: View?) {
        v?.let { onViewClicked(it) }
    }

    open fun onViewClicked(view: View) {

    }

    abstract fun initData()

    abstract fun initViewListener()

    abstract fun initObserver()

    abstract fun initView()
}