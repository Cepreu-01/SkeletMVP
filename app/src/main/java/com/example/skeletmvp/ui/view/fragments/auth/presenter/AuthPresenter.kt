package com.example.skeletmvp.ui.view.fragments.auth.presenter

import android.app.Activity
import android.content.Context
import android.util.Log
import android.widget.EditText
import com.example.skeletmvp.repository.Repository
import com.example.skeletmvp.repository.room.model.UserLogin
import com.example.skeletmvp.ui.view.fragments.auth.view.IAuthFragment
import com.example.skeletmvp.utils.USER_LOGIN
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableCompletableObserver
import io.reactivex.schedulers.Schedulers

class AuthPresenter(private val iAuthFragment: IAuthFragment)
    :IAuthPresenter {
    private var repository:Repository?=null

    override fun getLogin(edt_login:EditText,activity: Activity?): String? {
        val login = edt_login.text.toString()
        if (!login.isNullOrEmpty()){
        sendCurrentLoginToActivityArgs(login,activity)
        return login
        } else return null
    }

    override fun sendCurrentLoginToActivityArgs(currentLogin: String,activity:Activity?) {
        activity?.intent?.putExtra(USER_LOGIN,currentLogin)
    }

    override fun saveCurrentLogin(currentLogin: String) {
        val userLogin = UserLogin(currentLogin)
        repository
            ?.saveCurrentLogin(userLogin)
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribe(object: DisposableCompletableObserver(){
                override fun onComplete() {
                    Log.e("COMPLETED","COMPLETED")
                }

                override fun onError(e: Throwable) {
                    Log.e("ERROR",e.message.toString())
                }
            })
        iAuthFragment.showMessage("Логин сохранен")
    }

    override fun initParams(context: Context) {
        repository = Repository(context)
    }

}