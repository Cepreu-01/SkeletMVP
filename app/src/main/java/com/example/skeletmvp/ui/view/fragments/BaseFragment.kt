package com.example.skeletmvp.ui.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.example.skeletmvp.R

abstract class BaseFragment<VB:ViewBinding> : Fragment() {
    private var _binding:ViewBinding?=null
    abstract val bindingInflater:(LayoutInflater,ViewGroup?,Boolean) -> VB

    @Suppress("UNCHECKED_CAST")
    protected val binding: VB
        get() = _binding as VB

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = bindingInflater.invoke(inflater,container,false)
        return requireNotNull(binding).root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setup()
    }
    abstract fun setup()

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}