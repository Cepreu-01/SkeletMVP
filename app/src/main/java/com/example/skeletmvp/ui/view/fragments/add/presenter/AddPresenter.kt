package com.example.skeletmvp.ui.view.fragments.add.presenter

import android.os.Bundle
import androidx.core.os.bundleOf
import com.example.skeletmvp.R
import com.example.skeletmvp.databinding.FragmentAddBinding
import com.example.skeletmvp.repository.room.model.UserWithAddress
import com.example.skeletmvp.ui.view.fragments.add.view.IAddFragment
import com.example.skeletmvp.utils.ADD_USER

class AddPresenter(val iAddFragment: IAddFragment):IAddPresenter {

    override fun createUser(binding:FragmentAddBinding):Bundle? {
        val userName = binding.edtName.text.toString()
        val userAddress = binding.edtAddress.text.toString()

        val user = UserWithAddress(0, userName, 0, userAddress)
        iAddFragment.showMessage(R.string.add_fragment_label)

        return bundleOf(ADD_USER to user)
    }
}