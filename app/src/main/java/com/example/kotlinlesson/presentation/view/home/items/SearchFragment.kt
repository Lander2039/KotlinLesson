package com.example.kotlinlesson.presentation.view.home.items

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.Button
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.kotlinlesson.R
import com.example.kotlinlesson.databinding.FragmentSearchBinding
import com.example.kotlinlesson.presentation.view.home.items.service.MusicPlayer
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : Fragment() {

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    private val viewModel: SearchViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btn = Button(context)
        btn.background = context?.getDrawable(R.drawable.mango)
        btn.text = context?.getText(R.string.app_name)
        binding.root.addView(btn)

        AnimationUtils.loadAnimation(context, R.anim.rotate_anim).also {
            binding.start.startAnimation(it)
        }

//        ObjectAnimator
        val animatorSet = AnimatorSet()
        val y = ObjectAnimator.ofFloat(binding.start, "scaleY", 3f, 1f)
        val x = ObjectAnimator.ofFloat(binding.start, "scaleX", 3f, 1f)

        animatorSet.playTogether(x, y)

        animatorSet.start()
//        ValueAnimator
        val translate = ValueAnimator.ofFloat(2f, 1.5f)
        translate.addUpdateListener { animation ->
            val scale = animation.animatedValue.toString().toFloat()
            binding.stop.setScaleX(scale)
            binding.stop.setScaleY(scale)
        }
        translate.start()
        translate.repeatCount = 3


        binding.start.setOnClickListener {

            requireActivity().startForegroundService(
                Intent(
                    requireContext(),
                    MusicPlayer::class.java
                )
            )
        }

        binding.stop.setOnClickListener {
            requireActivity().stopService(Intent(requireContext(), MusicPlayer::class.java))
        }

        binding.search.setOnQueryTextListener(object :
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                viewModel.findItem(p0 ?: "")
                return false
            }
        })

        viewModel.item.observe(viewLifecycleOwner) {
            binding.description.text = it.description
            Picasso.get().load(Uri.parse(it.image)).into(binding.image)
        }

    }
}