package com.ojinc.foodlist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var newRecyclerView: RecyclerView
    private lateinit var  newArrayList: ArrayList<Foods>
    lateinit var imageId : Array<Int>
    lateinit var heading : Array<String>
    lateinit var price   : Array<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        imageId = arrayOf(
                    R.drawable.amala,
                    R.drawable.egusi,
                    R.drawable.jollof_rice,
                    R.drawable.ogbono,
                    R.drawable.salad,
                    R.drawable.spaghetti,
                    R.drawable.yam_porridge,
                R.drawable.gbegiri,
                R.drawable.banga_soup,
                R.drawable.beans_and_dodo,
                R.drawable.rice_stew
        )

        heading = arrayOf(
            "Amala",
            "Egusi",
            "Jollof-Rice",
            "Ogbono-Soup",
            "Salad",
            "Spaghetti",
            "Yam Porridge",
            "Gbegiri",
            "Banga soup",
            "Beans and Dodo",
            "Rice and Stew"
        )

        price = arrayOf(
            "N600",
            "N800",
            "N1600",
            "N600",
            "N800",
            "N1200",
            "N700",
            "N600",
            "N800",
            "1200",
            "N900"
        )

        newRecyclerView = findViewById(R.id.recyclerVeiw)
        newRecyclerView.layoutManager = LinearLayoutManager(this)
        newRecyclerView.setHasFixedSize(true)

        newArrayList = arrayListOf<Foods>()
        getUserData()


    }

    private fun getUserData() {
        for(i in imageId.indices){
            val foods = Foods(imageId[i],heading[i], price[i])
            newArrayList.add(foods)
        }
        newRecyclerView.adapter = MyAdapter(newArrayList)
    }
}