package com.example.kotlinlesson.presentation.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinlesson.R
import com.example.kotlinlesson.databinding.ItemFruitBinding
import com.example.kotlinlesson.presentation.adapter.listener.ItemsListener
import com.example.kotlinlesson.model.ItemsModel

class ItemsViewHolder(
    private val viewBinding: ItemFruitBinding,
    private val itemsListener: ItemsListener
): RecyclerView.ViewHolder(viewBinding.root) {

    fun bind(itemsModel: ItemsModel){


        viewBinding.tvText.text = itemsModel.name
        viewBinding.ivImage.setBackgroundResource(itemsModel.image)
        viewBinding.tvDate2.text = itemsModel.date

        viewBinding.ivImage.setOnClickListener{
            itemsListener.onClick()
        }

        itemView.setOnClickListener {
            itemsListener.onElementSelected(
                itemsModel.name,
                itemsModel.date,
                itemsModel.image
            )
        }
    }
}