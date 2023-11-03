package com.labyrintos.skeleton.CoroutineModule

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job

/**
 * Created by Labyrintos on 2019-10-31
 */
interface BaseCoroutineScope : CoroutineScope {
    val job: Job
    /**
     * Coroutine job cancel
     */
    fun releaseCoroutine()
}