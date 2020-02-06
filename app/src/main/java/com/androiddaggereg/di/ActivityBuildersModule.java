package com.androiddaggereg.di;

import com.androiddaggereg.di.auth.AuthViewModelsModule;
import com.androiddaggereg.ui.auth.AuthActivity;
import com.androiddaggereg.ui.auth.AuthViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import dagger.android.ContributesAndroidInjector;
import dagger.multibindings.ClassKey;
import dagger.multibindings.IntoMap;

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

    //---------------------------***-------------------------//
        /*Below code is internally generated code.
        So basically @ContributesAndroidInjector provides subcomponent dependency for scoping
        To check
         1.uncomment below code and comment above code
         2.Declare subcomponent abstact fun of ActivityBuildersModule in AppComponent*/


/*
    @Binds
    @IntoMap
    @ClassKey(AuthActivity.class)
    abstract AndroidInjector.Factory<?> bindAndroidInjectorFactory(
            AuthActivitySubcomponent.Factory builder);

    @Subcomponent(modules = AuthViewModelsModule.class)
    public interface AuthActivitySubcomponent extends AndroidInjector<AuthActivity> {
        @Subcomponent.Factory
        interface Factory extends AndroidInjector.Factory<AuthActivity> {}
    }*/


}
