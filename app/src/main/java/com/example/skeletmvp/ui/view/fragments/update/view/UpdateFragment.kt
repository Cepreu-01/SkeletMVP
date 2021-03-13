package com.example.skeletmvp.ui.view.fragments.update.view


import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.example.skeletmvp.R
import com.example.skeletmvp.databinding.FragmentUpdateBinding
import com.example.skeletmvp.repository.room.model.UserWithAddress
import com.example.skeletmvp.ui.view.fragments.base.BaseFragment
import com.example.skeletmvp.utils.UPDATE_USER


class UpdateFragment : BaseFragment<FragmentUpdateBinding>() {
    var userId:Int?=null
    var addressId:Int?=null

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentUpdateBinding
        get() = FragmentUpdateBinding::inflate

    override fun setupViews() {
        binding.btnOk.setOnClickListener {
            if (binding.edtAddress.text.isNotEmpty() && binding.edtName.text.isNotEmpty()){
                getUserInfo()
            }
        }
    }

    private fun getUserInfo() {
        val userId = this.userId
        val userName = binding.edtName.text.toString()
        val addressId = this.addressId
        val address = binding.edtAddress.text.toString()
        if (userId != null && addressId != null) {
            val bundle = createUserToUpdate(userId, userName, addressId, address)
            findNavController().navigate(R.id.action_updateFragment_to_mainnFragment, bundle)
        }
    }

    private fun createUserToUpdate(user_id:Int,user_name:String,address_id:Int,address:String):Bundle{
        val updatedUser = UserWithAddress(user_id,user_name,address_id,address)
        return bundleOf(UPDATE_USER to updatedUser)
    }

    override fun onStart() {
        super.onStart()
        if (arguments?.getParcelable<UserWithAddress>(UPDATE_USER)!=null){
            setUserInfo()
            arguments?.clear()
        }
    }

    private fun setUserInfo() {
        val user = arguments?.getParcelable<UserWithAddress>(UPDATE_USER)
        val userName = user?.user_name
        val address = user?.user_address

        addressId = user?.user_address_id
        userId = user?.user_id

        binding.edtName.setText(userName)
        binding.edtAddress.setText(address)
    }
}