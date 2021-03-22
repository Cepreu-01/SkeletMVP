package com.example.skeletmvp.ui.view.fragments.detalinfo.view

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import com.example.skeletmvp.databinding.FragmentDetalInfoBinding
import com.example.skeletmvp.ui.view.fragments.base.BaseFragment
import com.example.skeletmvp.ui.view.fragments.detalinfo.presenter.DetalInfoPresenter
import kotlinx.android.synthetic.main.fragment_detal_info.*

class DetalInfoFragment : BaseFragment<FragmentDetalInfoBinding>(), IDetalInfoFragment {
    private var presenter: DetalInfoPresenter? = null

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentDetalInfoBinding
        get() = FragmentDetalInfoBinding::inflate

    override fun onStop() {
        super.onStop()
        presenter = null
    }

    override fun setupViews() {
        presenter = DetalInfoPresenter(this)
        presenter?.initArgs(requireContext())

        val login = presenter?.getLogin(requireActivity())

        presenter?.setupInfo(
            login,
        binding.tvLogin,
        binding.tvRepoName,
        binding.tvDescription,
        binding.tvForksCount,
        binding.tvStarsCount,
        binding.tvCreatedAt,
        binding.imageView,
        arguments)

        btn_save_repo.setOnClickListener {
            presenter?.saveCurrentRepo()
        }
    }

    override fun showMessage(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

}