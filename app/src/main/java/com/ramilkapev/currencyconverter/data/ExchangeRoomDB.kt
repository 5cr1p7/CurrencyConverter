package com.ramilkapev.currencyconverter.data

import android.content.Context
import androidx.room.*
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.sqlite.db.SupportSQLiteOpenHelper
import com.ramilkapev.currencyconverter.ExchangeRates

/* Тут должна быть БД, но у меня возникла проблема с добавлением Map<String, Double>
    в нее. Я понимаю, что это как-то можно реализовать через конвертеры Room,
    но почему-то не получилось...
 */

//@Database(entities = [ExchangeRates::class], version = 1)
//abstract class ExchangeRoomDB: RoomDatabase() {
//    abstract fun exchangeDao(): ExchangeDao
//
//    companion object {
//        @Volatile
//        private var INSTANCE: ExchangeRoomDB? = null
//
//        fun getDatabase(
//            context: Context
//        ): ExchangeRoomDB {
//            return INSTANCE ?: synchronized(this) {
//                val instance = Room.databaseBuilder(
//                    context.applicationContext,
//                    ExchangeRoomDB::class.java,
//                    "rates_database"
//                ).build()
//                INSTANCE = instance
//                instance
//            }
//        }
//
//        private class WordDatabaseCallback() : RoomDatabase.Callback() {
//            /**
//             * Override the onCreate method to populate the database.
//             */
//            override fun onCreate(db: SupportSQLiteDatabase) {
//                super.onCreate(db)
//                // If you want to keep the data through app restarts,
//                // comment out the following line.
////                INSTANCE?.let { database ->
////                    scope.launch(Dispatchers.IO) {
////                        populateDatabase(database.wordDao())
////                    }
////                }
//            }
//        }
//
//        /**
//         * Populate the database in a new coroutine.
//         * If you want to start with more words, just add them.
//         */
////        suspend fun populateDatabase(wordDao: WordDao) {
////            // Start the app with a clean database every time.
////            // Not needed if you only populate on creation.
////            wordDao.deleteAll()
////
////            var word = Word("Hello")
////            wordDao.insert(word)
////            word = Word("World!")
////            wordDao.insert(word)
////        }
//    }
//}