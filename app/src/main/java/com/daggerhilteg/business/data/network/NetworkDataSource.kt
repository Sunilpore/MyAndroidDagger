package com.daggerhilteg.business.data.network

import com.daggerhilteg.business.domain.models.Blog


interface NetworkDataSource {

    suspend fun get(): List<Blog>
}