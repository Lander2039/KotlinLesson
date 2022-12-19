package com.example.kotlinlesson.utils

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.kotlinlesson.R

object FragmentNavigation {
    fun moveFragment (parentFragmentManager: FragmentManager, fragment: Fragment, addToBackStack: Boolean){
        if (addToBackStack) {
            parentFragmentManager
                .beginTransaction()
                .replace(R.id.activity_container, fragment)
                .addToBackStack("")
                .commit()
        } else{
            parentFragmentManager
                .beginTransaction()
                .replace(R.id.activity_container, fragment)

                .commit()
        }
    }
}