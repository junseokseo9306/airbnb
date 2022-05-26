package com.example.airbnb.datasource

import com.example.airbnb.dto.TmapDto
import com.example.airbnb.network.TmapRequest

interface TmapDataSource {

    suspend fun getTime(tmapRequest: TmapRequest): TmapDto
}