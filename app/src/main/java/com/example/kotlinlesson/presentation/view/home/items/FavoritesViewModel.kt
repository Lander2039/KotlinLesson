package com.example.kotlinlesson.presentation.view.home.items

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlinlesson.domain.items.ItemsInteractor
import com.example.kotlinlesson.domain.model.FavoriteModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
private val interactor: ItemsInteractor
): ViewModel() {

    private val _favorites = MutableLiveData<List<FavoriteModel>>()
    val favorites = _favorites

    fun getFavorites(){
        viewModelScope.launch {
            try {
                val favoritesItems = interactor.getFavorites()
                _favorites.value = favoritesItems
            } catch (e: Exception){
                Log.w("fav error", e.toString())
            }
        }

    }
}