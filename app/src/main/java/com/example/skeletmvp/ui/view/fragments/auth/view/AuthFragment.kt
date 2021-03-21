package com.example.skeletmvp.ui.view.fragments.auth.view


import android.view.*
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.skeletmvp.R
import com.example.skeletmvp.databinding.FragmentAuthBinding
import com.example.skeletmvp.ui.view.fragments.auth.presenter.AuthPresenter
import com.example.skeletmvp.ui.view.fragments.base.BaseFragment


class AuthFragment : BaseFragment<FragmentAuthBinding>(),IAuthFragment {
    private var presenter:AuthPresenter?=null

    override fun onStart() {
        super.onStart()
        presenter = AuthPresenter(this)
        presenter?.initParams(requireContext())
    }

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentAuthBinding
        get() = FragmentAuthBinding::inflate

    override fun setupViews() {
        binding.btnEnter.setOnClickListener {
            val login = presenter?.getLogin(binding.edtLogin,requireActivity())
            if (login!=null) {
                presenter?.saveCurrentLogin(login)
                findNavController().navigate(R.id.action_authFragment_to_coordinatorFragment)
            }else Toast.makeText(requireContext(),R.string.login_is_empty,Toast.LENGTH_SHORT).show()
        }
    }

    override fun showMessage(message: Int) {
        Toast.makeText(requireContext(),message,Toast.LENGTH_SHORT).show()
    }

}