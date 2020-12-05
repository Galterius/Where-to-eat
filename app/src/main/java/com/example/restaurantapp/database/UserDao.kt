package com.example.restaurantapp.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.restaurantapp.model.User


//Dao = data access object
@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addUser(user: User)

    @Query("SELECT * FROM USER_TABLE")
    fun readAllData(): LiveData<List<User>>

    @Update
    suspend fun updateUser(user: User)

    @Delete
    suspend fun deleteUser(user: User)

    @Query("DELETE FROM USER_TABLE")
    suspend fun deleteAllUser()
}