package com.example.restaurantapp.database

import androidx.lifecycle.LiveData

class UserRepository(private val userDao: UserDao) {
    val readAllDatabase: LiveData<List<User>> = userDao.readAllData()

    suspend fun addUser(user: User){
        userDao.addUser(user)
    }
}