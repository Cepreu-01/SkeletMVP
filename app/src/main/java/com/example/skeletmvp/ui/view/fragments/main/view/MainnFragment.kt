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
import com.example.skeletmvp.utils.ADD_USER
import com.example.skeletmvp.utils.UPDATE_USER
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_mainn.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainnFragment : BaseFragment<FragmentMainnBinding>(), ItemClicks {
    private var dao: UserDao? = null
    private var adapter: RecyclerAdapter? = null
    private var disp: Disposable? = null
    private var recyclerView: RecyclerView? = null

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentMainnBinding
        get() = FragmentMainnBinding::inflate


    override fun setupViews() {
        adapter = RecyclerAdapter(itemClicks = this)
        dao = Repository(requireContext()).dao
        createRecycler()
        disp = dao?.getUserWithAddress()?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())?.subscribe {
                adapter?.userList?.clear()
                adapter?.userList?.addAll(it)
                adapter?.notifyDataSetChanged()
            }

    }

    private fun createRecycler() {
        recyclerView = recycler_view
        recyclerView?.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        recyclerView?.adapter = adapter
    }

    override fun onDestroy() {
        super.onDestroy()
        disp?.dispose()
        recyclerView = null
        dao = null
        adapter = null
    }

    override fun onStart() {
        super.onStart()
        if (arguments?.getParcelable<UserWithAddress>(ADD_USER) != null) {
            addUser()
            arguments?.clear()
        }
        if(arguments?.getParcelable<UserWithAddress>(UPDATE_USER) != null){
            updateUser()
        }
    }

    private fun updateUser() {
        val userWithAddress = arguments?.getParcelable<UserWithAddress>(UPDATE_USER)
        val userId = userWithAddress?.user_id
        val userName = userWithAddress?.user_name
        val userAddressId = userWithAddress?.user_address_id
        val userAddress = userWithAddress?.user_address

        if (userId != null && userName != null && userAddressId != null && userAddress != null) {
            val user = UserModel(userId, userName)
            val address = UserAddress(userAddressId, userAddress)

            CoroutineScope(Dispatchers.IO).launch {
                dao?.updateUserWithAddress(user, address)
            }
        }
    }

    private fun addUser() {
        val userWithAddress = arguments?.getParcelable<UserWithAddress>(ADD_USER)
        val userId = userWithAddress?.user_id
        val userName = userWithAddress?.user_name
        val userAddressId = userWithAddress?.user_address_id
        val userAddress = userWithAddress?.user_address

        if (userId != null && userName != null && userAddressId != null && userAddress != null) {
            val user = UserModel(userId, userName)
            val address = UserAddress(userAddressId, userAddress)

            CoroutineScope(Dispatchers.IO).launch {
                dao?.insertUserWithAddress(user, address)
            }
        }
    }

    override fun itemClick(position: Int) {
        val user = adapter?.userList?.get(position)
        val bundle = bundleOf(UPDATE_USER to user)
        findNavController().navigate(R.id.action_mainnFragment_to_updateFragment, bundle)
    }

    override fun longItemClick(position: Int) {
        val userId = adapter?.userList?.get(position)?.user_id
        if (userId != null) deleteUser(userId)
    }

    private fun deleteUser(userId: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            dao?.deleteUser(userId)
        }
    }

}