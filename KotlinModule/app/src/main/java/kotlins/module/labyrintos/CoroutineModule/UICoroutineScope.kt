package com.coroutine.coroutineexample.module

import android.util.Log
import com.coroutine.coroutineexample.BuildConfig.DEBUG
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

class UICoroutineScope(private val dispatchers: CoroutineContext = Dispatchers.Main) : BaseCoroutineScope {

    override val job: Job = Job()

    override val coroutineContext: CoroutineContext
        get() = dispatchers + job

    override fun releaseCoroutine() {
        if (DEBUG) {
            Log.d("UICoroutineScope", "onRelease coroutine")
        }
        job.cancel()
    }
}