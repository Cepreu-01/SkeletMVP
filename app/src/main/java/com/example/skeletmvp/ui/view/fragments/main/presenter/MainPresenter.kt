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
            }
    }

    override fun destroyRefs() {
        repository?.closeDB()
        repository = null
        disp?.dispose()
    }


}