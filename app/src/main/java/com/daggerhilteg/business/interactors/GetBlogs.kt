package com.daggerhilteg.business.interactors


import com.daggerhilteg.business.data.cache.CacheDataSource
import com.daggerhilteg.business.data.network.NetworkDataSource
import com.daggerhilteg.business.domain.models.Blog
import com.daggerhilteg.business.domain.state.DataState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetBlogs
constructor(
    private val cacheDataSource: CacheDataSource,
    private val networkDataSource: NetworkDataSource
){

    private val TAG: String = "AppDebug"

    /**
     * Show loading
     * Get blogs from network
     * Insert blogs into cache
     * Show List<Blog>
     */
    suspend fun execute(): Flow<DataState<List<Blog>>> = flow {
        emit(DataState.Loading)
        delay(1000)
        val networkBlogs = networkDataSource.get()
        cacheDataSource.insertList(networkBlogs)
        val cachedBlogs = cacheDataSource.get()
        emit(DataState.Success(cachedBlogs))
    }

}
















