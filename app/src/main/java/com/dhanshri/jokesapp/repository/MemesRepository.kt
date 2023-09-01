package com.dhanshri.jokesapp.repository

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dhanshri.jokesapp.api.ApiInterface
import com.dhanshri.jokesapp.model.Data
import com.dhanshri.jokesapp.model.Jokes
import com.dhanshri.jokesapp.room.MainDatabase
import com.dhanshri.jokesapp.utils.utils

class MemesRepository(private val apiInterface: ApiInterface, private val memeDataBase: MainDatabase, private val context: Context){

    private val mLiveData = MutableLiveData<Jokes>()

    val memes : LiveData<Jokes>
    get() = mLiveData

    suspend fun getMemes() {
        if (utils.isInternetAvailable(context) == true) {
            val response = apiInterface.getJokes()
            if (response.body() != null) {

                memeDataBase.getRoomDao().insertMemes(response.body()!!.data.memes)

                mLiveData.postValue(response.body())
            }
        } else {
            val memes = memeDataBase.getRoomDao().getMemes()
            val memeList = Jokes(Data(memes), true)
            mLiveData.postValue(memeList)
        }
    }
}