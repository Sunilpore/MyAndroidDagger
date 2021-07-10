package com.daggerhilteg

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.core.app.launchActivity
import com.daggerhilteg.di.ProductionModule
import com.daggerhilteg.framework.presentation.MainActivity
import com.daggerhilteg.framework.presentation.MainFragment
import com.daggerhilteg.framework.presentation.MainFragmentFactory
import com.daggerhilteg.util.launchFragmentInHiltContainer
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.hamcrest.CoreMatchers.containsString
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject
import javax.inject.Singleton

/**
 * @param @UninstallModules -> Uninstall actual data module, So that we can test with Our TestModule Here Test Module is "TestAppModule"
 */
@ExperimentalCoroutinesApi
@UninstallModules(ProductionModule::class)
@HiltAndroidTest
class MainTest {

    @get:Rule()
    var hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var someString: String

    @Inject
    lateinit var fragmentFactory: MainFragmentFactory

    @Before
    fun init(){
        hiltRule.inject()
    }

    @Test
    fun someTest(){
        assertThat(someString,containsString("It is TEST String!"))
    }

    @Test
    fun mainActivityTest(){
        val scenario = launchActivity<MainActivity>()
    }

    @Test
    fun mainFragmentTest(){
        val scenario = launchFragmentInHiltContainer<MainFragment>(
            factory = fragmentFactory)
    }

    @Module
    @InstallIn(SingletonComponent::class) //Here we used SingletonComponent instead of applicationComponent as it is deprecated
    object TestAppModule {

        @Singleton
        @Provides
        fun provideSomeString(): String {
            return "It is TEST String!"
        }

    }

}