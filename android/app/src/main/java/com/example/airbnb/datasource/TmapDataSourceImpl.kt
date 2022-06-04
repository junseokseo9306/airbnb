package com.example.airbnb.datasource

import com.example.airbnb.BuildConfig
import com.example.airbnb.network.TmapApi
import com.example.airbnb.network.TmapRequest
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TmapDataSourceImpl @Inject constructor(
    private val tmapApi: TmapApi
) : TmapDataSource {
    override fun getTime(
        tmapRequest: TmapRequest,
        dispatcher: CoroutineDispatcher
    ) = flow {
        val time = tmapApi.getTime(
            BuildConfig.TMAP_APP_KEY,
            tmapRequest
        )
        emit(time)
    }.flowOn(dispatcher)
}
