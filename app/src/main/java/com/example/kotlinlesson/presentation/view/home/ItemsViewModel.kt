package com.example.kotlinlesson.presentation.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlinlesson.R
import com.example.kotlinlesson.domain.items.ItemsInteractor
import com.example.kotlinlesson.domain.model.ItemsModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ItemsViewModel @Inject constructor(private val itemsInteractor: ItemsInteractor) :
    ViewModel() {

    private val _items = MutableLiveData<List<ItemsModel>>()
    val items: LiveData<List<ItemsModel>> = _items

    private val _msg = MutableLiveData<Int>()
    val msg: LiveData<Int> = _msg

    private val _bundle = MutableLiveData<NavigateWithBundle?>()
    val bundle: LiveData<NavigateWithBundle?> = _bundle

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    fun getData() {
        viewModelScope.launch {
            try {
                val listItems = itemsInteractor.getData()
                _items.value = listItems
            } catch (e:Exception){
                _error.value = e.message.toString()
            }
        }
    }

    fun imageViewClicked() {

        _msg.value = R.string.Imageviewclicked
    }

    fun elementClicked(description: String, image: String) {
        _bundle.value = NavigateWithBundle(
            description,
            image,
            destinationId = R.id.action_itemsFragment_to_detailsFragment
        )
    }

    fun userNavigated() {
        _bundle.value = null
    }
}

data class NavigateWithBundle(
    val description: String,
    val image: String,
    val destinationId: Int
)


