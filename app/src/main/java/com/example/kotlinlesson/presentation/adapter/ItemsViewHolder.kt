package com.example.kotlinlesson.presentation.adapter

import android.net.Uri
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinlesson.R
import com.example.kotlinlesson.domain.model.ItemsModel
import com.example.kotlinlesson.presentation.adapter.listener.ItemsListener
import com.squareup.picasso.Picasso

class ItemsViewHolder(
    private val view: View,
    private val itemsListener: ItemsListener
) : RecyclerView.ViewHolder(view) {

    fun bind(itemsModel: ItemsModel) {

        val description = view.findViewById<TextView>(R.id.tv_name)
        val imageView = view.findViewById<ImageView>(R.id.iv_image)
        val delete = view.findViewById<ImageView>(R.id.delete)
        val favorites = view.findViewById<ImageView>(R.id.btnFav)

        description.text = itemsModel.description

        Picasso.get().load(Uri.parse(itemsModel.image)).into(imageView)

        imageView.setOnClickListener {
            itemsListener.onClick()
        }

        itemView.setOnClickListener {
            itemsListener.onElementSelected(
                itemsModel.description,
                itemsModel.image
            )
        }

//        viewBinding.tvText.text = itemsModel.description
//
//        Picasso.get().load(Uri.parse(itemsModel.image)).into(viewBinding.ivImage)
//
//        viewBinding.ivImage.setOnClickListener {
//            itemsListener.onClick()
//        }
//
//        viewBinding.ivImage.setOnClickListener {
//            itemsListener.onElementSelected(
//                itemsModel.description,
//                itemsModel.image
//            )
//        }
        delete.setOnClickListener {
            itemsListener.onDeleteClicked(itemsModel.description)
        }

        favorites.setOnClickListener {
            itemsListener.onFavClicked(itemsModel.description)
        }

    }
}