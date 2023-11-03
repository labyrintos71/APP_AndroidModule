package com.labyrintos.skeleton.Network.RetrofitForCouroutine

import android.os.Bundle
import com.labyrintos.skeleton.CoroutineModule.CoroutineScopeActivity
import com.labyrintos.skeleton.R
import com.labyrintos.skeleton.Network.Retrofit.CoroutineService
import com.labyrintos.skeleton.Network.Retrofit.RetrofitCreator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

/**
 * Created by Labyrintos on 2019-11-01
 */
class ExamActivity :CoroutineScopeActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getData()
    }
    fun getData() = launch {
        val data = async(Dispatchers.IO) {
            RetrofitCreator.create(CoroutineService::class.java).getUser(1)
        }
        //text.text = data.await().data.id.toString()
    }
}
