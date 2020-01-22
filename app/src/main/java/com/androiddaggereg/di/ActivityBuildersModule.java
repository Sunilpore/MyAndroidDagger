package com.androiddaggereg.di;

import com.androiddaggereg.di.auth.AuthViewModelsModule;
import com.androiddaggereg.ui.auth.AuthActivity;
import com.androiddaggereg.ui.auth.AuthViewModel;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuildersModule {

    /**
     *
     * @@ContributesAndroidInjector ->define all activities injection abstract method
     */
    @ContributesAndroidInjector(
            modules = {AuthViewModelsModule.class}
    )
    abstract AuthActivity contributeAuthActivity();


}
