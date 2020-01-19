package com.androiddaggereg.di;

import com.androiddaggereg.AuthActivity;

import dagger.Module;
import dagger.Provides;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuildersModule {

    /**
     *
     * @@ContributesAndroidInjector ->define all activities injection abstract method
     */
    @ContributesAndroidInjector
    abstract AuthActivity contributeAuthActivity();


}
