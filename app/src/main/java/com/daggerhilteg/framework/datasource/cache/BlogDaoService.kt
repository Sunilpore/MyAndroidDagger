package com.daggerhilteg.framework.datasource.cache

import com.daggerhilteg.framework.datasource.cache.model.BlogCacheEntity


interface BlogDaoService {

    suspend fun insert(blogEntity: BlogCacheEntity): Long

    suspend fun get(): List<BlogCacheEntity>

}