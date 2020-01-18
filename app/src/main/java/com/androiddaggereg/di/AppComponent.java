package com.androiddaggereg.di;

import android.app.Application;

import com.androiddaggereg.BaseApplication;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

@Component(
        modules = {AndroidSupportInjectionModule.class,
                    ActivityBuildersModule.class}
)
public interface AppComponent extends AndroidInjector<BaseApplication> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        Builder application(Application application);

        AppComponent build();
    }


}

/**
 * Note:-
 * AppComponent -> Treat as service class
 *  Activity/Fragment/BaseActivity -> Treat as client class
 *
 * So here is a many to one relation here i.e. clinets-> service
 * Here we can also reproduce sub-service using @SubComponent.
 *
 * Advatage->
 * Here we don't required to specify @inject component for dagger component class in Activity/fragment
 */
