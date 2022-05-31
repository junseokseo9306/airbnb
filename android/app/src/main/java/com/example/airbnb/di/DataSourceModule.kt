package com.example.airbnb.di

import com.example.airbnb.datasource.HomeDataImpl
import com.example.airbnb.datasource.HomeDataSource
import com.example.airbnb.datasource.TmapDataSource
import com.example.airbnb.datasource.TmapDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class HomeDataModule {

    @Singleton
    @Binds
    abstract fun bindHomeData(
        homeDataImpl: HomeDataImpl
    ): HomeDataSource
}

@Module
@InstallIn(SingletonComponent::class)
abstract class TmapDataModule {

    @Singleton
    @Binds
    abstract fun bindTmapData(
        tmapDataSourceImpl: TmapDataSourceImpl
    ): TmapDataSource
}