package com.dyh.firistcode.material

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import com.bumptech.glide.Glide
import com.dyh.firistcode.R
import com.google.android.material.appbar.CollapsingToolbarLayout

class FruitActivity : AppCompatActivity() {

    companion object {
        const val FRUIT_NAME = "fruit_name"
        const val FRUIT_IMG = "fruit_img"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fruit)
        val fruitName = intent.getStringExtra(FRUIT_NAME) ?: ""
        val fruitImgId = intent.getIntExtra(FRUIT_IMG, 0)
        setSupportActionBar(findViewById(R.id.tb_toolbar1))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        findViewById<CollapsingToolbarLayout>(R.id.ctl_collapsing).title = fruitName
        Glide.with(this).load(fruitImgId).into(findViewById(R.id.iv_fruit_img1))
        val fruitContent = findViewById<TextView>(R.id.tv_fruit_content)
        fruitContent.text = generateFruit(fruitName)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun generateFruit(fruitName: String) = fruitName.repeat(500)
}