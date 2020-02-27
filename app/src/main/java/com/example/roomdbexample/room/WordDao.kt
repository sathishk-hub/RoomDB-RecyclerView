package com.example.roomdbexample.room

import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface WordDao {

    // allowing the insert of the same word multiple times by passing a
    // conflict resolution strategy
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(word: Word)

    @Query("DELETE FROM word_table")
    suspend fun deleteAll()

    @Query("DELETE FROM word_table WHERE id = :Id")
    suspend fun delete(Id:Int?)

    @Query("SELECT * from word_table ORDER BY word ASC")
    fun getAlphabetizedWords():LiveData<List<Word>>
}