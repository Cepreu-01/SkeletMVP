package com.example.skeletmvp.ui.view.fragments.repos.presenter

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import com.example.skeletmvp.repository.Repository
import com.example.skeletmvp.repository.retrofit.model.UserRepoPOJOItem
import com.example.skeletmvp.ui.view.fragments.repos.view.IReposFragment
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers

class ReposPresenter(private val iReposFragment: IReposFragment):IReposPresenter {
    private var repository:Repository?=null

    override fun initArgs(context: Context) {
        repository = Repository(context)
    }

    override fun observeRepos(login: String?, adapter: ArrayAdapter<String>,arrayList:ArrayList<String>) {
        if (!login.isNullOrEmpty()){
            repository
                ?.getUserRepoPojo(login)
                ?.subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribe(object : DisposableObserver<List<UserRepoPOJOItem>>() {
                    override fun onComplete() {}
                    override fun onNext(t: List<UserRepoPOJOItem>) {
                        Log.e("LIST", t.toString())
                        arrayList.clear()
                        t.forEach {
                            arrayList.add(it.name)
                        }
                        adapter.notifyDataSetChanged()
                    }

                    override fun onError(e: Throwable) {
                        iReposFragment.showMessage(e.message.toString())
                    }
                })
        } else iReposFragment.showMessage("Логин пустой")
    }

    override fun getCurrentLogin(arguments: Bundle?) = arguments?.getString("new_args")

}