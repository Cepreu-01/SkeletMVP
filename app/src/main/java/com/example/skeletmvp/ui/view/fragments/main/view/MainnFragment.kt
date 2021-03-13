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
import com.example.skeletmvp.repository.Repository
import com.example.skeletmvp.repository.room.dao.UserDao
import com.example.skeletmvp.repository.room.model.UserAddress
import com.example.skeletmvp.repository.room.model.UserModel
import com.example.skeletmvp.repository.room.model.UserWithAddress
import com.example.skeletmvp.ui.recyclerview.ItemClicks
import com.example.skeletmvp.ui.recyclerview.RecyclerAdapter
import com.example.skeletmvp.ui.view.fragments.base.BaseFragment
import com.example.skeletmvp.ui.view.fragments.main.presenter.MainPresenter
import com.example.skeletmvp.utils.ADD_USER
import com.example.skeletmvp.utils.UPDATE_USER
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_mainn.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainnFragment : BaseFragment<FragmentMainnBinding>(), ItemClicks,IMainFragment {
    private var adapter: RecyclerAdapter? = null
    private var recyclerView: RecyclerView? = null
    private var presenter:MainPresenter?=null

    init {
        adapter = RecyclerAdapter(itemClicks = this)
    }

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentMainnBinding
        get() = FragmentMainnBinding::inflate


    override fun setupViews() {
        setupRecycler()
        presenter = MainPresenter(this,requireContext())
    }

    override fun updateAdapter(list: List<UserWithAddress>) {
        adapter?.userList?.clear()
        adapter?.userList?.addAll(list)
        adapter?.notifyDataSetChanged()
    }

    override fun setupRecycler() {
        recyclerView = recycler_view
        recyclerView?.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        recyclerView?.adapter = adapter
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter?.destroyRefs()
        recyclerView = null
        adapter = null
    }

    override fun onStart() {
        super.onStart()
        presenter?.addUser(arguments)
        presenter?.updateUser(arguments)
        presenter?.observeChanges()

    }

    override fun itemClick(position: Int) {
        val user = adapter?.userList?.get(position)
        val bundle = bundleOf(UPDATE_USER to user)
        findNavController().navigate(R.id.action_mainnFragment_to_updateFragment, bundle)
    }

    override fun longItemClick(position: Int) {
        val userId = adapter?.userList?.get(position)?.user_id
        if (userId != null) presenter?.deleteUser(userId)
    }

    override fun showMessage(message: String) {
        Toast.makeText(requireContext(),message,Toast.LENGTH_SHORT).show()
    }

}