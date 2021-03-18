package com.example.skeletmvp.ui.view.fragments.saved


import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.SearchView
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.example.skeletmvp.R
import com.example.skeletmvp.currentlogin.CurrentLogin
import com.example.skeletmvp.databinding.FragmentSavedRepoBinding
import com.example.skeletmvp.repository.Repository
import com.example.skeletmvp.repository.retrofit.SimpleRetrofit
import com.example.skeletmvp.repository.retrofit.model.User
import com.example.skeletmvp.repository.retrofit.model.UserRepoPOJOItem
import com.example.skeletmvp.ui.view.fragments.base.BaseFragment
import io.reactivex.Flowable
import io.reactivex.FlowableSubscriber
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import org.reactivestreams.Subscription

class SavedRepoFragment : BaseFragment<FragmentSavedRepoBinding>() {
    private var arrayList = ArrayList<String>()
    private var repository:Repository?=null
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentSavedRepoBinding
        get() = FragmentSavedRepoBinding::inflate

    override fun setupViews() {
        repository = Repository(requireContext())

        val searchView =binding.searchView
        val listView = binding.listView
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, arrayList)
        listView.adapter = adapter

        listView.setOnItemClickListener { parent, view, position, id ->

        }

        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchView.clearFocus()
                if (arrayList.contains(query)){
                    adapter.filter.filter(query)
                }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                adapter.filter.filter(newText)
                return false
            }
        })

        val currentLogin = CurrentLogin.login
        repository?.getSavedRepos(currentLogin)?.subscribeOn(Schedulers.io())?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribe(object:DisposableObserver<List<UserRepoPOJOItem>>(){
                override fun onComplete() {

                }

                override fun onNext(t: List<UserRepoPOJOItem>) {
                    arrayList.clear()
                    t.forEach {
                        arrayList.add(it.name)
                    }
                    adapter.notifyDataSetChanged()
                }

                override fun onError(e: Throwable) {

                }
            })
    }

}