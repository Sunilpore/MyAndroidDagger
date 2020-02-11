package com.androiddaggereg.di.app;

import android.app.Application;
import android.content.Context;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.androiddaggereg.utils.VerticalSpacingItemDecoration;

import dagger.Module;
import dagger.Provides;

@Module
public class ViewModules {

    @Provides
    static LinearLayoutManager provideLinearLayoutManager(Context context){
        return new LinearLayoutManager(context);
    }

    @Provides
    static VerticalSpacingItemDecoration provideItemDecorator(){
        return new VerticalSpacingItemDecoration();
    }

}
