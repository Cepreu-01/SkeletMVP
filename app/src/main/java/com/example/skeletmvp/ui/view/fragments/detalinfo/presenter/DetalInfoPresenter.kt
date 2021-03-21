package com.example.skeletmvp.ui.view.fragments.detalinfo.presenter

import android.app.Activity
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import com.example.skeletmvp.repository.Repository
import com.example.skeletmvp.repository.retrofit.model.Owner
import com.example.skeletmvp.repository.retrofit.model.UserRepoPOJOItem
import com.example.skeletmvp.ui.view.fragments.detalinfo.view.IDetalInfoFragment
import com.example.skeletmvp.utils.USER_LOGIN
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableCompletableObserver
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception
import java.net.HttpURLConnection
import java.net.URL

class DetalInfoPresenter(private val iDetalInfoFragment: IDetalInfoFragment) : IDetalInfoPresenter {
    private var repository: Repository? = null
    private var saveRepo: UserRepoPOJOItem? = null

    override fun initArgs(context: Context) {
        repository = Repository(context)
    }

    override fun getLogin(activity: Activity?) = activity?.intent?.getStringExtra(USER_LOGIN)
    override fun setupInfo(
        login: String?,
        tv_login: TextView,
        tv_repo_name: TextView,
        tv_description: TextView,
        tv_forks_count: TextView,
        tv_stars_count: TextView,
        tv_created_at: TextView,
        avatar: ImageView,
        arguments: Bundle?
    ) {
        repository
            ?.getUserRepoPojo(login!!)
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribe(object : DisposableObserver<List<UserRepoPOJOItem>>() {
                override fun onComplete() {}
                override fun onNext(t: List<UserRepoPOJOItem>) {
                    if (getRepoByPosition(arguments) != null) {

                        val repo = getRepoByPosition(arguments)
                        val singleRepo = t[repo!!]

                        saveRepo = UserRepoPOJOItem(
                            singleRepo.description,
                            singleRepo.forks_count,
                            singleRepo.name,
                            Owner(
                                singleRepo.owner.avatar_url,
                                singleRepo.owner.login
                            ),
                            singleRepo.stargazers_count,
                            singleRepo.created_at
                        )

                        tv_login.text = "login: "+singleRepo.owner.login
                        tv_repo_name.text = "name: "+singleRepo.name
                        tv_description.text = "desc: "+singleRepo.description
                        tv_forks_count.text = "forks: "+singleRepo.forks_count.toString()
                        tv_stars_count.text = "stars: "+singleRepo.stargazers_count.toString()
                        tv_created_at.text = "created: "+singleRepo.created_at
                        CoroutineScope(Dispatchers.IO).launch {
                            val src = singleRepo.owner.avatar_url
                            try {
                                val bitmap = createBitMap(src)
                                launch(Dispatchers.Main) {
                                    avatar.setImageBitmap(bitmap)
                                }
                            } catch (ex:Exception){
                                Log.e("IMAGE LOADING EXC: ", ex.message.toString())
                            }
                        }

                    }
                }

                override fun onError(e: Throwable) {
                    iDetalInfoFragment.showMessage(e.message.toString())
                }
            })
    }

    override fun getRepoByPosition(arguments: Bundle?): Int? {
        val repoPosition = arguments?.getInt("single_repo")!!
        return repoPosition
    }

    override fun saveCurrentRepo() {
        saveRepo?.let { userRepo ->
            repository
                ?.saveCurrentRepo(userRepo)
                ?.subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribe(object : DisposableCompletableObserver() {
                    override fun onComplete() {
                        Log.e("COMPLETABLE", "COMPLETED")
                    }

                    override fun onError(e: Throwable) {
                        iDetalInfoFragment.showMessage(e.message.toString())
                    }
                })
        }
    }

    override fun createBitMap(src: String?): Bitmap? {
        val url = URL(src)
        val connection = url.openConnection() as HttpURLConnection
        connection.doInput = true
        connection.connect()
        val input = connection.inputStream

        return BitmapFactory.decodeStream(input)
    }


}