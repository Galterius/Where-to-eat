package com.example.restaurantapp.repository

import androidx.lifecycle.LiveData
import com.example.restaurantapp.database.UserDao
import com.example.restaurantapp.model.User

class UserRepository(private val userDao: UserDao) {
    val readAllDatabase: LiveData<List<User>> = userDao.readAllData()

    suspend fun addUser(user: User){
        userDao.addUser(user)
    }

    suspend fun updateUser(user: User){
        userDao.updateUser(user)
    }

    suspend fun deleteUser(user: User){
        userDao.deleteUser(user)
    }

    suspend fun deleteAllUser(){
        userDao.deleteAllUser()
    }
}