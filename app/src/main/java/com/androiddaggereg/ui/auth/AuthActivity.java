package com.androiddaggereg.ui.auth;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.androiddaggereg.R;
import com.androiddaggereg.models.User;
import com.androiddaggereg.ui.main.MainActivity;
import com.androiddaggereg.utils.LogHelper;
import com.androiddaggereg.viewmodels.ViewModelProviderFactory;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.RequestOptions;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class AuthActivity extends DaggerAppCompatActivity implements View.OnClickListener {

    private EditText edUserId;

    @Inject
    Drawable logo;

    @Inject
    RequestManager requestManager;

    @Inject
    ViewModelProviderFactory providerFactory;

    AuthViewModel authViewModel;

    private ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth_main);

        edUserId = findViewById(R.id.user_id_input);
        mProgressBar = findViewById(R.id.progress_bar);

        findViewById(R.id.login_button).setOnClickListener(this);
        authViewModel = ViewModelProviders.of(this,providerFactory).get(AuthViewModel.class);

        authViewModel.observeAuthState().observe(this, new Observer<AuthResource<User>>() {
            @Override
            public void onChanged(AuthResource<User> userAuthResource) {

                if(userAuthResource != null){
                    switch (userAuthResource.status){

                        case LOADING:{
                            isShowProgressBar(true);
                            break;
                        }
                        case AUTHENTICATED:{
                            isShowProgressBar(false);
                            onLoginSuccess();
                            break;
                        }
                        case ERROR:{
                            isShowProgressBar(false);
                            Toast.makeText(AuthActivity.this, userAuthResource.message, Toast.LENGTH_SHORT).show();
                            break;
                        }
                        case NOT_AUTHENTICATED:{
                            isShowProgressBar(false);
                            break;
                        }
                    }
                }
            }
        });

        setLogo();
    }

    private void setLogo(){
        requestManager
                .load(logo)
                .into((ImageView) findViewById(R.id.login_logo));
    }

    private void onLoginSuccess(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void isShowProgressBar(boolean isVisible){
        if(isVisible){
            mProgressBar.setVisibility(View.VISIBLE);
        } else {
            mProgressBar.setVisibility(View.GONE);
        }
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.login_button:{
                if(!TextUtils.isEmpty(edUserId.getText().toString())){
                    authViewModel.authenticateWithId(Integer.parseInt(edUserId.getText().toString()));
                } else {
                    Toast.makeText(this,"",Toast.LENGTH_SHORT).show();
                }

            }
        }
    }


}
