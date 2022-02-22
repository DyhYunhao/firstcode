package com.dyh.firistcode.recycleview

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dyh.firistcode.R

sealed class MsgViewHolder(view: View): RecyclerView.ViewHolder(view)

class LeftViewHolder(view: View): MsgViewHolder(view) {
    val leftMsg: TextView = view.findViewById(R.id.tv_left_msg)
}

class RightViewHolder(view: View): MsgViewHolder(view) {
    val rightMsg: TextView = view.findViewById(R.id.tv_right_msg)
}