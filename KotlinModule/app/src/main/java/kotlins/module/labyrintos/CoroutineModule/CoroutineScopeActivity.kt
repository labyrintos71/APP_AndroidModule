package com.coroutine.coroutineexample.module

import androidx.appcompat.app.AppCompatActivity

abstract class CoroutineScopeActivity @JvmOverloads constructor(scope: BaseCoroutineScope = UICoroutineScope())
    : AppCompatActivity(), BaseCoroutineScope by scope {

    override fun onDestroy() {
        super.onDestroy()

        releaseCoroutine()
    }
}