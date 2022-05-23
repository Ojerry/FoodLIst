package com.ojinc.foodlist

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import org.w3c.dom.Text
import android.media.Image
import androidx.appcompat.app.AppCompatActivity

class DetailActivity : AppCompatActivity() {
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_detail)

            val headingFoods : TextView = findViewById(R.id.heading)
            val mainDetails : TextView = findViewById(R.id.details)
            val foodImage : ImageView = findViewById(R.id.food_image)

            val bundle : Bundle?= intent.extras
            val heading = bundle!!.getString("heading")
            val imageId = bundle.getInt("imageId")
            val details = bundle.getString("details")

            headingFoods.text = heading
            mainDetails.text = details
            foodImage.setImageResource(imageId)

    }
}