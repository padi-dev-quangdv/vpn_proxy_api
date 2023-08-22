package com.midterm.securevpnproxy.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import com.midterm.securevpnproxy.base.compose.AppTheme

abstract class BaseComposeFragment<VB : ViewDataBinding, VM : ViewModel>
constructor(
    @LayoutRes private val layoutId2: Int
) : BaseFragment<VB, VM>(layoutId2) {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = super.onCreateView(inflater, container, savedInstanceState)
        initMainComposeView()
        return view
    }

    private fun initMainComposeView() {
        getMainComposeView().apply {
            setDisposeStrategy()
            setContent {
                AppTheme {
                    MainComposeViewContent()
                }
            }
        }
    }

    private fun ComposeView.setDisposeStrategy() {
        setViewCompositionStrategy(
            ViewCompositionStrategy.DisposeOnLifecycleDestroyed(
                viewLifecycleOwner
            )
        )
    }

    protected abstract fun getMainComposeView(): ComposeView

    @Composable
    protected open fun MainComposeViewContent() {
        // You will need to add @Composable when trying to override this func.
    }

    override fun initViewListener() {

    }

    override fun initObserver() {

    }
}