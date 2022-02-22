package com.dyh.firistcode.fragment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.fragment.app.Fragment
import com.dyh.firistcode.R

class FragmentActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment)
        val mBtnLeft = findViewById<Button>(R.id.btn_left_fragment)
        mBtnLeft.setOnClickListener {

            val fragment: Fragment = RightFragment() as Fragment
            val fragmentManager = supportFragmentManager
            val transaction = fragmentManager.beginTransaction()
            transaction.replace(R.id.layout_right, fragment)
            transaction.addToBackStack(null)
            transaction.commit()

            //在activity中获取fragment实例
            val fragment1 = fragmentManager.findFragmentById(R.id.fragment_left) as LeftFragment

        }
    }
}