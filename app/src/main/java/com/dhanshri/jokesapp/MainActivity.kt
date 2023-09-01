package com.dhanshri.jokesapp

import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.dhanshri.jokesapp.databinding.ActivityMainBinding
import com.dhanshri.jokesapp.model.Meme
import com.dhanshri.jokesapp.viewmodel.HomeViewModel
import com.dhanshri.jokesapp.viewmodel.HomeViewModelFactory
import com.dhanshri.jokesapp.viewmodel.MyAdapter

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: HomeViewModel
    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val repository = (application as AppApplication).repository

        viewModel = ViewModelProvider(this, HomeViewModelFactory(repository)).get(HomeViewModel::class.java)

        viewModel.memes.observe(this) {
//            Log.d("API_RESPONSE", "onCreate: ${it.data}")

            initRecyclerView(it.data.memes)
            it.data.memes.iterator().forEach { meme ->
                Log.d("API_RESPONSE", "onCreate: ${meme.name}")
            }
        }
    }
    fun initRecyclerView (memes: List<Meme>){
        binding.recyclerview.layoutManager = LinearLayoutManager(this)

        val adapter = MyAdapter(memes) {meme ->
            val dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_layout, null)
            val dialogBuilder = AlertDialog.Builder(this, AlertDialog.THEME_HOLO_LIGHT)
                .setView(dialogView)
                .setPositiveButton("Close") { dialog, _ ->
                    dialog.dismiss()
                }
            window.setBackgroundDrawableResource(android.R.color.white)
            val dialog = dialogBuilder.create()

            val imageView = dialogView.findViewById<ImageView>(R.id.imageView)

            Glide.with(this)
                .load(meme.url)
                .into(imageView)

            dialog.show()

        }

        binding.recyclerview.adapter = adapter

    }
}