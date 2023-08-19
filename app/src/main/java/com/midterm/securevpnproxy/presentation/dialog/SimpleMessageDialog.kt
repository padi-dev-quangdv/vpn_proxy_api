package com.midterm.securevpnproxy.presentation.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import com.midterm.securevpnproxy.R
import com.midterm.securevpnproxy.databinding.DialogSimpleMessageBinding

class SimpleMessageDialog(
    private val title: String? = null,
    private val description: String,
    private val cancellable: Boolean = true,
) : DialogFragment() {
    private lateinit var binding: DialogSimpleMessageBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DialogSimpleMessageBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        dialog?.setCancelable(cancellable)
        dialog?.setCanceledOnTouchOutside(cancellable)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initViewListener()
    }

    private fun initViewListener() {
        binding.btnGotIt.setOnClickListener { dismiss() }
    }

    private fun initView() {
        binding.title = title ?: getString(R.string.app_name)
        binding.description = description
    }
}