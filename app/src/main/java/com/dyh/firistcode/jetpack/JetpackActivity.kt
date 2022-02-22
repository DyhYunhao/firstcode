package com.dyh.firistcode.jetpack

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.core.content.edit
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import com.dyh.firistcode.R
import kotlinx.android.synthetic.main.activity_jetpack.*
import java.util.concurrent.TimeUnit
import kotlin.concurrent.thread

class JetpackActivity : AppCompatActivity() {

    lateinit var viewModel: MainViewModel
    lateinit var sp: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_jetpack)

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        findViewById<Button>(R.id.btn_plus).setOnClickListener {
//            viewModel.counter ++
//            refreshCounter()
            viewModel.plusOne()
        }
        findViewById<Button>(R.id.btn_clear).setOnClickListener {
//            viewModel.counter = 0
//            refreshCounter()
            viewModel.clear()
        }
//        refreshCounter()
        viewModel.counter.observe(this, Observer { count ->
            findViewById<TextView>(R.id.tv_info_text).text = count.toString()
        })

//        lifecycle.addObserver(MyObserver())

        val userDao = AppDatabase.getDatabase(this).userDao()
        val user1 = User("Tom", "Brady", 40)
        val user2 = User("Tom", "Hanks", 63)
        btn_add_user.setOnClickListener {
            thread {
                user1.id = userDao.insertUser(user1)
                user2.id = userDao.insertUser(user2)
           }
        }
        btn_update_user.setOnClickListener {
            thread {
                user1.age = 42
                userDao.updateUser(user1)
            }
        }
        btn_delete_user.setOnClickListener {
            thread {
                userDao.deleteUserByLastName("Hanks")
            }
        }
        btn_query_user.setOnClickListener {
            thread {
                for (user in userDao.loadAllUsers()) {
                    Log.d("room", "user: ${user.toString()}")
                }
            }
        }

        btn_doWorker.setOnClickListener {
            val request = OneTimeWorkRequest.Builder(SimpleWorker::class.java)
                .setInitialDelay(5, TimeUnit.MINUTES)
                .addTag("simple")
                .build()
            WorkManager.getInstance(this).enqueue(request)
        }
    }

    override fun onPause() {
        super.onPause()
        sp.edit {
            putInt("content_reserved", viewModel.counter.value ?: 0)
        }
    }

    private fun refreshCounter() {
        findViewById<TextView>(R.id.tv_info_text).text = viewModel.counter.toString()
    }
}