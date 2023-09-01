package com.dhanshri.jokesapp

import android.app.Application
import com.dhanshri.jokesapp.api.ApiInterface
import com.dhanshri.jokesapp.api.RetrofitInstance
import com.dhanshri.jokesapp.repository.MemesRepository
import com.dhanshri.jokesapp.room.MainDatabase

class AppApplication : Application() {

    lateinit var repository: MemesRepository

        override fun onCreate() {
            super.onCreate()

            val apiInterface = RetrofitInstance.getInstance().create(ApiInterface::class.java)
            val database = MainDatabase.getDatabase(applicationContext)

            repository = MemesRepository(apiInterface, database, applicationContext)
        }
}