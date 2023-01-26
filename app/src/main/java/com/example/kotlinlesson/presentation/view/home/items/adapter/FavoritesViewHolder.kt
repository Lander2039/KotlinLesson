package com.example.kotlinlesson.presentation.view.home.items.adapter

import android.net.Uri
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinlesson.databinding.ItemFavoritesBinding
import com.example.kotlinlesson.domain.model.FavoriteModel
import com.squareup.picasso.Picasso

class FavoritesViewHolder(
    private val viewBinding: ItemFavoritesBinding,
) : RecyclerView.ViewHolder(viewBinding.root) {

    fun bind(favItems: FavoriteModel) {

        viewBinding.tvName.text = favItems.description
        Picasso.get().load(Uri.parse(favItems.image)).into(viewBinding.ivImage)
    }
}