package com.daggerhilteg.framework.datasource.network

import com.daggerhilteg.framework.datasource.network.model.BlogNetworkEntity


interface BlogRetrofitService {

    suspend fun get(): List<BlogNetworkEntity>
}