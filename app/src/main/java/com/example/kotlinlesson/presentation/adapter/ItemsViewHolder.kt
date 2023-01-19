package com.example.kotlinlesson.presentation.adapter

import android.net.Uri
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinlesson.databinding.ItemFruitBinding
import com.example.kotlinlesson.domain.model.ItemsModel
import com.example.kotlinlesson.presentation.adapter.listener.ItemsListener
import com.squareup.picasso.Picasso

class ItemsViewHolder(
    private val viewBinding: ItemFruitBinding,
    private val itemsListener: ItemsListener
) : RecyclerView.ViewHolder(viewBinding.root) {

    fun bind(itemsModel: ItemsModel) {

        viewBinding.tvText.text = itemsModel.description

        Picasso.get().load(Uri.parse(itemsModel.image)).into(viewBinding.ivImage)

        viewBinding.ivImage.setOnClickListener {
            itemsListener.onClick()
        }

        itemView.setOnClickListener {
            itemsListener.onElementSelected(
                itemsModel.description,
                itemsModel.image
            )
        }
    }
}