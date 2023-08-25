package com.midterm.securevpnproxy.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy.*
import androidx.compose.ui.unit.dp
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.midterm.securevpnproxy.base.compose.AppTheme
import com.midterm.securevpnproxy.base.compose.HandleEffect
import com.midterm.securevpnproxy.base.compose.LocalColors
import com.midterm.securevpnproxy.base.compose.noRippleClickable

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import timber.log.Timber

abstract class BaseComposeFragment<VB : ViewDataBinding, VM : ViewModel>
constructor(
    @LayoutRes private val layoutId2: Int,
) : BaseFragment<VB, VM>(layoutId2) {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
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
                    Surface(modifier = Modifier.background(color = LocalColors.current.white)) {
                        ListenEffect()
                        MainComposeViewContent(modifier = Modifier)
                        ListenLoading(modifier = Modifier
                            .fillMaxSize()
                            .noRippleClickable { }
                        )
                    }
                }
            }
        }
    }


    @Composable
    private fun ListenEffect() {
        val handleEffect = this as? HandleEffect<BaseViewEffect>
        handleEffect ?: return

        val effectState by handleEffect.provideEffectFlow.collectAsStateWithLifecycle(
            initialValue = handleEffect.defaultEffectValue
        )
        LaunchedEffect(effectState) {
            handleEffect.onEffectTriggered(effectState)
        }
    }

    @Composable
    protected fun ListenLoading(
        modifier: Modifier = Modifier,
    ) {
        val loading by loadingState.collectAsStateWithLifecycle(initialValue = false)
        AnimatedVisibility(visible = loading, enter = fadeIn(), exit = fadeOut()) {
            Box(
                modifier = modifier
                    .background(color = Color.Black.copy(alpha = 0.4f))
            ) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .clip(RoundedCornerShape(4.dp))
                        .background(color = Color.White)
                        .align(Alignment.Center)
                        .padding(16.dp)
                        .size(40.dp)
                )
            }
        }

    }

    private fun ComposeView.setDisposeStrategy() {
        setViewCompositionStrategy(DisposeOnLifecycleDestroyed(viewLifecycleOwner))
    }

    protected abstract fun getMainComposeView(): ComposeView

    protected open val loadingView: View? = null

    protected open val loadingState: Flow<Boolean> = flowOf(false)

    @Composable
    protected open fun MainComposeViewContent(modifier: Modifier) {
        // You will need to add @Composable when trying to override this func.
    }

    override fun initViewListener() {

    }

    override fun initObserver() {

    }
}