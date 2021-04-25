package com.daggerhilteg.di

import com.daggerhilteg.repository.MainRepository
import com.daggerhilteg.retrofit.BlogRetrofit
import com.daggerhilteg.retrofit.NetworkMapper
import com.daggerhilteg.room.BlogDao
import com.daggerhilteg.room.CacheMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideMainRepository(
        blogDao: BlogDao,
        retrofit: BlogRetrofit,
        cacheMapper: CacheMapper,
        networkMapper: NetworkMapper
    ): MainRepository{
        return MainRepository(blogDao, retrofit, cacheMapper, networkMapper)
    }
}