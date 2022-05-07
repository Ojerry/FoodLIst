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
                    R.drawable.yam_porridge
        )

        heading = arrayOf(
            "amala, as solid as it gets",
            "egusi, as tasty as it gets",
            "jollof, everybodys favourite",
            "ogbono, my own personal favourite",
            "salad, damn what we should all take more of",
            "spaghetti, italian meal, hehehe",
            "good porridge can save lives..."
        )

        newRecyclerView = findViewById(R.id.recyclerVeiw)
        newRecyclerView.layoutManager = LinearLayoutManager(this)
        newRecyclerView.setHasFixedSize(true)

        newArrayList = arrayListOf<Foods>()
        getUserData()



    }

    private fun getUserData() {
        for(i in imageId.indices){
            val foods = Foods(imageId[i],heading[i])
            newArrayList.add(foods)
        }
        newRecyclerView.adapter = MyAdapter(newArrayList)
    }
}