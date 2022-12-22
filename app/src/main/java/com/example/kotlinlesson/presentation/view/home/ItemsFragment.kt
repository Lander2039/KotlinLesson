package com.example.kotlinlesson.presentation.view.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlinlesson.R
import com.example.kotlinlesson.databinding.FragmentItemsBinding
import com.example.kotlinlesson.domain.model.ItemsModel
import com.example.kotlinlesson.presentation.adapter.ItemsAdapter
import com.example.kotlinlesson.presentation.adapter.listener.ItemsListener
import com.example.kotlinlesson.utils.BundleConstants.IMAGE_VIEW
import com.example.kotlinlesson.utils.BundleConstants.NAME
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


//not use
//const val NAME = "name"
private const val DETAILS = "Details"

@AndroidEntryPoint
class ItemsFragment : Fragment(), ItemsListener, ItemsView {

    private var _binding: FragmentItemsBinding? = null
    private val binding: FragmentItemsBinding get() = _binding!!

    private lateinit var itemsAdapter: ItemsAdapter

    @Inject
    lateinit var itemsPresenter: ItemsPresenter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentItemsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        itemsPresenter.setView(this)

        itemsAdapter = ItemsAdapter(this)
        binding.recyclerview.layoutManager = LinearLayoutManager(context)
        binding.recyclerview.adapter = itemsAdapter

        itemsPresenter.getItems()
    }

    override fun onClick() {
        itemsPresenter.imageViewClicked()
    }

    override fun onElementSelected(name: String, date: String, imageView: Int) {
        itemsPresenter.itemClicked(name, date, imageView)
    }

    override fun itemsReceived(itemsList: List<ItemsModel>) {
        itemsAdapter.submitList(itemsList)
    }

    override fun imageViewClicked(msg: Int) {
        Toast.makeText(context, getString(msg), Toast.LENGTH_SHORT).show()
    }

    override fun itemClicked(navigationDate: NavigateWithBundle) {
        val detailsFragment = DetailsFragment()
        val bundle = Bundle()
        bundle.putString(NAME, navigationDate.name)
        bundle.putString(DATE, navigationDate.date)
        bundle.putInt(IMAGE_VIEW, navigationDate.image)
        detailsFragment.arguments = bundle

        parentFragmentManager.beginTransaction()
            .replace(R.id.activity_container, detailsFragment)
            .addToBackStack(DETAILS)
            .commit()
    }

    companion object {
        const val DATE = "date"
    }
}