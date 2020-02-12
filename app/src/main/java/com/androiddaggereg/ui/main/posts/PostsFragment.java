package com.androiddaggereg.ui.main.posts;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.androiddaggereg.R;
import com.androiddaggereg.models.Post;
import com.androiddaggereg.network.Resource;
import com.androiddaggereg.utils.LogHelper;
import com.androiddaggereg.utils.VerticalSpacingItemDecoration;
import com.androiddaggereg.viewmodels.ViewModelProviderFactory;
import com.google.gson.Gson;

import java.util.List;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;

public class PostsFragment extends DaggerFragment {

    private  PostViewModel viewModel;
    private RecyclerView postsRv;

    @Inject
    PostsRecyclerAdapter adapter;

    @Inject
    LinearLayoutManager linearLayoutManager;

    @Inject
    VerticalSpacingItemDecoration verticalSpacingItemDecoration;

    @Inject
    ViewModelProviderFactory providerFactory;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_posts,container,false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        postsRv = view.findViewById(R.id.recycler_view);

        viewModel = ViewModelProviders.of(this,providerFactory).get(PostViewModel.class);
        initRecyclerView();
        subscribeObservers();

    }


    private void subscribeObservers(){
        viewModel.observePosts().removeObservers(getViewLifecycleOwner());
        viewModel.observePosts().observe(getViewLifecycleOwner(), new Observer<Resource<List<Post>>>() {
            @Override
            public void onChanged(Resource<List<Post>> listResource) {
                if(listResource!=null){
                    switch (listResource.status){

                        case LOADING:{
                            LogHelper.showLogError("Loading posts.....");
                            break;
                        }

                        case SUCCESS:{
                            adapter.setPosts(listResource.data);
                            break;
                        }

                        case ERROR:{
                            LogHelper.showLogError(listResource.message);
                            break;
                        }
                    }
                }
            }
        });
    }

    private void initRecyclerView(){

        postsRv.setLayoutManager(new LinearLayoutManager(getActivity()));
        VerticalSpacingItemDecoration itemDecoration = new VerticalSpacingItemDecoration(15);

        //erticalSpacingItemDecoration.setVerticalSpaceHeight(15);
        //postsRv.setLayoutManager(linearLayoutManager);
        postsRv.addItemDecoration(itemDecoration);
        postsRv.setAdapter(adapter);
    }

}
