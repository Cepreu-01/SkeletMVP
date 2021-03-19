package com.example.skeletmvp.ui.view.fragments.saved


import android.app.AlertDialog
import android.content.DialogInterface
import android.util.Log
import android.view.*
import android.widget.ArrayAdapter
import android.widget.SearchView
import com.example.skeletmvp.R
import com.example.skeletmvp.databinding.FragmentSavedRepoBinding
import com.example.skeletmvp.repository.Repository
import com.example.skeletmvp.repository.retrofit.model.UserRepoPOJOItem
import com.example.skeletmvp.ui.view.fragments.base.BaseFragment
import com.example.skeletmvp.utils.USER_LOGIN
import io.reactivex.Completable
import io.reactivex.CompletableObserver
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
        retainInstance = true

        repository = Repository(requireContext())

        val searchView =binding.searchView
        val listView = binding.listView
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, arrayList)
        listView.adapter = adapter

        listView.setOnItemLongClickListener { parent, view, position, id ->
            val dialogBuilder = AlertDialog.Builder(requireContext())
            dialogBuilder.setMessage("Удалить этот репозиторий?")
            dialogBuilder.setNegativeButton("Нет",DialogInterface.OnClickListener { dialog, which -> })
            dialogBuilder.setPositiveButton("Да",DialogInterface.OnClickListener { dialog, which ->
                val repoName = arrayList[position]
                removeRepo(repoName)
            })
            dialogBuilder.create()
            dialogBuilder.show()
            true
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

        val currentLogin = activity?.intent?.getStringExtra(USER_LOGIN)
        repository?.getSavedRepos(currentLogin!!)?.subscribeOn(Schedulers.io())?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribe(object:DisposableObserver<List<UserRepoPOJOItem>>(){
                override fun onComplete() {}
                override fun onNext(t: List<UserRepoPOJOItem>) {
                    arrayList.clear()
                    t.forEach {
                        arrayList.add(it.name)
                    }
                    adapter.notifyDataSetChanged()
                }
                override fun onError(e: Throwable) {}
            })
    }

    private fun removeRepo(repoName: String) {
        repository
            ?.removeRepo(repoName)
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribe(object : CompletableObserver{
                override fun onComplete() {
                    Log.e("COMPLETE:","COMPLETED")
                }

                override fun onSubscribe(d: Disposable) {

                }

                override fun onError(e: Throwable) {
                    Log.e("ERROR:",e.message.toString())
                }
            })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        menu.add(0,2,0, R.string.remove_all)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            2 -> removeAllRepos()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun removeAllRepos() {
        repository
            ?.removeAllRepos()
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribe(object: CompletableObserver{
                override fun onComplete() {

                }

                override fun onSubscribe(d: Disposable) {

                }

                override fun onError(e: Throwable) {

                }
            })
    }

}