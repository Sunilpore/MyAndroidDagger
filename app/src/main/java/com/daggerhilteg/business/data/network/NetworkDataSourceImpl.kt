package com.daggerhilteg.business.data.network


import com.daggerhilteg.business.domain.models.Blog
import com.daggerhilteg.framework.datasource.network.BlogRetrofitService
import com.daggerhilteg.framework.datasource.network.mappers.NetworkMapper

class NetworkDataSourceImpl
constructor(
    private val blogRetrofitService: BlogRetrofitService,
    private val networkMapper: NetworkMapper
): NetworkDataSource{

    override suspend fun get(): List<Blog> {
        return networkMapper.mapFromEntityList(blogRetrofitService.get())
    }

}