package com.coroutine.coroutineexample.module

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job

interface BaseCoroutineScope : CoroutineScope {
    val job: Job
    /**
     * Coroutine job cancel
     */
    fun releaseCoroutine()
}