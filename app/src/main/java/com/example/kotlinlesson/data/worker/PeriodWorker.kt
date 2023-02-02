package com.example.kotlinlesson.data.worker

import android.content.Context
import android.util.Log
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.kotlinlesson.data.items.ItemsRepositoryImpl
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@HiltWorker
class PeriodWorker @AssistedInject constructor(
    @Assisted context: Context,
    @Assisted parameters: WorkerParameters,
    val itemsRepositoryImp: ItemsRepositoryImpl
) : CoroutineWorker(context, parameters) {

    override suspend fun doWork(): Result {
        try {
            CoroutineScope(Dispatchers.IO).launch {
                Log.w("doWork called", "getData")
                itemsRepositoryImp.getData()
            }
        } catch (e: Exception) {
            Log.w("doWork failed", e.toString())
            return Result.retry()
        }
        return Result.success()
    }

    companion object {
        const val WORKER_NAME = "Worker Name"
    }
}