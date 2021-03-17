package com.example.skeletmvp.ui.view.fragments.auth


import android.util.Log
import android.view.*
import androidx.navigation.fragment.findNavController
import com.example.skeletmvp.R
import com.example.skeletmvp.currentlogin.CurrentLogin
import com.example.skeletmvp.databinding.FragmentAuthBinding
import com.example.skeletmvp.repository.Repository
import com.example.skeletmvp.repository.room.model.UserLogin
import com.example.skeletmvp.ui.view.fragments.base.BaseFragment
import io.reactivex.Completable
import io.reactivex.CompletableObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Action
import io.reactivex.observers.DisposableCompletableObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AuthFragment : BaseFragment<FragmentAuthBinding>() {
    private var repository: Repository?=null

    override fun onStart() {
        super.onStart()
        repository = Repository(requireContext())
    }

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentAuthBinding
        get() = FragmentAuthBinding::inflate

    override fun setupViews() {
        binding.btnEnter.setOnClickListener {
            val login=getLogin()
            isRememberLogin(login)
            findNavController().navigate(R.id.action_authFragment_to_coordinatorFragment)
        }
    }

    private fun getLogin():String {
        val login = binding.edtLogin.text.toString()
        rememberCurrentLogin(login)
        return login
    }
    private fun isRememberLogin(login: String)  {
        if (binding.checkboxLogin.isChecked) {
            saveCurrentLogin(login)
        }
    }

    private fun rememberCurrentLogin(login:String){
        CurrentLogin.login = login
    }

    private fun saveCurrentLogin(login:String){
        val userLogin = UserLogin(login)
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
    }

}