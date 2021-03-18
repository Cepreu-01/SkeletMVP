package com.example.skeletmvp.ui.view.fragments.repos


import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.SearchView
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.example.skeletmvp.R
import com.example.skeletmvp.databinding.FragmentReposBinding
import com.example.skeletmvp.repository.Repository
import com.example.skeletmvp.repository.retrofit.model.UserRepoPOJOItem
import com.example.skeletmvp.ui.view.fragments.base.BaseFragment
import com.example.skeletmvp.utils.USER_LOGIN
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers


class ReposFragment : BaseFragment<FragmentReposBinding>() {
    private var repository:Repository?=null
    private var arrayList:ArrayList<String> = ArrayList()

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentReposBinding
        get() = FragmentReposBinding::inflate

    override fun setupViews() {
        repository = Repository(requireContext())

        val searchView =binding.searchView
        val listView = binding.listView
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, arrayList)
        listView.adapter = adapter

        listView.setOnItemClickListener { parent, view, position, id ->
            val bundle = bundleOf("single_repo" to position)
            findNavController().navigate(R.id.action_coordinatorFragment_to_detalInfoFragment,bundle)
        }

        searchView.setOnQueryTextListener(object:SearchView.OnQueryTextListener{
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


        val login = arguments?.getString("new_args")

        repository
            ?.getUserRepoPojo(login!!)
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribe(object:DisposableObserver<List<UserRepoPOJOItem>>(){
                override fun onComplete() {}
                override fun onNext(t: List<UserRepoPOJOItem>) {
                    Log.e("LIST",t.toString())
                    arrayList.clear()
                    t.forEach {
                        arrayList.add(it.name)
                    }
                    adapter.notifyDataSetChanged()
                }

                override fun onError(e: Throwable) {
                    Log.e("ERROR:",e.message.toString())
                }
            })
    }

}