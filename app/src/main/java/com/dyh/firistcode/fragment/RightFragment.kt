package com.dyh.firistcode.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dyh.firistcode.MainActivity
import com.dyh.firistcode.R

class RightFragment : androidx.fragment.app.Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_right, container, false)
    }

    override fun onStart() {
        super.onStart()
        val activity = activity
        if (activity != null) {
            val fragmentActivity = activity as MainActivity
        }
    }
}