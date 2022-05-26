package com.example.airbnb.repository

import com.example.airbnb.datasource.TmapDataSource
import com.example.airbnb.dto.toTmap
import com.example.airbnb.model.Tmap
import com.example.airbnb.network.TmapRequest
import javax.inject.Inject

class TmapRepositoryImpl @Inject constructor(
    private val tmapDataSource: TmapDataSource
) : TmapRepository {
    override suspend fun getTime(tmapRequest: TmapRequest): Tmap {
        return tmapDataSource.getTime(tmapRequest).toTmap()
    }
}