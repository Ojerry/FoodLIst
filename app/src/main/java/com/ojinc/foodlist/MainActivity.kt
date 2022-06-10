package com.ojinc.foodlist

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager

class MainActivity : AppCompatActivity() {

    private lateinit var newRecyclerView: RecyclerView
    private lateinit var  newArrayList: ArrayList<Foods>
    private var isLinearLayoutManager = 1
    lateinit var imageId : Array<Int>
    lateinit var heading : Array<String>
    lateinit var price   : Array<String>
    lateinit var details : Array<String>



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        chooseLayout()
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
            "Jollof Rice",
            "Ogbono Soup",
            "Salad",
            "Spaghetti",
            "Yam Porridge",
            "Gbegiri",
            "Banga soup",
            "Beans and Dodo",
            "Rice and Stew"
        )

        details = arrayOf(
            getString(R.string.amala),
            getString(R.string.egusi),
            getString(R.string.jollof),
            getString(R.string.ogbono),
            getString(R.string.salad),
            getString(R.string.spaghetti),
            getString(R.string.yam_porridge),
            getString(R.string.gbegiri),
            getString(R.string.banga),
            getString(R.string.beans_and_dodo),
            getString(R.string.rice_stew)
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

        var adapter = MyAdapter(newArrayList)
        newRecyclerView.adapter = adapter
        adapter.setOnItemClickListener(object : MyAdapter.onItemClickListener{
            override fun onItemClick(position: Int) {
//                val intent = Intent(this@MainActivity, DetailFragment::class.java)
//                intent.putExtra("heading",newArrayList[position].heading)
//                intent.putExtra("imageId",newArrayList[position].foodImage)
//                intent.putExtra("details",details[position])
//                startActivity(intent)
                val fragmentManager = supportFragmentManager
                val fragmentTransaction = fragmentManager.beginTransaction()
                val mFragment = DetailFragment()

                fragmentManager.findFragmentByTag(DetailFragment::class.java.simpleName)
                val mBundle = Bundle()
                mBundle.putString("heading", newArrayList[position].heading)
                mBundle.putInt("imageId", newArrayList[position].foodImage)
                mBundle.putString("details", details[position])
                mFragment.arguments = mBundle
                fragmentTransaction.add(R.id.fragmentContainer, mFragment, DetailFragment::class.java.simpleName).addToBackStack(null).commitAllowingStateLoss()
//                fragmentTransaction.replace(R.id.fragmentContainer, mFragment).addToBackStack(null).commit()
//                fragmentTransaction.replace(R.id.fragmentContainer, mFragment).commit()
                val view : View = findViewById(R.id.recyclerVeiw)
                view.visibility = View.GONE
            }
        })
    }

    override fun onBackPressed()
    {
        val fragmentManager = supportFragmentManager
//        val fragmentTransaction = fragmentManager.beginTransaction()
//        val mFragment = DetailFragment()
        val currentFragment =this@MainActivity.supportFragmentManager.findFragmentById(R.id.fragmentContainer)
        if(currentFragment is DetailFragment)
        {
//            Toast.makeText(this, "There", Toast.LENGTH_SHORT).show()

            fragmentManager.popBackStack()

            val view : View = findViewById(R.id.recyclerVeiw)
            view.visibility = View.VISIBLE
//                fragmentTransaction.remove(DetailFragment()).commit()
        }
        else{
            super.onBackPressed()
        }
    }


    private fun chooseLayout() {
        if (isLinearLayoutManager == 1) {
            newRecyclerView.layoutManager = LinearLayoutManager(this)
        } else if (isLinearLayoutManager == 2) {
            newRecyclerView.layoutManager = GridLayoutManager(this, 2)
        } else {
            newRecyclerView.layoutManager = StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)
//        newRecyclerView.adapter = MyAdapter()
        }
    }

    private fun setItem(menuItem: MenuItem?) {
        if (menuItem == null) return
        fun icon(): Drawable? {
            if (isLinearLayoutManager == 1){
                return ContextCompat.getDrawable(this, R.drawable.ic_list_view)
            } else if(isLinearLayoutManager == 2){
             return   ContextCompat.getDrawable(this, R.drawable.ic_grid_view)
            } else {
                return ContextCompat.getDrawable(this, R.drawable.ic_staggered_view)
            }
        }
        menuItem.icon = icon()
//                if(isLinearLayoutManager == 1) {
//                    ContextCompat.getDrawable(this, R.drawable.ic_list_view)
//                } else {
//                ContextCompat.getDrawable(this, R.drawable.ic_grid_view)
//        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.layout_menu, menu)

        val layoutButton = menu?.findItem(R.id.action_switch)
        setItem(layoutButton)
        return true
    }
    fun switch(): Int {
        isLinearLayoutManager++
        if (isLinearLayoutManager == 4){
            isLinearLayoutManager = 1
        }
        return isLinearLayoutManager
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.action_switch -> {
                isLinearLayoutManager = switch()

                chooseLayout()
                setItem(item)

                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}