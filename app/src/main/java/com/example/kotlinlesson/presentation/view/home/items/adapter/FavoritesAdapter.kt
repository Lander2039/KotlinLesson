package com.example.kotlinlesson.presentation.view.home.items.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinlesson.databinding.ItemFavoritesBinding
import com.example.kotlinlesson.domain.model.FavoriteModel

class FavoritesAdapter(

) : RecyclerView.Adapter<FavoritesViewHolder>() {

    private var listItems = listOf<FavoriteModel>()

    @SuppressLint("NotifyDataSetChanged")
    fun submitList(list: List<FavoriteModel>) {
        this.listItems = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoritesViewHolder {
        val viewBinding = ItemFavoritesBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return FavoritesViewHolder(viewBinding)
    }

    override fun onBindViewHolder(holder: FavoritesViewHolder, position: Int) {
        holder.bind(listItems[position])
    }

    override fun getItemCount(): Int {
        return listItems.size
    }
}