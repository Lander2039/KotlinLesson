package com.example.kotlinlesson.utils.Coroutines

import android.util.Log
import kotlinx.coroutines.*

class Coroutines {

    fun testCoroutineJoin() {
        CoroutineScope(Dispatchers.IO).launch {

            val job = launch {
                for (i in 1..5) {
                    Log.w("called", "$i")
                    delay(400)
                }
            }
            Log.w("start", "started")
            job.join()
            Log.w("finish", "finished")
        }
    }

    fun testCoroutineCansel(){
        CoroutineScope(CoroutineName("TMS Lesson 28") + Dispatchers.IO).launch {

            val job = launch {
                for (i in 1..5) {
                    Log.w("called", "$i")
                    delay(400)
                }
            }
            Log.w("start", "started")
            job.cancel()
            Log.w("finish", "${coroutineContext[CoroutineName.Key]}")
        }
    }
}