package com.example.skeletmvp.ui.view.fragments.add.view


import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.skeletmvp.R
import com.example.skeletmvp.databinding.FragmentAddBinding
import com.example.skeletmvp.ui.view.fragments.add.presenter.AddPresenter
import com.example.skeletmvp.ui.view.fragments.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_add.*

class AddFragment : BaseFragment<FragmentAddBinding>(),IAddFragment {
    private var presenter:AddPresenter?=null
    init {
        presenter = AddPresenter(this)
    }
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentAddBinding
        get() = FragmentAddBinding::inflate

    override fun setupViews() {
        btn_ok.setOnClickListener {
            val bundle = presenter?.createUser(binding)
            findNavController().navigate(R.id.action_addFragment_to_mainnFragment,bundle)
        }
    }

    override fun showMessage(message: String) {
        Toast.makeText(requireContext(),message,Toast.LENGTH_SHORT).show()
    }


}