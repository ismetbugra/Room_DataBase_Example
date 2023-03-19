package com.example.databaseroom.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface UserDao {

            // eklenen veri daha önce eklenmisse eklemez
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addUser(user:User)

    @Update
    suspend fun updateUser(user: User)

    @Delete
    suspend fun deleteUser(user: User)

    @Query("DELETE FROM user_table")
    suspend fun deleteAllUsers()

   @Query("SELECT*FROM user_table ORDER BY id ASC")
    fun readAllData():LiveData<List<User>>


}