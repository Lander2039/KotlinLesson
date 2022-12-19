package com.example.kotlinlesson.domain.auth

import com.example.kotlinlesson.domain.model.UserModel

interface AuthRepository {

    fun LoginUser(userName: String, userPassword: String)

    fun showUserCreds() :UserModel

    fun doesUserExist(): Boolean

    fun userLogout()
}