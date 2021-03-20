package com.example.skeletmvp.ui.view.fragments.saved.view


import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.ArrayAdapter
import android.widget.SearchView
import android.widget.Toast
import com.example.skeletmvp.R
import com.example.skeletmvp.databinding.FragmentSavedRepoBinding
import com.example.skeletmvp.repository.Repository
import com.example.skeletmvp.repository.retrofit.model.UserRepoPOJOItem
import com.example.skeletmvp.ui.view.fragments.base.BaseFragment
import com.example.skeletmvp.ui.view.fragments.repos.view.ReposFragment
import com.example.skeletmvp.ui.view.fragments.saved.presenter.SavedRepoPresenter
import com.example.skeletmvp.utils.USER_LOGIN
import io.reactivex.CompletableObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers

class SavedRepoFragment : BaseFragment<FragmentSavedRepoBinding>(),ISavedRepoFragment {
    companion object{
        const val SAVED_ARRAY = "saved_array"
    }

    private var arrayList:ArrayList<String> = ArrayList()
    private var presenter:SavedRepoPresenter?=null

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentSavedRepoBinding
        get() = FragmentSavedRepoBinding::inflate

    override fun setupViews() {

        presenter = SavedRepoPresenter(this)
        presenter?.initArgs(requireContext())

        val adapter = creatingListView(arrayList)

        creatingSearchView(adapter,arrayList)

        val login = presenter?.getCurrentLogin(requireActivity())

        presenter?.observeSavedRepos(login, adapter, arrayList)

        adapter.notifyDataSetChanged()

    }



    private fun creatingSearchView(adapter: ArrayAdapter<String>,arrayList: ArrayList<String>) {
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

    private fun creatingListView(arrayList: ArrayList<String>): ArrayAdapter<String> {
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, arrayList)

        val listView = binding.listView
        listView.adapter = adapter

        listView.setOnItemLongClickListener { parent, view, position, id ->
            val dialogBuilder = AlertDialog.Builder(requireContext())
            dialogBuilder.setMessage("Удалить этот репозиторий?")
            dialogBuilder.setNegativeButton(
                "Нет",
                DialogInterface.OnClickListener { dialog, which -> })
            dialogBuilder.setPositiveButton("Да", DialogInterface.OnClickListener { dialog, which ->
                val repoName = arrayList[position]
                presenter?.removeRepo(repoName,adapter)
            })
            dialogBuilder.create()
            dialogBuilder.show()
            true
        }
        return adapter
    }



    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        if (arrayList.isNotEmpty()) {
            menu.add(0,2,0, R.string.remove_all)
        }
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        super.onPrepareOptionsMenu(menu)
        if (arrayList.isEmpty()){
            menu.removeItem(2)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            2 -> presenter?.removeAllRepos()

        }
        return super.onOptionsItemSelected(item)
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