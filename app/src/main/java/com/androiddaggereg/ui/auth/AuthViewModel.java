package com.androiddaggereg.ui.auth;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import com.androiddaggereg.models.User;
import com.androiddaggereg.network.auth.AuthApi;
import com.androiddaggereg.utils.LogHelper;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class AuthViewModel extends ViewModel {

    private AuthApi authApi;
    private MediatorLiveData<User> authUser = new MediatorLiveData<>();


    @Inject
    public AuthViewModel(final AuthApi authApi) {
        this.authApi = authApi;
    }

    public void authenticateWithId(int id){
        final LiveData<User> source = LiveDataReactiveStreams.fromPublisher(
                authApi.getUser(id)
                        .subscribeOn(Schedulers.io())
        );

        authUser.addSource(source, new Observer<User>() {
            @Override
            public void onChanged(User user) {
                authUser.setValue(user);
                authUser.removeSource(source);
            }
        });
    }

    public LiveData<User> observeUser(){
        return authUser;
    }


}
