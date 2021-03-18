package com.example.skeletmvp.ui.view.fragments

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.skeletmvp.R
import com.example.skeletmvp.databinding.FragmentDetalInfoBinding
import com.example.skeletmvp.repository.Repository
import com.example.skeletmvp.repository.retrofit.model.Owner
import com.example.skeletmvp.repository.retrofit.model.Repo
import com.example.skeletmvp.repository.retrofit.model.UserRepoPOJOItem
import com.example.skeletmvp.ui.view.fragments.base.BaseFragment
import com.example.skeletmvp.utils.USER_LOGIN
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableCompletableObserver
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_detal_info.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.net.HttpURLConnection
import java.net.URL

class DetalInfoFragment : BaseFragment<FragmentDetalInfoBinding>() {
    private var repository:Repository?=null
    private var saveRepo: UserRepoPOJOItem?=null

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentDetalInfoBinding
        get() = FragmentDetalInfoBinding::inflate

    override fun setupViews() {
        repository = Repository(requireContext())


        val login = activity?.intent?.getStringExtra(USER_LOGIN)

        repository
            ?.getUserRepoPojo(login!!)
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribe(object: DisposableObserver<List<UserRepoPOJOItem>>(){
                override fun onComplete() {}
                override fun onNext(t: List<UserRepoPOJOItem>) {
                    if (arguments?.getInt("single_repo")!=null){

                        val repo =arguments?.getInt("single_repo")!!
                        val singleRepo=t[repo]
                        saveRepo = UserRepoPOJOItem(
                            singleRepo.description,
                            singleRepo.forks_count,
                            singleRepo.name,
                            Owner(
                                singleRepo.owner.avatar_url,
                                singleRepo.owner.login),
                            singleRepo.stargazers_count,
                            singleRepo.created_at)

                        binding.tvLogin.text = singleRepo.owner.login
                        binding.tvRepoName.text = singleRepo.name
                        binding.tvDescription.text = singleRepo.description
                        binding.tvForksCount.text = singleRepo.forks_count.toString()
                        binding.tvStarsCount.text = singleRepo.stargazers_count.toString()
                        binding.tvCreatedAt.text = singleRepo.created_at
                        CoroutineScope(Dispatchers.IO).launch {
                            val src = singleRepo.owner.avatar_url
                            val bitmap = createBitmap(src)
                            launch(Dispatchers.Main) {
                                binding.imageView.setImageBitmap(bitmap)
                            }
                        }

                    }
                }
                override fun onError(e: Throwable) {}
            })

        button.setOnClickListener {
            findNavController().navigate(R.id.action_detalInfoFragment_to_coordinatorFragment)
        }
        btn_save_repo.setOnClickListener {
            Log.e("REPO", saveRepo.toString())
            saveRepo?.let {userRepo->
                repository
                    ?.saveCurrentRepo(userRepo)
                    ?.subscribeOn(Schedulers.io())
                    ?.observeOn(AndroidSchedulers.mainThread())
                    ?.subscribe(object:DisposableCompletableObserver(){
                        override fun onComplete() {
                            Log.e("COMPLETABLE","COMPLETED")
                        }

                        override fun onError(e: Throwable) {
                            Log.e("ERROR",e.message.toString())
                        }
                    }) }
        }

    }
    private fun createBitmap(src:String): Bitmap {
        val url = URL(src)
        val connection = url.openConnection() as HttpURLConnection
        connection.doInput = true
        connection.connect()
        val input = connection.inputStream
        return BitmapFactory.decodeStream(input)
    }

}