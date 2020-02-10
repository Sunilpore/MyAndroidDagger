package com.androiddaggereg.network;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;

import com.androiddaggereg.models.User;
import com.androiddaggereg.ui.auth.AuthResource;
import com.androiddaggereg.utils.LogHelper;

import javax.inject.Inject;

public class SessionManager {

    //Here we use MediatorLiveData instead of RawData to observe the authenticate user from any class where we inject the session manager in it

    private MediatorLiveData<AuthResource<User>> cacheUser = new MediatorLiveData<>();

    @Inject
    public SessionManager() {}

    public void authenticateWithId(final LiveData<AuthResource<User>> source){
        LogHelper.showLogData("authenticate user....");
        if(cacheUser !=null){
            cacheUser.setValue(AuthResource.<User>loading(null));
            cacheUser.addSource(source, new Observer<AuthResource<User>>() {
                @Override
                public void onChanged(AuthResource<User> userAuthResource) {
                    cacheUser.setValue(userAuthResource);
                    cacheUser.removeSource(source);
                }
            });
        }
    }

    public void logOut(){
        LogHelper.showLogData("User logout....");
        cacheUser.setValue(AuthResource.<User>logout());
    }

    public LiveData<AuthResource<User>> getAuthUser(){
        return cacheUser;
    }

}
