package com.androiddaggereg.ui.main.profile;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.androiddaggereg.models.User;
import com.androiddaggereg.network.SessionManager;
import com.androiddaggereg.ui.auth.AuthResource;
import com.androiddaggereg.utils.LogHelper;

import javax.inject.Inject;

public class ProfileViewModel extends ViewModel {


    private  SessionManager sessionManager;

    @Inject
    ProfileViewModel(SessionManager sessionManager) {
        this.sessionManager = sessionManager;
        LogHelper.showLogData("ProfileViewModel init...");
    }

    public LiveData<AuthResource<User>> getAuthenticateUser(){
        return sessionManager.getAuthUser();
    }

}
