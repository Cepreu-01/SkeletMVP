package com.example.skeletmvp.ui.view.fragments.info


import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.SearchView
import androidx.core.os.bundleOf

import androidx.navigation.fragment.findNavController
import com.example.skeletmvp.R
import com.example.skeletmvp.currentlogin.CurrentLogin
import com.example.skeletmvp.databinding.FragmentInfoBinding
import com.example.skeletmvp.repository.Repository

import com.example.skeletmvp.repository.retrofit.model.UserRepoPOJOItem
import com.example.skeletmvp.ui.view.fragments.DetalInfoFragment
import com.example.skeletmvp.ui.view.fragments.base.BaseFragment
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers

import java.net.HttpURLConnection
import java.net.URL


class InfoFragment : BaseFragment<FragmentInfoBinding>() {
    private var repository:Repository?=null
    private var arrayList:ArrayList<String> = ArrayList()

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentInfoBinding
        get() = FragmentInfoBinding::inflate

    override fun setupViews() {
        repository = Repository(requireContext())
        val login = CurrentLogin.login

        val searchView =binding.searchView
        val listView = binding.listView
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, arrayList)
        listView.adapter = adapter

        listView.setOnItemClickListener { parent, view, position, id ->
            val bundle = bundleOf("single_repo" to position)
            findNavController().navigate(R.id.action_infoFragment_to_detalInfoFragment,bundle)
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

        repository
            ?.getUserRepoPojo(login)
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribe(object:DisposableObserver<List<UserRepoPOJOItem>>(){
                override fun onComplete() {}
                override fun onNext(t: List<UserRepoPOJOItem>) {
                    t.forEach {
                        arrayList.add(it.name)
                    }
                    adapter.notifyDataSetChanged()
                }

                override fun onError(e: Throwable) {}
            })
    }
}