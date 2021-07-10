package com.daggerhilteg.business.data.cache

import com.daggerhilteg.business.domain.models.Blog


interface CacheDataSource {

    suspend fun insert(blog: Blog): Long

    suspend fun insertList(blogs: List<Blog>)

    suspend fun get(): List<Blog>
}