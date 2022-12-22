package com.example.kotlinlesson.presentation.view.home

import com.example.kotlinlesson.domain.auth.AuthInteractor
import javax.inject.Inject

class DetailsPresenter @Inject constructor(private val authInteractor: AuthInteractor) {

    private lateinit var detailsView: DetailsView

    fun setView(detailsFragment: DetailsFragment) {
        detailsView = detailsFragment
    }

    fun getArguments(name: String?, date: String?, imageView: Int) {
        detailsView.displayItemDate(
            when (name.isNullOrEmpty()) {
                true -> "HAHA NO DATA"
                false -> name
            },
            when (date.isNullOrEmpty()) {
                true -> "NO DATE"
                false -> date
            },
            imageView
        )
    }

    fun logoutUser() {
        authInteractor.logoutUser()
        detailsView.userLoggedOut()
    }
}