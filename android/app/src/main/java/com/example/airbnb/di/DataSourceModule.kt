package com.example.airbnb.di

import com.example.airbnb.datasource.AirbnbDataSourceImpl
import com.example.airbnb.datasource.AirbnbDataSource
import com.example.airbnb.datasource.TmapDataSource
import com.example.airbnb.datasource.TmapDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AirbnbDataSourceModule {

    @Singleton
    @Binds
    abstract fun bindAirbnbDataSource(
        airbnbDataSourceImpl: AirbnbDataSourceImpl
    ): AirbnbDataSource
}

@Module
@InstallIn(SingletonComponent::class)
abstract class TmapDataSourceModule {

    @Singleton
    @Binds
    abstract fun bindTmapDataSource(
        tmapDataSourceImpl: TmapDataSourceImpl
    ): TmapDataSource
}