package com.example.skeletmvp.ui.view.fragments.update.view


import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.skeletmvp.R
import com.example.skeletmvp.databinding.FragmentUpdateBinding
import com.example.skeletmvp.repository.room.model.UserWithAddress
import com.example.skeletmvp.ui.view.fragments.base.BaseFragment
import com.example.skeletmvp.ui.view.fragments.update.presenter.UpdatePresenter
import com.example.skeletmvp.utils.UPDATE_USER


class UpdateFragment : BaseFragment<FragmentUpdateBinding>(),IUpdateFragment {
    private var presenter:UpdatePresenter?=null

    init {
        presenter = UpdatePresenter(this)
    }

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentUpdateBinding
        get() = FragmentUpdateBinding::inflate

    override fun setupViews() {
        binding.btnOk.setOnClickListener {
            if (binding.edtAddress.text.isNotEmpty() && binding.edtName.text.isNotEmpty()){
                val bundle =presenter?.getUserInfo(binding)

                if (bundle!=null){
                    findNavController().navigate(R.id.action_updateFragment_to_mainnFragment, bundle)
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()
        presenter?.setUserInfo(arguments,binding)
    }

    override fun showMessage(message: Int) {
        Toast.makeText(requireContext(),message,Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter = null
    }
}