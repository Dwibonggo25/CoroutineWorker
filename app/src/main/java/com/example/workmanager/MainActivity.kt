package com.example.workmanager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.work.*
import kotlinx.android.synthetic.main.activity_main.*
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        configView()
    }

    private fun configView() {
        // Worker Unique Power&Internet
//        activity_main_button_worker_unique.setOnClickListener {
//            initWorkerContraint ()
//        }

        // Periodic worker
        btnOnTime.setOnClickListener {
            initWorkerContraint ()
        }


        // Periodic worker
        btnPeriodic.setOnClickListener {
            initPeriodicWorker()
        }
    }

    private fun initWorkerContraint () {
        val constraints : Constraints = Constraints.Builder()
            .setRequiresCharging(true)
            .build()

        val workerTest = OneTimeWorkRequestBuilder<WorkerExample>()
            .setConstraints(constraints)
            .build()

        WorkManager.getInstance().enqueue(workerTest)
    }

    private fun initPeriodicWorker () {

        val mWorkManager = WorkManager.getInstance()
        mWorkManager?.cancelAllWorkByTag(WorkerExample.TAG)

        val periodicBuilder = PeriodicWorkRequest.Builder(WorkerExample::class.java, 15, TimeUnit.MINUTES)
        val myWork = periodicBuilder.addTag(WorkerExample.TAG).build()
        mWorkManager?.enqueue(myWork)
    }

}
