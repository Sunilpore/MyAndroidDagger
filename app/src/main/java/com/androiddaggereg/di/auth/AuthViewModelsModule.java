package com.androiddaggereg.di.auth;

import androidx.lifecycle.ViewModel;

import com.androiddaggereg.di.ViewModelKey;
import com.androiddaggereg.ui.auth.AuthViewModel;

import dagger.Binds;
import dagger.MapKey;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class AuthViewModelsModule {

    @Binds
    @IntoMap
    @ViewModelKey(AuthViewModel.class)
    public abstract ViewModel bindAuthViewModel(AuthViewModel authViewModel);


/*
    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel.class)
    public abstract ViewModel bindAuthViewModel(MainViewModel authViewModel);
    and so on....*/

}