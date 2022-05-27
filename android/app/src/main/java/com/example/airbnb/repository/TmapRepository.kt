package com.example.airbnb.repository

import com.example.airbnb.model.Tmap
import com.example.airbnb.network.TmapRequest

interface TmapRepository {

    suspend fun getTime(tmapRequest: TmapRequest): Tmap
}