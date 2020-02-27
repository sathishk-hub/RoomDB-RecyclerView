package com.example.roomdbexample.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

// Annotates class to be a Room Database with a table (entity) of the Word class
@Database(entities = arrayOf(Word::class),version = 1,exportSchema = false)
abstract class WordRoomDB : RoomDatabase() {
    abstract fun wordDao(): WordDao


    private class WordDatabaseCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {

        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onOpen(db)
            INSTANCE?.let { database ->
                scope.launch {
                    populateDatabase(database.wordDao())
                }
            }
        }

        suspend fun populateDatabase(wordDao: WordDao) {


            // Add sample words.
            var word = Word(Word = "Hello")
            wordDao.insert(word)
            word = Word(Word = "World!")
            wordDao.insert(word)

            // TODO: Add your own words!
        }
    }

    companion object {

        //singleton prevents multiple instances of database opening
        // at the same time
        @Volatile
        private var INSTANCE: WordRoomDB? = null

        fun getDatabase(
            context: Context,
            scope: CoroutineScope
        ): WordRoomDB {
            val tempInstance =
                INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    WordRoomDB::class.java,
                    "word_database"
                )
                    .addCallback(WordDatabaseCallback(scope))
                    .build()

                INSTANCE = instance
                return instance

            }
        }
    }

}