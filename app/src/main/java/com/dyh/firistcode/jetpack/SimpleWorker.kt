package com.dyh.firistcode.jetpack

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters

class SimpleWorker(context: Context, params: WorkerParameters): Worker(context, params) {
    override fun doWork(): Result {
        Log.d("workManager", "doWork: in simpleWorker")
        return Result.success()
    }
}