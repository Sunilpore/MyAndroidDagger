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
import javax.inject.Singleton

/**
 *KeyNote: Dagger Hilt Constructor Injection Issue
 */

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    //field injection
    /*@Inject
    lateinit var gsonProviderUtils: GsonProviderUtils*/

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


//-----------------------****-------------------------//


//Gson Provider Dependency
/*class GsonProviderUtils
@Inject
constructor(private val gson: Gson){
    fun getGsonData() = "Gson data"
}*/

//-----------------------****-------------------------//

//@ActivityScoped
//@FragmentScoped  //Note: You can' into activity level as mentioned dagger graph hierarchy
class SomClass
@Inject
constructor(
   private val someInterfaceImpl: SomeInterface
){

    fun getAThing() = "Hi, It is a random string."

    fun getAnotherThing() = someInterfaceImpl.getAnotherThing()
}


//Constructor Injection
class SomeInterfaceImpl
@Inject
constructor(
        private var someDependencyValue:String
): SomeInterface {

    override fun getAnotherThing(): String {
        return "Hi, It is another ${someDependencyValue} dependency"
    }

    //fun getAnotherThing() = "Hi, It is another random string"
}


interface SomeInterface{
    fun getAnotherThing(): String
}

//-----------------------****-------------------------//
//Solution for Dagger-Hilt Constructor Injection

//-->Method1 (with @Binds)

//@InstallIn(SingletonComponent::class) //application level annotation(previously used as ApplicationComponent which is deprecated now)
/*@InstallIn(ActivityComponent::class) //activity level annotation
@Module
abstract class MyModule {

    //@Singleton
    @ActivityScoped
    @Binds
    abstract fun bindSomeDependency(someInterfaceImpl: SomeInterfaceImpl): SomeInterface

    @ActivityScoped
    @Binds
    abstract fun bindGson(gson: Gson):Gson

}*/

//-->Method2 (with @Provides)
@InstallIn(SingletonComponent::class)
@Module
class MyModule{

    @Singleton
    @Provides
    fun provideSomeString():String{
        return "Android Jet Pack"
    }

    @Singleton
    @Provides
    fun provideSomeInterface(someString:String):SomeInterface{
        return SomeInterfaceImpl(someString)
    }


    @Singleton
    @Provides
    fun provideGson(): Gson{
        return Gson()
    }
}