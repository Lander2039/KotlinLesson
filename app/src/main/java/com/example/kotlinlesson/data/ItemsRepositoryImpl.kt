package com.example.kotlinlesson.data

import com.example.kotlinlesson.R
import com.example.kotlinlesson.domain.ItemsRepository
import com.example.kotlinlesson.presentation.model.ItemsModel
import javax.inject.Inject

class ItemsRepositoryImpl @Inject constructor() : ItemsRepository {
    override fun getData(): List<ItemsModel> {
        val listItems = listOf<ItemsModel>(
            ItemsModel(
                R.drawable.android,
                "Android",
                "26/02/2003"
            ),
            ItemsModel(
                R.drawable.ios,
                "IOS",
                "16/02/2021"
            ),
            ItemsModel(
                R.drawable.xamarin,
                "Xamarin",
                "21/12/2022"
            ),
            ItemsModel(
                R.drawable.net,
                ".Net",
                "23/05/2013"
            ),
            ItemsModel(
                R.drawable.cplusplus,
                "C++",
                "16/03/2013"
            ),
            ItemsModel(
                R.drawable.cplusplus,
                "C",
                "22/02/2010"
            ),
            ItemsModel(
                R.drawable.golang,
                "Golang",
                "23/04/2041"
            ),
            ItemsModel(
                R.drawable.php,
                "PHP",
                "26/03/2023"
            ),
            ItemsModel(
                R.drawable.ruby,
                "Ruby",
                "26/02/2016"
            ),
            ItemsModel(
                R.drawable.js,
                "JS",
                "01/05/2022"
            )
        )
        return listItems
    }
}