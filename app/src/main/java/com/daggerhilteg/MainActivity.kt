package com.daggerhilteg

import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import androidx.fragment.app.Fragment
import com.google.gson.Gson
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.internal.managers.ApplicationComponentManager
import dagger.hilt.android.scopes.ActivityScoped
import dagger.hilt.android.scopes.FragmentScoped
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject
import javax.inject.Qualifier
import javax.inject.Singleton

/**
 *KeyNote: Dagger Hilt Constructor Injection Issue
 */

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var somClass: SomClass

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val stringOne = somClass.getAThing1()
        println("hilt_op1 $stringOne")

        val stringTwo = somClass.getAThing2()
        println("hilt_op2 $stringTwo")

        println("hilt_op_obj_loc: $somClass")

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

//-----------------------****-------------------------//

//@ActivityScoped
//@FragmentScoped  //Note: You can' into activity level as mentioned dagger graph hierarchy
class SomClass
@Inject
constructor(
    @Impl1 private val someInterfaceImpl1: SomeInterface,
    @Impl2 private val someInterfaceImpl2: SomeInterface
){

    fun getAThing1() = "Look ${someInterfaceImpl1.getAnotherThing()}."

    fun getAThing2() = "Look ${someInterfaceImpl2.getAnotherThing()}."

}


//Different classes with Constructor Injection having same method of implementation
class SomeInterfaceImpl1
@Inject
constructor(): SomeInterface {

    override fun getAnotherThing(): String {
        return "It is another random dependency 1"
    }
}


class SomeInterfaceImpl2
@Inject
constructor(): SomeInterface {

    override fun getAnotherThing(): String {
        return "It is another random dependency 2"
    }
}


interface SomeInterface{
    fun getAnotherThing(): String
}

//-----------------------****-------------------------//
//Solution for Dagger-Hilt Constructor Injection


//-->Method (with @Provides)
@InstallIn(SingletonComponent::class)
@Module
class MyModule{

    @Impl1
    @Singleton
    @Provides
    fun provideSomeInterface1():SomeInterface{
        return SomeInterfaceImpl1()
    }

    @Impl2
    @Singleton
    @Provides
    fun provideSomeInterface2():SomeInterface{
        return SomeInterfaceImpl2()
    }

}

//Add Qualifiers Instead of @NameScope
@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class Impl1

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class Impl2