package com.example.skeletmvp.ui.view.fragments.repos.view


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.SearchView
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.skeletmvp.R
import com.example.skeletmvp.databinding.FragmentReposBinding
import com.example.skeletmvp.ui.view.fragments.base.BaseFragment
import com.example.skeletmvp.ui.view.fragments.repos.presenter.ReposPresenter


class ReposFragment : BaseFragment<FragmentReposBinding>(),IReposFragment {
    companion object{
        const val SAVED_ARRAY = "saved_array"
        const val SINGLE_REPO = "single_repo"

    }

    private var presenter:ReposPresenter?=null
    private var arrayList:ArrayList<String> = ArrayList()

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentReposBinding
        get() = FragmentReposBinding::inflate

    override fun setupViews() {
        presenter = ReposPresenter(this)
        presenter?.initArgs(requireContext())

        val adapter = creatingListView()

        creatingSearchView(adapter)

        val login = presenter?.getCurrentLogin(requireActivity())

        presenter?.observeRepos(login,adapter,arrayList)

        adapter.notifyDataSetChanged()
    }

    private fun creatingSearchView(adapter: ArrayAdapter<String>) {
        val searchView = binding.searchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchView.clearFocus()
                if (arrayList.contains(query)) {
                    adapter.filter.filter(query)
                }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                adapter.filter.filter(newText)
                return false
            }
        })
    }

    private fun creatingListView(): ArrayAdapter<String> {
        val listView = binding.listView
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, arrayList)
        listView.adapter = adapter

        listView.setOnItemClickListener { parent, view, position, id ->
            val bundle = bundleOf(SINGLE_REPO to position)
            findNavController().navigate(
                R.id.action_coordinatorFragment_to_detalInfoFragment,
                bundle
            )
        }
        return adapter
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putStringArrayList(SAVED_ARRAY,arrayList)
        super.onSaveInstanceState(outState)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val arr = savedInstanceState?.getStringArrayList(SAVED_ARRAY)
        arr?.let { arrayList.addAll(it) }
    }

    override fun showMessage(message: String) {
        Toast.makeText(requireContext(),message,Toast.LENGTH_SHORT).show()
    }



}