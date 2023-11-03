package com.labyrintos.skeleton.CoroutineModule

import androidx.appcompat.app.AppCompatActivity

/**
 * Created by Labyrintos on 2019-10-31
 */
abstract class CoroutineScopeActivity @JvmOverloads constructor(scope: BaseCoroutineScope = UICoroutineScope())
    : AppCompatActivity(), BaseCoroutineScope by scope {

    override fun onDestroy() {
        super.onDestroy()
        releaseCoroutine()
    }
}