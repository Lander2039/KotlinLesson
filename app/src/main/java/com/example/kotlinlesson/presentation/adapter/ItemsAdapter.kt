package com.example.kotlinlesson.presentation.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinlesson.R
import com.example.kotlinlesson.databinding.ItemFruitBinding
import com.example.kotlinlesson.domain.model.ItemsModel
import com.example.kotlinlesson.presentation.adapter.listener.ItemsListener

class ItemsAdapter(
    private val itemsListener: ItemsListener
) : RecyclerView.Adapter<ItemsViewHolder>() {

    private var listItems = listOf<ItemsModel>()

    @SuppressLint("NotifyDataSetChanged")
    fun submitList(list: List<ItemsModel>) {
        this.listItems = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_fruit, parent, false)
        return ItemsViewHolder(view, itemsListener)
    }

    override fun onBindViewHolder(holder: ItemsViewHolder, position: Int) {
        holder.bind(listItems[position])
    }

    override fun getItemCount(): Int {
        return listItems.size
    }
}