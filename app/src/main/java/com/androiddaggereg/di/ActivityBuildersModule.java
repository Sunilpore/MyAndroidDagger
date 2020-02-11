package com.androiddaggereg.di;

import com.androiddaggereg.di.auth.AuthModule;
import com.androiddaggereg.di.auth.AuthViewModelsModule;
import com.androiddaggereg.di.main.MainFragmentBuildersModule;
import com.androiddaggereg.di.main.MainModule;
import com.androiddaggereg.di.main.MainViewModelsModule;
import com.androiddaggereg.ui.auth.AuthActivity;
import com.androiddaggereg.ui.main.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuildersModule {

    /**
     *
     * @@ContributesAndroidInjector ->define all activities injection abstract method
     */
    @ContributesAndroidInjector(
            modules = {AuthViewModelsModule.class, AuthModule.class}
    )
    abstract AuthActivity contributeAuthActivity();

    @ContributesAndroidInjector(
            modules = {MainFragmentBuildersModule.class, MainViewModelsModule.class, MainModule.class}
    )
    abstract MainActivity contributeMainActivity();



}
