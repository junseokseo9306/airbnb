package com.example.airbnb.repository

import android.util.Log
import com.example.airbnb.datasource.TmapDataSource
import com.example.airbnb.dto.toTmap
import com.example.airbnb.model.Tmap
import com.example.airbnb.network.TmapRequest
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class TmapRepositoryImpl @Inject constructor(
    private val tmapDataSource: TmapDataSource
) : TmapRepository {
    override fun getTime(tmapRequest: TmapRequest): Flow<Tmap> {
        val tmapTime = MutableSharedFlow<Tmap>(replay = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)

        tmapDataSource.getTime(tmapRequest).buffer().onEach { tmapDto ->
            tmapTime.emit(tmapDto.toTmap())
        }.launchIn(CoroutineScope(Dispatchers.Main.immediate))

        return tmapTime
    }
}