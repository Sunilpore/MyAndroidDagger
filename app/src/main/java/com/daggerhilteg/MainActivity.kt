package com.daggerhilteg

import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    //field injection
    @Inject
    lateinit var somClass: SomClass

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        println(somClass.getAThing())
        println(somClass.getAnotherThing())

    }

}

class SomClass
@Inject
constructor(
    val someOtherClass: SomeOtherClass
){

    fun getAThing() = "Hi, It is a random string."

    fun getAnotherThing() = someOtherClass.getAnotherThing()
}

//Constructor Injection
class SomeOtherClass
@Inject
constructor(){

    fun getAnotherThing() = "Hi, It is another random string"
}