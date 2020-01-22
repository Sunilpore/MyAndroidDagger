package com.androiddaggereg.ui.auth;

import androidx.lifecycle.ViewModel;

import com.androiddaggereg.utils.LogHelper;

import javax.inject.Inject;

public class AuthViewModel extends ViewModel {

    @Inject
    public AuthViewModel() {
        LogHelper.showLogData("Auth Viewmodel is working....");
    }
}
