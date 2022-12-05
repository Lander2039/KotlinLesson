package com.example.kotlinlesson.presentation.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.kotlinlesson.R
import com.example.kotlinlesson.databinding.FragmentDetailsBinding
import com.example.kotlinlesson.databinding.FragmentOnBoardingBinding
import com.example.kotlinlesson.utils.BundleConstants.IMAGE_VIEW
import com.example.kotlinlesson.utils.BundleConstants.NAME
import com.example.kotlinlesson.presentation.view.ItemsFragment.Companion.DATE

class DetailsFragment : Fragment() {

    private var _viewBinding: FragmentDetailsBinding? = null
    private val viewBinding get() = _viewBinding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _viewBinding = FragmentDetailsBinding.inflate(inflater)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val bundle = arguments
        bundle?.let { safeBundle ->
            val name = safeBundle.getString(NAME)
            val date = safeBundle.getString(DATE)
            val image = safeBundle.getInt(IMAGE_VIEW)

            viewBinding.detailsName.text = name
            viewBinding.detailsDate.text = date
            viewBinding.detailsImage.setBackgroundResource(image)

        }
    }
}