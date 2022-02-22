package com.dyh.firistcode.recycleview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dyh.firistcode.R

class RcvActivity : AppCompatActivity() {
    private val msgList = ArrayList<Msg>()
//    private var adapter: MsgAdapter? = null
    private lateinit var adapter: MsgAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rcv)
        initData()
        val layoutManager = LinearLayoutManager(this)
        val recyclerView = findViewById<RecyclerView>(R.id.rcv_test1)
        recyclerView.layoutManager = layoutManager
        adapter = MsgAdapter(msgList)
        recyclerView.adapter = adapter
        val inputText = findViewById<EditText>(R.id.et_input)
        findViewById<Button>(R.id.btn_send).setOnClickListener{
            val content = inputText.text.toString()
            if (content.isNotEmpty()) {
                val msg = Msg(content, Msg.TYPE_SEND)
                msgList.add(msg)
                adapter.notifyItemInserted(msgList.size - 1)
                recyclerView.scrollToPosition(msgList.size - 1)
                inputText.setText("")
            }
        }

    }

    private fun initData() {
        val msg1 = Msg("hello", Msg.TYPE_RECEIVED)
        val msg2 = Msg("hello, world, i am 1", Msg.TYPE_SEND)
        val msg3 = Msg("i am 2, nice to meet you", Msg.TYPE_RECEIVED)
        val msg4 = Msg("me too, bye", Msg.TYPE_SEND)
        val msg5 = Msg("bye", Msg.TYPE_RECEIVED)

        msgList.apply {
            add(msg1)
            add(msg2)
            add(msg3)
            add(msg4)
            add(msg5)
        }
    }


}