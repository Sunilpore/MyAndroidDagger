package com.daggerhilteg

import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import androidx.fragment.app.Fragment
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.scopes.ActivityScoped
import dagger.hilt.android.scopes.FragmentScoped
import javax.inject.Inject
import javax.inject.Singleton

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

        println("obj_loc: $somClass")

    }

}

@AndroidEntryPoint
class MyFragment(): Fragment() {

    @Inject
    lateinit var somClass: SomClass


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        print("obj_loc2: $somClass")
    }
}



@Singleton
//@ActivityScoped
//@FragmentScoped  //Note: You can' into activity level as mentioned dagger graph hierarchy
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