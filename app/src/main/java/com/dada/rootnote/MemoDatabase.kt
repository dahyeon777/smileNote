package com.dada.rootnote

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [Memo::class],version = 1)
abstract class AppDatabase: RoomDatabase(){

    abstract fun MemoDAO(): MemoDAO

    companion object{

        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase?{
            if(INSTANCE == null){

                INSTANCE = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database")
                    .build()

            }

            return INSTANCE

        }
    }
}
