package com.daggerhilteg.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.daggerhilteg.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        //Injection happens in super.onCreate()
        //i.e. we cnnot revoke any constructor para injection before super.onCreate()

        //Hence here we add Custom NavHostFragment inside activity fragment container,
        //Because fragment factory need set before onCreate

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }
}



















