package com.example.kotlinlesson.presentation.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinlesson.utils.BundleConstants.IMAGE_VIEW
import com.example.kotlinlesson.utils.BundleConstants.NAME
import com.example.kotlinlesson.R
import com.example.kotlinlesson.data.ItemsRepositoryImpl
import com.example.kotlinlesson.databinding.FragmentItemsBinding
import com.example.kotlinlesson.databinding.FragmentOnBoardingBinding
import com.example.kotlinlesson.domain.ItemsInteractor
import com.example.kotlinlesson.presentation.adapter.ItemsAdapter
import com.example.kotlinlesson.presentation.adapter.listener.ItemsListener
import com.example.kotlinlesson.model.ItemsModel


//not use
//const val NAME = "name"
private const val DETAILS = "Details"

class ItemsFragment : Fragment(), ItemsListener, ItemsView {

    private lateinit var itemsAdapter: ItemsAdapter

    lateinit var itemsPresenter: ItemsPresenter

    private var _viewBinding: FragmentItemsBinding? = null
    private val viewBinding get() = _viewBinding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _viewBinding = FragmentItemsBinding.inflate(inflater)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        itemsPresenter = ItemsPresenter(this, ItemsInteractor(ItemsRepositoryImpl()))

        itemsAdapter = ItemsAdapter(this)

        viewBinding.recyclerview.adapter = itemsAdapter

        itemsPresenter.getDate()
    }

    override fun onClick() {
        itemsPresenter.imageViewClicked()
    }

    override fun onElementSelected(name: String, date: String, imageView: Int) {
        itemsPresenter.elementSelected(name, date, imageView)
    }

    companion object {
        // we can used it, because we see where we get it
        const val DATE = "date"
    }

    override fun dataReceived(list: List<ItemsModel>) {
        itemsAdapter.submitList(list)
    }

    override fun imageViewClicked(msg: Int) {
        Toast.makeText(context, getString(msg), Toast.LENGTH_SHORT).show()
    }

    override fun goToDetails(name: String, date: String, imageView: Int) {
        val detailsFragment = DetailsFragment()
        val bundle = Bundle()
        bundle.putString(NAME, name)
        bundle.putString(DATE, date)
        bundle.putInt(IMAGE_VIEW, imageView)
        detailsFragment.arguments = bundle

        parentFragmentManager.beginTransaction()
            .replace(R.id.activity_container, detailsFragment)
            .addToBackStack(DETAILS)
            .commit()
    }
}