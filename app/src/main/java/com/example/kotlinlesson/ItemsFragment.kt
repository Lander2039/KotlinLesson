package com.example.kotlinlesson

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinlesson.adapter.ItemsAdapter
import com.example.kotlinlesson.listener.ItemsListener
import com.example.kotlinlesson.model.ItemsModel

class ItemsFragment : Fragment(), ItemsListener {

    private lateinit var itemsAdapter: ItemsAdapter
    private val viewModel: ItemsViewModel by viewModels()
    private val keyName:String = "name"
    private val keyDate:String = "date"
    private val keyImageView:String = "imageView"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_items, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        itemsAdapter = ItemsAdapter(this)
        val recycledView = view.findViewById<RecyclerView>(R.id.recyclerview)
        recycledView.layoutManager = LinearLayoutManager(context)
        recycledView.adapter = itemsAdapter

        viewModel.getData()
        viewModel.items.observe(viewLifecycleOwner) { listItems ->
            itemsAdapter.submitList(listItems)
        }

        viewModel.msg.observe(viewLifecycleOwner) { msg ->
            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
        }

        viewModel.bundle.observe(viewLifecycleOwner){navBundle ->
            val detailsFragment = DetailsFragment()
            val bundle = Bundle()
            bundle.putString(keyName, navBundle.name)
            bundle.putString(keyDate, navBundle.date)
            bundle.putInt(keyImageView, navBundle.image)
            detailsFragment.arguments = bundle

            parentFragmentManager.beginTransaction()
                .replace(R.id.activity_container, detailsFragment)
                .addToBackStack("Details")
                .commit()
        }
    }

    override fun onClick() {
        viewModel.imageViewClicked()
    }

    override fun onElementSelected(name: String, date: String, imageView: Int) {
        viewModel.elementClicked(name, date, imageView)

    }
}