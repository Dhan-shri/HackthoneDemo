package com.dhanshri.jokesapp.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.dhanshri.jokesapp.model.Meme


@Database(entities = [Meme::class], version = 1, exportSchema = false)
abstract class MainDatabase: RoomDatabase(){

    abstract fun getRoomDao(): RoomDao

    companion object{
        private var INSTANCE: MainDatabase? = null

        fun getDatabase(context: Context): MainDatabase{
            if(INSTANCE == null){
                INSTANCE = Room.databaseBuilder(
                    context,
                    MainDatabase::class.java,
                    "MainDB"
                ).build()
            }
            return INSTANCE!!
        }
    }
}