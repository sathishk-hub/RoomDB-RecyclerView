package com.example.roomdbexample.room

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "word_table")
class Word (

    @PrimaryKey(autoGenerate = true) val id:Int?=null,
    @NonNull@ColumnInfo(name = "word")
     val name: String


)