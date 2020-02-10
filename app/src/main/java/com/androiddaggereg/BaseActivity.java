package com.androiddaggereg;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;

import com.androiddaggereg.models.User;
import com.androiddaggereg.network.SessionManager;
import com.androiddaggereg.ui.auth.AuthActivity;
import com.androiddaggereg.ui.auth.AuthResource;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public abstract class BaseActivity extends DaggerAppCompatActivity {

    @Inject
    public SessionManager sessionManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        subscribeObserver();

    }

    private void subscribeObserver(){

        sessionManager.getAuthUser().observe(this, new Observer<AuthResource<User>>() {
            @Override
            public void onChanged(AuthResource<User> userAuthResource) {

                if(userAuthResource != null){
                    switch (userAuthResource.status){

                        case LOADING:{
                            break;
                        }
                        case AUTHENTICATED:{
                            Toast.makeText(BaseActivity.this, "authentication success: "+userAuthResource.data.getUsername(), Toast.LENGTH_SHORT).show();
                            break;
                        }
                        case ERROR:{
                            Toast.makeText(BaseActivity.this, userAuthResource.message, Toast.LENGTH_SHORT).show();
                            break;
                        }
                        case NOT_AUTHENTICATED:{
                            navigateToLoginScreen();
                            break;
                        }
                    }
                }

            }
        });
    }


    private void navigateToLoginScreen(){
        Intent intent = new Intent(this, AuthActivity.class);
        startActivity(intent);
        finish();
    }

}
