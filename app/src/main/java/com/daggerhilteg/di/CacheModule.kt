package com.daggerhilteg.di

import android.content.Context
import androidx.room.Room
import com.daggerhilteg.business.data.cache.CacheDataSource
import com.daggerhilteg.business.data.cache.CacheDataSourceImpl
import com.daggerhilteg.framework.datasource.cache.BlogDaoService
import com.daggerhilteg.framework.datasource.cache.BlogDaoServiceImpl
import com.daggerhilteg.framework.datasource.cache.database.BlogDao
import com.daggerhilteg.framework.datasource.cache.database.BlogDatabase
import com.daggerhilteg.framework.datasource.cache.mappers.CacheMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object CacheModule {

    @Singleton
    @Provides
    fun provideBlogDb(@ApplicationContext context: Context): BlogDatabase {
        return Room
            .databaseBuilder(
                context,
                BlogDatabase::class.java,
                BlogDatabase.DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideBlogDAO(blogDatabase: BlogDatabase): BlogDao {
        return blogDatabase.blogDao()
    }

    @Singleton
    @Provides
    fun provideBlogDaoService(
        blogDao: BlogDao
    ): BlogDaoService {
        return BlogDaoServiceImpl(blogDao)
    }

    @Singleton
    @Provides
    fun provideCacheDataSource(
        blogDaoService: BlogDaoService,
        cacheMapper: CacheMapper
    ): CacheDataSource {
        return CacheDataSourceImpl(blogDaoService, cacheMapper)
    }


}

























