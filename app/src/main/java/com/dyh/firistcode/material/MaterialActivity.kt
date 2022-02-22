package com.dyh.firistcode.material

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.dyh.firistcode.R
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import kotlin.concurrent.thread

class MaterialActivity : AppCompatActivity() {

    val fruits = mutableListOf(Fruit("Apple", R.drawable.apple), Fruit("Banana", R.drawable.banana),
        Fruit("Orange", R.drawable.orange), Fruit("Watermelon", R.drawable.watermelon),
        Fruit("Pear", R.drawable.pear), Fruit("Grape", R.drawable.grape),
        Fruit("Pineapple", R.drawable.pineapple), Fruit("Strawberry", R.drawable.strawberry),
        Fruit("Cherry", R.drawable.cherry), Fruit("Mango", R.drawable.mango))

    val fruitList = ArrayList<Fruit>()

    lateinit var swipe: SwipeRefreshLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_material)
        setSupportActionBar(findViewById(R.id.tb_toolbar))
        supportActionBar?.let {
            it.setDisplayHomeAsUpEnabled(true)
            it.setHomeAsUpIndicator(R.drawable.message_right)
        }

        swipe = findViewById(R.id.srl_swipe)
        findViewById<FloatingActionButton>(R.id.fab_fab).setOnClickListener { view ->
//            Toast.makeText(this, "click fab", Toast.LENGTH_SHORT).show()
            Snackbar.make(view, "Data deleted", Snackbar.LENGTH_SHORT)
                .setAction("Undo") {
                    Toast.makeText(this, "Data restored", Toast.LENGTH_SHORT).show()
                }.show()
        }

        initFruits()
        val layoutManager = GridLayoutManager(this, 2)
        val recyclerView = findViewById<RecyclerView>(R.id.rcv_card)
        val adapter = FruitAdapter(this, fruitList)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter


        swipe.setColorSchemeResources(R.color.design_default_color_primary)
        swipe.setOnRefreshListener {
            refreshFruits(adapter)
        }
    }

    private fun refreshFruits(adapter: FruitAdapter) {
        thread {
            Thread.sleep(2000)
            runOnUiThread {
                initFruits()
                adapter.notifyDataSetChanged()
                swipe.isRefreshing = false
            }
        }
    }

    private fun initFruits() {
        fruitList.clear()
        repeat(50) {
            val index = (0 until  fruits.size).random()
            fruitList.add(fruits[index])
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> findViewById<DrawerLayout>(R.id.dl_drawer_layout).openDrawer(GravityCompat.START)
            R.id.backup -> Toast.makeText(this, "click backup", Toast.LENGTH_SHORT).show()
            R.id.delete -> Toast.makeText(this, "click delete", Toast.LENGTH_SHORT).show()
            R.id.setting -> Toast.makeText(this, "click setting", Toast.LENGTH_SHORT).show()
        }
        return true
    }
}