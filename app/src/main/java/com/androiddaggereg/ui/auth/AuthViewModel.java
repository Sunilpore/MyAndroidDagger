package com.androiddaggereg.ui.auth;

import androidx.lifecycle.ViewModel;

import com.androiddaggereg.models.User;
import com.androiddaggereg.network.auth.AuthApi;
import com.androiddaggereg.utils.LogHelper;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class AuthViewModel extends ViewModel {

    private AuthApi authApi;

    @Inject
    public AuthViewModel(AuthApi authApi) {

        authApi.getUser(1)
                .toObservable()
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<User>(){
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(User user) {
                        LogHelper.showLogData("user_email: "+user.getEmail());
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }
}
