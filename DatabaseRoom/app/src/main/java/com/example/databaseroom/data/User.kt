package com.example.databaseroom.data

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

// user classının argument olarak gonderilebilmsei için parcelize yaptık
@Parcelize
@Entity(tableName = "user_table")
class User(
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    @ColumnInfo("firstName")
    val firstName:String,
    @ColumnInfo("lastName")
    val lastName:String,
    @ColumnInfo("age")
    val age:Int
):Parcelable {
}