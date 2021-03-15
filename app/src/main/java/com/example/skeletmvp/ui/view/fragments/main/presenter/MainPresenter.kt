package com.example.skeletmvp.ui.view.fragments.main.presenter

import android.content.Context
import android.os.Bundle
import com.example.skeletmvp.R
import com.example.skeletmvp.repository.Repository
import com.example.skeletmvp.repository.room.dao.UserDao
import com.example.skeletmvp.repository.room.db.UserDatabase
import com.example.skeletmvp.repository.room.model.UserAddress
import com.example.skeletmvp.repository.room.model.UserModel
import com.example.skeletmvp.repository.room.model.UserWithAddress
import com.example.skeletmvp.ui.view.fragments.main.view.IMainFragment
import com.example.skeletmvp.utils.ADD_USER
import com.example.skeletmvp.utils.UPDATE_USER
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainPresenter(val iMainFragment: IMainFragment, context: Context) : IMainPresenter {
    private var repository:Repository? = null
    private var disp: Disposable? = null

    init {
        repository = Repository(context)
    }

    override fun observeChanges() {
        disp = repository?.getUserWithAddress()?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())?.subscribe {
                iMainFragment.updateAdapter(it)
            }
    }

    override fun destroyRefs() {
        repository?.closeDB()
        repository = null
        disp?.dispose()
    }

    override fun getAddUserArguments(arguments: Bundle?)=arguments?.getParcelable<UserWithAddress>(ADD_USER)

    override fun getUpdateUserArguments(arguments: Bundle?)=arguments?.getParcelable<UserWithAddress>(UPDATE_USER)

    override fun updateUser(arguments:Bundle?) {
        if(getUpdateUserArguments(arguments) != null) {
            val userWithAddress = getUpdateUserArguments(arguments)
            val userId = userWithAddress?.user_id
            val userName = userWithAddress?.user_name
            val userAddressId = userWithAddress?.user_address_id
            val userAddress = userWithAddress?.user_address

            if (userId != null && userName != null && userAddressId != null && userAddress != null) {
                val user = UserModel(userId, userName)
                val address = UserAddress(userAddressId, userAddress)

                CoroutineScope(Dispatchers.IO).launch {
                    repository?.updateUserWithAddress(user, address)
                }
            }
            arguments?.clear()
            iMainFragment.showMessage(R.string.user_updated)
        }
    }

    override fun addUser(arguments: Bundle?) {
        if (getAddUserArguments(arguments)!=null) {
            val userWithAddress = getAddUserArguments(arguments)
            val userId = userWithAddress?.user_id
            val userName = userWithAddress?.user_name
            val userAddressId = userWithAddress?.user_address_id
            val userAddress = userWithAddress?.user_address

            if (userId != null && userName != null && userAddressId != null && userAddress != null) {
                val user = UserModel(userId, userName)
                val address = UserAddress(userAddressId, userAddress)

                CoroutineScope(Dispatchers.IO).launch {
                    repository?.insertUserWithAddress(user, address)
                }
            }
            arguments?.clear()
            iMainFragment.showMessage(R.string.user_added)
        }
    }

    override fun deleteUser(userId:Int) {
        CoroutineScope(Dispatchers.IO).launch {
            repository?.deleteUser(userId)
        }
        iMainFragment.showMessage(R.string.user_deleted)
    }
}