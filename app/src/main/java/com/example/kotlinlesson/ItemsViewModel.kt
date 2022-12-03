package com.example.kotlinlesson

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kotlinlesson.model.ItemsModel

class ItemsViewModel : ViewModel() {

    private val _items = MutableLiveData<List<ItemsModel>>()
    val items: LiveData<List<ItemsModel>> = _items

    private val _msg = MutableLiveData<String>()
    val msg: LiveData<String> = _msg

    private val _bundle = MutableLiveData<NavigateWithBundle>()
    val bundle: LiveData<NavigateWithBundle> = _bundle

    fun getData(){
        val listItems = listOf<ItemsModel>(
            ItemsModel(R.drawable.android,
                "Android",
                "26/02/2003"),
            ItemsModel(R.drawable.ios,
                "IOS",
                "16/02/2021"),
            ItemsModel(R.drawable.xamarin,
                "Xamarin",
                "21/12/2022"),
            ItemsModel(R.drawable.net,
                ".Net",
                "23/05/2013"),
            ItemsModel(R.drawable.cplusplus,
                "C++",
                "16/03/2013"),
            ItemsModel(R.drawable.cplusplus,
                "C",
                "22/02/2010"),
            ItemsModel(R.drawable.golang,
                "Golang",
                "23/04/2041"),
            ItemsModel(R.drawable.php,
                "PHP",
                "26/03/2023"),
            ItemsModel(R.drawable.ruby,
                "Ruby",
                "26/02/2016"),
            ItemsModel(R.drawable.js,
                "JS",
                "01/05/2022")
        )

        _items.value = listItems
    }

    fun  imageViewClicked(){
        _msg.value = "ImageView clicked"
    }


    fun elementClicked(name: String, date: String, imageView: Int) {
        _bundle.value = NavigateWithBundle(name = name, date = date, image = imageView)

    }

}

data class NavigateWithBundle(
    val image: Int,
    val name: String,
    val date: String
)