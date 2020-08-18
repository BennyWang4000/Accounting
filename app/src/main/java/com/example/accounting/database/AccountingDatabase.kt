package com.example.accounting.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.accounting.database.dao.AccountingDao
import com.example.accounting.database.model.AccountEntity
import com.example.accounting.database.model.DateEntity
import com.example.accounting.database.model.ItemEntity
import com.example.accounting.database.model.TypeEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = [ItemEntity::class, TypeEntity::class, DateEntity::class, AccountEntity::class], version = 5)
abstract class AccountingDatabase : RoomDatabase(){

    //取得 dao 實體(?
    abstract fun getItemDao(): AccountingDao

    //官方推薦的 Singleton 寫法，因為實體的產生很耗資源，而且也不需要多個資料庫實體
    companion object {
        @Volatile
        private var INSTANCE: AccountingDatabase? = null

        //回傳此 class
        fun getDatabase(context: Context, scope: CoroutineScope
        ): AccountingDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance =
                    Room
                        .databaseBuilder(
                            context.applicationContext,
                            AccountingDatabase::class.java,
                    "accounting_database")
                    // Wipes and rebuilds instead of migrating if no Migration object.
                    // Migration is not part of this codelab.
                        .fallbackToDestructiveMigration()
                        .addCallback(ItemDatabaseCallback(scope))
                        .build()
                INSTANCE = instance
                // return instance
                instance
            }
        }


        private class ItemDatabaseCallback(
            private val scope: CoroutineScope
        ) : RoomDatabase.Callback() {
            /**
             * Override the onOpen method to populate the database.
             * For this sample, we clear the database every time it is created or opened.
             */
            override fun onOpen(db: SupportSQLiteDatabase) {
                super.onOpen(db)
                // If you want to keep the data through app restarts,
                // comment out the following line.
                INSTANCE?.let { database ->
                    scope.launch(Dispatchers.IO) {
                        populateDatabase(database.getItemDao())
                    }
                }
            }
        }

        /**
         * Populate the database in a new coroutine.
         * If you want to start with more words, just add them.
         */
        fun populateDatabase(accountingDao: AccountingDao) {
            // Start the app with a clean database every time.
            // Not needed if you only populate on creation.
//            listDao.deleteAll()

        }
    }

}