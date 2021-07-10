package com.daggerhilteg.di


import com.daggerhilteg.business.data.cache.CacheDataSource
import com.daggerhilteg.business.data.network.NetworkDataSource
import com.daggerhilteg.business.interactors.GetBlogs
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object InteractorsModule {

    @Singleton
    @Provides
    fun provideGetBlogs(
        cacheDataSource: CacheDataSource,
        networkDataSource: NetworkDataSource
    ): GetBlogs {
        return GetBlogs(cacheDataSource, networkDataSource)
    }
}














