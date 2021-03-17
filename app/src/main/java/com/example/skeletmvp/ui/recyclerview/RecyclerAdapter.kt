package com.example.skeletmvp.ui.recyclerview

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView


class RecyclerAdapter(
    var userList:ArrayList<String> = ArrayList(),
    val itemClicks: ItemClicks
):RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    inner class ViewHolder(item:View):RecyclerView.ViewHolder(item),View.OnClickListener,View.OnLongClickListener {
        init {
            setClickListeners()
        }



        override fun onClick(v: View?) {
            itemClicks.itemClick(adapterPosition)
        }

        override fun onLongClick(v: View?): Boolean {
            itemClicks.longItemClick(adapterPosition)
            return true
        }
        private fun setClickListeners(){
            itemView.setOnClickListener(this)
            itemView.setOnLongClickListener(this)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(parent)
    }

    override fun getItemCount() = userList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

    }
}