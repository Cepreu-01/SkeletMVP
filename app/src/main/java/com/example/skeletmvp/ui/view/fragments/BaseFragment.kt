package com.example.skeletmvp.ui.view.fragments

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import com.example.skeletmvp.R
import com.example.skeletmvp.databinding.FragmentAddBinding
import com.example.skeletmvp.databinding.FragmentMainnBinding
import com.example.skeletmvp.databinding.FragmentUpdateBinding

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

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        when(binding){
            is FragmentMainnBinding  -> menu.add(0,1,0,R.string.menu_add)
            is FragmentUpdateBinding -> menu.add(0,2,0,R.string.menu_update)
            is FragmentAddBinding    -> menu.add(0,3,0,R.string.menu_ok)
        }
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            1 -> findNavController().navigate(R.id.action_mainnFragment_to_addFragment)
            2 -> findNavController().navigate(R.id.action_updateFragment_to_mainnFragment)
            3 -> findNavController().navigate(R.id.action_addFragment_to_mainnFragment)
        }
        return super.onOptionsItemSelected(item)
    }
}