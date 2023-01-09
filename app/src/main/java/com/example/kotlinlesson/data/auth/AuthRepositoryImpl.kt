package com.example.kotlinlesson.data.auth

import com.example.kotlinlesson.data.sharedPreferences.SharedPreferencesHelper
import com.example.kotlinlesson.domain.auth.AuthRepository
import com.example.kotlinlesson.domain.model.UserModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(private val sharedPreferencesHelper: SharedPreferencesHelper) :
    AuthRepository {

    override suspend fun LoginUser(userName: String, userPassword: String) {
        withContext(Dispatchers.IO) {
            sharedPreferencesHelper.saveUserName(userName)
            sharedPreferencesHelper.saveUserPassword(userPassword)
        }
    }

    override suspend fun showUserCreds(): UserModel {
        return withContext(Dispatchers.IO) {
            sharedPreferencesHelper.getUserCreds()
        }
    }

    override suspend fun doesUserExist(): Boolean {
        return withContext(Dispatchers.IO) {
            sharedPreferencesHelper.checkUserExists()
        }
        }

    override suspend fun userLogout() {
        return withContext(Dispatchers.IO) {
            sharedPreferencesHelper.removeUser()
        }
    }
}