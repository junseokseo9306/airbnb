package com.example.airbnb.di

import com.example.airbnb.repository.AirbnbRepository
import com.example.airbnb.repository.AirbnbRepositoryImpl
import com.example.airbnb.repository.TmapRepository
import com.example.airbnb.repository.TmapRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Singleton
    @Binds
    abstract fun bindAirbnbRepository(
        airbnbRepositoryImpl: AirbnbRepositoryImpl
    ): AirbnbRepository
}

@Module
@InstallIn(SingletonComponent::class)
abstract class TmapRepositoryModule {

    @Singleton
    @Binds
    abstract fun bindTmapRepository(
        tmapRepositoryImpl: TmapRepositoryImpl
    ): TmapRepository
}