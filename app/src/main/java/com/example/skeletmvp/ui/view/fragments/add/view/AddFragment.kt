package com.example.skeletmvp.ui.view.fragments.add.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.example.skeletmvp.R
import com.example.skeletmvp.databinding.FragmentAddBinding
import com.example.skeletmvp.repository.room.model.UserWithAddress
import com.example.skeletmvp.ui.view.fragments.base.BaseFragment
import com.example.skeletmvp.utils.ADD_USER
import kotlinx.android.synthetic.main.fragment_add.*

class AddFragment : BaseFragment<FragmentAddBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentAddBinding
        get() = FragmentAddBinding::inflate

    override fun setupViews() {
        btn_ok.setOnClickListener {
            val bundle = createUser()
            findNavController().navigate(R.id.action_addFragment_to_mainnFragment,bundle)
        }
    }

    private fun createUser(): Bundle {
        val userName = binding.edtName.text.toString()
        val userAddress = binding.edtAddress.text.toString()

        val user = UserWithAddress(0, userName, 0, userAddress)
        return bundleOf(ADD_USER to user)
    }


}