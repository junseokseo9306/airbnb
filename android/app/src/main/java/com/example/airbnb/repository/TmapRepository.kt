package com.example.airbnb.repository

import com.example.airbnb.model.Tmap
import com.example.airbnb.network.TmapRequest
import kotlinx.coroutines.flow.Flow

interface TmapRepository {

    fun getTime(tmapRequest: TmapRequest): Flow<Tmap>
}