package com.example.airbnb.datasource

import com.example.airbnb.dto.TmapDto
import com.example.airbnb.network.TmapRequest
import kotlinx.coroutines.flow.Flow

interface TmapDataSource {

    fun getTime(tmapRequest: TmapRequest): Flow<TmapDto>
}