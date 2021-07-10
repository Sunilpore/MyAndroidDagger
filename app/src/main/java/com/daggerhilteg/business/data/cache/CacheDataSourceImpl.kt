package com.daggerhilteg.business.data.cache


import com.daggerhilteg.business.domain.models.Blog
import com.daggerhilteg.framework.datasource.cache.BlogDaoService
import com.daggerhilteg.framework.datasource.cache.mappers.CacheMapper

class CacheDataSourceImpl
constructor(
    private val blogDaoService: BlogDaoService,
    private val cacheMapper: CacheMapper
): CacheDataSource{

    override suspend fun insert(blog: Blog): Long {
        return blogDaoService.insert(cacheMapper.mapToEntity(blog))
    }

    override suspend fun insertList(blogs: List<Blog>){
        for(blog in blogs) {
            blogDaoService.insert(cacheMapper.mapToEntity(blog))
        }
    }

    override suspend fun get(): List<Blog> {
        return cacheMapper.mapFromEntityList(blogDaoService.get())
    }

}
