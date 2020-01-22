package com.androiddaggereg.di;

import androidx.lifecycle.ViewModelProvider;

import com.androiddaggereg.viewmodels.ViewModelProviderFactory;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

@Module
public abstract class ViewModelFactoryModule {

    @Binds
    public abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelProviderFactory viewModelProviderFactory);

    /*
    //alternative to binds
    @Provides
    static ViewModelProvider.Factory bindFactory(ViewModelProviderFactory viewModelProviderFactory){
        return viewModelProviderFactory;
    }*/

}
