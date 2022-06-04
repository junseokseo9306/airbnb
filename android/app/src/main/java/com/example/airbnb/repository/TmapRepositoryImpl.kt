package com.example.airbnb.repository

import com.example.airbnb.datasource.TmapDataSource
import com.example.airbnb.dto.toTmap
import com.example.airbnb.model.Tmap
import com.example.airbnb.network.TmapRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class TmapRepositoryImpl @Inject constructor(
    private val tmapDataSource: TmapDataSource
) : TmapRepository {
    override fun getTime(tmapRequest: TmapRequest): Flow<Tmap> =
        tmapDataSource.getTime(tmapRequest, Dispatchers.Default).map { tmapDto ->
            tmapDto.toTmap()
        }
}