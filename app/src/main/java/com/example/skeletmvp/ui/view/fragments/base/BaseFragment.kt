package com.example.skeletmvp.ui.view.fragments.base

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import com.example.skeletmvp.R
import com.example.skeletmvp.databinding.FragmentCoordinatorBinding


abstract class BaseFragment<VB:ViewBinding> : Fragment() {
    private var _binding:ViewBinding?=null
    abstract val bindingInflater:(LayoutInflater,ViewGroup?,Boolean) -> VB
    private var navController:NavController?=null

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
        setupViews()
        navController = findNavController()
    }
    abstract fun setupViews()

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
        navController = null
    }
    override fun onStart() {
        super.onStart()
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        when(binding){
            //is FragmentCoordinatorBinding  -> menu.add(0,1,0, R.string.exit)
        }
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            //1 -> navController?.navigate(R.id.action_coordinatorFragment_to_authFragment)
        }
        return super.onOptionsItemSelected(item)
    }

}