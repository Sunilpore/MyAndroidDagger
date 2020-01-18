package com.androiddaggereg;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.androiddaggereg.utils.LogHelper;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class AuthActivity extends DaggerAppCompatActivity {

    @Inject
    String testValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth_main);

        LogHelper.showLogData(testValue);
    }


}
