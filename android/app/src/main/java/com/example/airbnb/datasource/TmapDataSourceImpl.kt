package com.example.airbnb.datasource

import com.example.airbnb.BuildConfig
import com.example.airbnb.dto.TmapDto
import com.example.airbnb.network.TmapApi
import com.example.airbnb.network.TmapRequest
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TmapDataSourceImpl @Inject constructor(
    private val tmapApi: TmapApi
) : TmapDataSource {
    override fun getTime(tmapRequest: TmapRequest): Flow<TmapDto> {
        return flow {
            emit(
                tmapApi.getTime(
                    BuildConfig.TMAP_APP_KEY,
                    tmapRequest
                )
            )
        }
    }
}