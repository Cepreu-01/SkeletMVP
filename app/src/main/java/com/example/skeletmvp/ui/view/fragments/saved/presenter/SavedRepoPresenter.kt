package com.example.skeletmvp.ui.view.fragments.saved.presenter

import android.app.Activity
import android.content.Context
import android.util.Log
import android.widget.ArrayAdapter
import com.example.skeletmvp.repository.Repository
import com.example.skeletmvp.repository.retrofit.model.UserRepoPOJOItem
import com.example.skeletmvp.ui.view.fragments.saved.view.ISavedRepoFragment
import com.example.skeletmvp.utils.USER_LOGIN
import io.reactivex.CompletableObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers

class SavedRepoPresenter(private val iSavedRepoFragment: ISavedRepoFragment):ISavedRepoPresenter {
    private var repository: Repository? = null

    override fun initArgs(context: Context) {
        repository = Repository(context)
    }

    override fun observeSavedRepos(login: String?, adapter: ArrayAdapter<String>,arrayList:ArrayList<String>) {
        if (!login.isNullOrEmpty()){
            repository?.getSavedRepos(login)?.subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribe(object : DisposableObserver<List<UserRepoPOJOItem>>() {
                    override fun onComplete() {}
                    override fun onNext(t: List<UserRepoPOJOItem>) {
                        arrayList.clear()
                        t.forEach {
                            arrayList.add(it.name)
                        }
                        adapter.notifyDataSetChanged()
                    }

                    override fun onError(e: Throwable) {
                        iSavedRepoFragment.showMessage(e.message.toString())
                    }
                })
        }

    }

    override fun removeRepo(repoName: String, adapter: ArrayAdapter<String>) {
        repository
            ?.removeRepo(repoName)
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribe(object : CompletableObserver {
                override fun onComplete() {
                    adapter.notifyDataSetChanged()
                }
                override fun onSubscribe(d: Disposable) {}
                override fun onError(e: Throwable) {
                   iSavedRepoFragment.showMessage(e.message.toString())
                }
            })
    }

    override fun removeAllRepos() {
        repository
            ?.removeAllRepos()
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribe(object: CompletableObserver{
                override fun onComplete() {}
                override fun onSubscribe(d: Disposable) {}
                override fun onError(e: Throwable) {
                    iSavedRepoFragment.showMessage(e.message.toString())
                }
            })
    }

    override fun getCurrentLogin(activity: Activity)= activity.intent.getStringExtra(USER_LOGIN)

}