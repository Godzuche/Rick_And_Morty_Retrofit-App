package com.example.retrofitapp

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.squareup.picasso.Picasso


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewModel: SharedViewModel by lazy {
            ViewModelProvider(this).get(SharedViewModel::class.java)
        }
        val nameTextView = findViewById<TextView>(R.id.nameTextView)
        val headerImageView = findViewById<ImageView>(R.id.headerImageView)
        val statusTextView = findViewById<TextView>(R.id.statusTextView)
        val specieTextView = findViewById<TextView>(R.id.specieTextView)
        val statusIcon = findViewById<ImageView>(R.id.statusIcon)

        viewModel.refreshCharacter(54)
       viewModel.characterByIdLiveData.observe(this) { response->
           if(response == null) {
               Toast.makeText(this@MainActivity, "Unsuccessful network call!", Toast.LENGTH_SHORT).show()
               return@observe
           }

           nameTextView.text = response.name
           statusTextView.text = response.status
           specieTextView.text = response.species

           if(response.status.equals("alive", true)) {
               statusIcon.setImageResource(R.drawable.ic_baseline_check_circle_24)
           } else {
               statusIcon.setImageResource(R.drawable.ic_baseline_check_circle__dead)
           }

           Picasso.get().load(response.image).into(headerImageView)
       }

    }
}