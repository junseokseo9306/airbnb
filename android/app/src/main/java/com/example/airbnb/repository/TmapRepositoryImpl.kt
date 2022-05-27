package com.example.airbnb.repository

import com.example.airbnb.datasource.TmapDataSource
import com.example.airbnb.dto.toTmap
import com.example.airbnb.model.Tmap
import com.example.airbnb.network.TmapRequest
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class TmapRepositoryImpl @Inject constructor(
    private val tmapDataSource: TmapDataSource
) : TmapRepository {
    override suspend fun getTime(tmapRequest: TmapRequest): Flow<Tmap> {
        lateinit var tmapTime: Tmap
        tmapDataSource.getTime(tmapRequest).collect { tmapDto ->
            tmapTime = tmapDto.toTmap()
        }
        return flow {
            emit(tmapTime)
        }
    }
}