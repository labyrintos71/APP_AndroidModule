package com.labyrintos.skeleton.CoroutineModule

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

/**
 * Created by Labyrintos on 2019-10-31
 */
class UICoroutineScope(private val dispatchers: CoroutineContext = Dispatchers.Main) :
    BaseCoroutineScope {

    override val job: Job = Job()

    override val coroutineContext: CoroutineContext
        get() = dispatchers + job

    override fun releaseCoroutine() {
        Log.d("UICoroutineScope", "onRelease coroutine")
        job.cancel()
    }
}