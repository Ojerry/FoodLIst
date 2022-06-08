package com.ojinc.foodlist

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

class DetailFragment : Fragment() {
    private lateinit var headingFoods : TextView
    private lateinit var mainDetails : TextView
    private lateinit var foodImage : ImageView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view : View =  inflater.inflate(R.layout.fragment_detail, container, false)

        headingFoods = view.findViewById<View>(R.id.heading) as TextView
        mainDetails = view.findViewById<View>(R.id.details) as TextView
        foodImage = view.findViewById<View>(R.id.food_image) as ImageView
        // Inflate the layout for this fragment

//        val bundle = arguments
        val bundle : Bundle?= arguments
        val heading = bundle!!.getString("heading")
        val imageId = bundle.getInt("imageId")
        val details = bundle.getString("details")

        headingFoods.text = heading
        mainDetails.text = details
        foodImage.setImageResource(imageId)

        return view
    }

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//
//    }

}