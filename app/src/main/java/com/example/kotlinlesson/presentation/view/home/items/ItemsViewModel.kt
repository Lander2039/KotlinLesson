package com.example.kotlinlesson.presentation.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlinlesson.R
import com.example.kotlinlesson.domain.items.ItemsInteractor
import com.example.kotlinlesson.domain.model.ItemsModel
import io.reactivex.disposables.CompositeDisposable
import kotlinx.coroutines.launch
import javax.inject.Inject


class ItemsViewModel @Inject constructor(private val itemsInteractor: ItemsInteractor) :
    ViewModel() {

    private val _items = MutableLiveData<List<ItemsModel>>()
    val items: LiveData<List<ItemsModel>> = _items

//    val items = flow { emit(itemsInteractor.showData()) }

//    val getData = flow { emit((itemsInteractor.getData())) }

//    private val _trigger = MutableLiveData<Flow<Unit>>()
//    val trigger = _trigger

    private val _msg = MutableLiveData<Int>()
    val msg: LiveData<Int> = _msg

    private val _bundle = MutableLiveData<NavigateWithBundle?>()
    val bundle: LiveData<NavigateWithBundle?> = _bundle

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    private val compositeDisposable = CompositeDisposable()


//    fun getData() {
//        viewModelScope.launch {
//            _trigger.value = flow { emit(itemsInteractor.getData()) }
//        }
//    }

    fun getData() {
        val getDate = itemsInteractor.getData().subscribe({

        }, {

        })
        compositeDisposable.add(getDate)
        val showData = itemsInteractor.showData().subscribe({
            _items.value = it
        }, {
            _error.value = "Error occurred while showing data"
        })
        compositeDisposable.add(showData)
    }

    suspend fun getDataSimple() {
        itemsInteractor.getData()
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

    fun deleteItem(description: String) {
        viewModelScope.launch {
            itemsInteractor.deleteItemByDescription(description)
        }
    }

    fun onFavClicked(description: String, isFavorite: Boolean) {
        viewModelScope.launch {
            itemsInteractor.onFavClicked(description, isFavorite)
        }
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}

data class NavigateWithBundle(
    val description: String,
    val image: String,
    val destinationId: Int
)


