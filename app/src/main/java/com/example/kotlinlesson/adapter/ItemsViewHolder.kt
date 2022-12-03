package com.example.kotlinlesson.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinlesson.R
import com.example.kotlinlesson.listener.ItemsListener
import com.example.kotlinlesson.model.ItemsModel

class ItemsViewHolder(
    private val view: View,
    private val itemsListener: ItemsListener
): RecyclerView.ViewHolder(view) {

    fun bind(itemsModel: ItemsModel){
        val name = view.findViewById<TextView>(R.id.tv_text)
        val imageView = view.findViewById<ImageView>(R.id.iv_image)
        val date = view.findViewById<TextView>(R.id.tv_date2)

        name.text = itemsModel.name
        imageView.setBackgroundResource(itemsModel.image)
        date.text = itemsModel.date

        imageView.setOnClickListener{
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