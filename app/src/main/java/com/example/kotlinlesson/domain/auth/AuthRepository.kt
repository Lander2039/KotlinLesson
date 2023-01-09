package com.example.kotlinlesson.domain.auth

import com.example.kotlinlesson.domain.model.UserModel

interface AuthRepository {

   suspend fun LoginUser(userName: String, userPassword: String)

   suspend fun showUserCreds() :UserModel

   suspend fun doesUserExist(): Boolean

   suspend fun userLogout()
}