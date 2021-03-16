package com.example.skeletmvp.ui.view.fragments.main.view

import android.util.Log
import android.view.*
import android.widget.LinearLayout
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.skeletmvp.R
import com.example.skeletmvp.databinding.FragmentMainnBinding
import com.example.skeletmvp.repository.room.model.UserWithAddress
import com.example.skeletmvp.ui.recyclerview.ItemClicks
import com.example.skeletmvp.ui.recyclerview.RecyclerAdapter
import com.example.skeletmvp.ui.view.fragments.base.BaseFragment
import com.example.skeletmvp.ui.view.fragments.main.presenter.MainPresenter
import com.example.skeletmvp.utils.ADD_USER
import com.example.skeletmvp.utils.UPDATE_USER
import kotlinx.android.synthetic.main.fragment_mainn.*

class MainnFragment : BaseFragment<FragmentMainnBinding>(),IMainFragment {
    private var recyclerView: RecyclerView? = null
    private var presenter:MainPresenter?=null


    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentMainnBinding
        get() = FragmentMainnBinding::inflate


    override fun setupViews() {
        presenter = MainPresenter(this,requireContext())
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter?.destroyRefs()
        recyclerView = null
    }

    override fun onStart() {
        super.onStart()
        presenter?.observeChanges()

    }

    override fun showMessage(message: Int) {
        Toast.makeText(requireContext(),message,Toast.LENGTH_SHORT).show()
    }

}