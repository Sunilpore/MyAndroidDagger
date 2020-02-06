package com.androiddaggereg.ui.auth;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.androiddaggereg.R;
import com.androiddaggereg.models.User;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth_main);

        edUserId = findViewById(R.id.user_id_input);
        findViewById(R.id.login_button).setOnClickListener(this);
        authViewModel = ViewModelProviders.of(this,providerFactory).get(AuthViewModel.class);

        authViewModel.observeUser().observe(this, new Observer<User>() {
            @Override
            public void onChanged(User user) {
                Toast.makeText(AuthActivity.this, "login success: "+user.getEmail(), Toast.LENGTH_SHORT).show();
            }
        });

        setLogo();
    }

    private void setLogo(){
        requestManager
                .load(logo)
                .into((ImageView) findViewById(R.id.login_logo));
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
