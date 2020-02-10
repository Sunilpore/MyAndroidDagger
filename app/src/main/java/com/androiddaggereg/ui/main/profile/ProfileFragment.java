package com.androiddaggereg.ui.main.profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.androiddaggereg.R;
import com.androiddaggereg.models.User;
import com.androiddaggereg.ui.auth.AuthResource;
import com.androiddaggereg.ui.main.MainActivity;
import com.androiddaggereg.viewmodels.ViewModelProviderFactory;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;

public class ProfileFragment extends DaggerFragment {

    private TextView email, username, website;

    @Inject
    ViewModelProviderFactory providerFactory;

    ProfileViewModel profileViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        profileViewModel = ViewModelProviders.of(this,providerFactory).get(ProfileViewModel.class);

        email = view.findViewById(R.id.email);
        username = view.findViewById(R.id.username);
        website = view.findViewById(R.id.website);

        subscribeOnObservers();
    }

    private void subscribeOnObservers(){

        profileViewModel.getAuthenticateUser().removeObservers(getViewLifecycleOwner());

        profileViewModel.getAuthenticateUser().observe(getViewLifecycleOwner(), new Observer<AuthResource<User>>() {
            @Override
            public void onChanged(AuthResource<User> userAuthResource) {

                if(userAuthResource !=null){
                    switch (userAuthResource.status){

                        case AUTHENTICATED:{
                            setUserDetails(userAuthResource.data);
                            break;
                        }

                        case ERROR:{
                            setErrorDetails(userAuthResource.message);
                            break;
                        }
                    }
                }
            }
        });
    }


    private void setUserDetails(User user){
        email.setText(user.getEmail());
        username.setText(user.getUsername());
        website.setText(user.getWebsite());
    }

    private void setErrorDetails(String message){
        email.setText(message);
        username.setText("error");
        website.setText("error");
    }


}
