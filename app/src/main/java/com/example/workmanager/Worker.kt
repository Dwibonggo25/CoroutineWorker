package com.example.workmanager

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters

class WorkerExample(contex: Context, params: WorkerParameters) : CoroutineWorker(contex, params) {


    companion object {
        val TAG = WorkerExample::class.java.simpleName
        val ARG_EXTRA_PARAM = "ARG_EXTRA_PARAM"
    }

    override suspend fun doWork(): Result  {
        return try {
            Result.success()
        }catch (error: Throwable){
            Result.failure()
        }
    }



}