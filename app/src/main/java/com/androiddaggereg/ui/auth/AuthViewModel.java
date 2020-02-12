package com.androiddaggereg.ui.auth;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import com.androiddaggereg.models.User;
import com.androiddaggereg.network.SessionManager;
import com.androiddaggereg.network.auth.AuthApi;
import com.androiddaggereg.utils.LogHelper;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class AuthViewModel extends ViewModel {

    private AuthApi authApi;       //@AuthScope scoped dependency
    private SessionManager sessionManager;  //@Singleton scoped dependency


    @Inject
    public AuthViewModel(final AuthApi authApi, SessionManager sessionManager) {
        this.authApi = authApi;
        this.sessionManager = sessionManager;
    }

    public void authenticateWithId(int id){

        sessionManager.authenticateWithId(queryUserId(id));

    }

    private LiveData<AuthResource<User>> queryUserId(int userId){
        return LiveDataReactiveStreams.fromPublisher(
                authApi.getUser(userId)

                        //Instead of calling onError
                        .onErrorReturn(new Function<Throwable, User>() {
                            @Override
                            public User apply(Throwable throwable) throws Exception {
                                User errorUser = new User();
                                errorUser.setId(-1);
                                return errorUser;
                            }
                        })

                        //Wrap User object in AuthResource
                        .map(new Function<User, AuthResource<User>>() {
                            @Override
                            public AuthResource<User> apply(User user) throws Exception {
                                if(user.getId() == -1){
                                    return AuthResource.error("authentication failed",null);
                                }
                                return AuthResource.authenticated(user);
                            }
                        })

                        .subscribeOn(Schedulers.io())
        );
    }

    public LiveData<AuthResource<User>> observeAuthState(){
        return sessionManager.getAuthUser();
    }


}
