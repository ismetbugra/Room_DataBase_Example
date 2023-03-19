package com.example.databaseroom.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class UserDatabase:RoomDatabase() {
    abstract fun userDao():UserDao

    companion object{
        @Volatile private var instance : UserDatabase?=null

        fun getDatabase(context: Context):UserDatabase{
            val tempInstance=instance
            if(tempInstance!=null){
                return tempInstance
            }
            synchronized(this){
                val instancex=Room.databaseBuilder(context.applicationContext,
                UserDatabase::class.java,
                "user_database").build()
                instance=instancex
                return instancex
            }
        }
    }

}