package com.example.kotlinlesson.presentation.view.home.items

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinlesson.App
import com.example.kotlinlesson.R
import com.example.kotlinlesson.databinding.FragmentItemsBinding
import com.example.kotlinlesson.presentation.adapter.ItemsAdapter
import com.example.kotlinlesson.presentation.adapter.listener.ItemsListener
import com.example.kotlinlesson.presentation.view.ItemsViewModel
import com.example.kotlinlesson.utils.BaseFragment
import com.example.kotlinlesson.utils.BundleConstants
import com.example.kotlinlesson.utils.BundleConstants.DESCRIPTION
import com.example.kotlinlesson.utils.NavHelper.navigateWithBundle
import kotlinx.coroutines.flow.catch


//not use
//const val NAME = "name"
//private const val DETAILS = "Details"


class ItemsFragment : BaseFragment(), ItemsListener {

    private lateinit var itemsAdapter: ItemsAdapter


    private val viewModel: ItemsViewModel by viewModels { viewModelFactory }

    private var _binding: FragmentItemsBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentItemsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireActivity().applicationContext as App).provideAppComponent().inject(this)

        itemsAdapter = ItemsAdapter(this)
        val recycledView = view.findViewById<RecyclerView>(R.id.recyclerview)
        recycledView.layoutManager = LinearLayoutManager(context)
        recycledView.adapter = itemsAdapter

        viewModel.showData()

        viewModel.items.observe(viewLifecycleOwner) { listItems ->
            itemsAdapter.submitList(listItems)
        }

        viewModel.msg.observe(viewLifecycleOwner) { msg ->
            Toast.makeText(context, getString(msg), Toast.LENGTH_SHORT).show()
        }

        viewModel.error.observe(viewLifecycleOwner) {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
        }

        viewModel.bundle.observe(viewLifecycleOwner) { navBundle ->
            if (navBundle != null) {
                val bundle = Bundle()
                bundle.putString(DESCRIPTION, navBundle.description)
                bundle.putString(BundleConstants.IMAGE_VIEW, navBundle.image)



                navigateWithBundle(
                    navBundle.destinationId, bundle
                )
                viewModel.userNavigated()
            }
        }
    }

    override fun onClick() {
        viewModel.imageViewClicked()
    }

    override fun onElementSelected(description: String, image: String) {
        viewModel.elementClicked(description, image)

    }

    override fun onDeleteClicked(description: String) {
        viewModel.deleteItem(description)
    }

    override fun onFavClicked(description: String, isFavorite: Boolean) {
        viewModel.onFavClicked(description, isFavorite)
    }

    companion object {
        // we can used it, because we see where we get it
//        const val DATE = "date"
    }
}