package com.example.skeletmvp.ui.view.fragments.update.presenter

import android.os.Bundle
import android.util.Log
import androidx.core.os.bundleOf
import com.example.skeletmvp.databinding.FragmentUpdateBinding
import com.example.skeletmvp.repository.room.model.UserWithAddress
import com.example.skeletmvp.ui.view.fragments.update.view.IUpdateFragment
import com.example.skeletmvp.utils.UPDATE_USER

class UpdatePresenter(val iUpdateFragment: IUpdateFragment):IUpdatePresenter {
    var userId:Int?=null
    var addressId:Int?=null

    override fun setUserInfo(arguments:Bundle?,binding: FragmentUpdateBinding) {
        if (arguments?.getParcelable<UserWithAddress>(UPDATE_USER)!=null) {
            val user = arguments.getParcelable<UserWithAddress>(UPDATE_USER)
            val userName = user?.user_name
            val address = user?.user_address

            addressId = user?.user_address_id
            userId = user?.user_id

            binding.edtName.setText(userName)
            binding.edtAddress.setText(address)
        }
        iUpdateFragment.showMessage("SET USER INFO")
    }

    override fun createUserToUpdate(
        user_id: Int,
        user_name: String,
        address_id: Int,
        address: String
    ): Bundle {
        val updatedUser = UserWithAddress(user_id,user_name,address_id,address)
        return bundleOf(UPDATE_USER to updatedUser)
    }

    override fun getUserInfo(binding: FragmentUpdateBinding):Bundle? {
        val userId = this.userId
        val userName = binding.edtName.text.toString()
        val addressId = this.addressId
        val address = binding.edtAddress.text.toString()

        var bundle:Bundle?=null
        if (userId != null && addressId != null) {
            bundle = createUserToUpdate(userId, userName, addressId, address)
        }
        iUpdateFragment.showMessage("GET USER INFO")
        return bundle
    }
}