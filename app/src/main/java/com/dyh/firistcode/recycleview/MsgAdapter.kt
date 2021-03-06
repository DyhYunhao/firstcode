package com.dyh.firistcode.recycleview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dyh.firistcode.R
import java.lang.IllegalArgumentException

class MsgAdapter(val msgList: List<Msg>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

//    inner class LeftViewHolder(view: View): RecyclerView.ViewHolder(view) {
//        val leftMsg: TextView = view.findViewById(R.id.tv_left_msg)
//    }

//    inner class RightViewHolder(view: View): RecyclerView.ViewHolder(view) {
//        val rightMsg: TextView = view.findViewById(R.id.tv_right_msg)
//    }

    override fun getItemViewType(position: Int): Int {
        val msg = msgList[position]
        return msg.type
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        if (viewType == Msg.TYPE_RECEIVED) {
            val view =
                LayoutInflater.from(parent.context).inflate(R.layout.item_msg_left, parent, false)
            LeftViewHolder(view)
        } else {
            val view =
                LayoutInflater.from(parent.context).inflate(R.layout.item_msg_right, parent, false)
            RightViewHolder(view)
        }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val msg = msgList[position]
        when (holder) {
            is LeftViewHolder -> holder.leftMsg.text = msg.content
            is RightViewHolder -> holder.rightMsg.text = msg.content
        }
    }

    override fun getItemCount(): Int {
        return msgList.size
    }
}