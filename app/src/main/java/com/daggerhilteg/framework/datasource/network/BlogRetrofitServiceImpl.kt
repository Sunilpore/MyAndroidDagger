package com.daggerhilteg.framework.datasource.network

import com.daggerhilteg.framework.datasource.network.model.BlogNetworkEntity
import com.daggerhilteg.framework.datasource.network.retrofit.BlogRetrofit


class BlogRetrofitServiceImpl
constructor(
    private val blogRetrofit: BlogRetrofit
): BlogRetrofitService {

    override suspend fun get(): List<BlogNetworkEntity> {
        return blogRetrofit.get()
    }
}