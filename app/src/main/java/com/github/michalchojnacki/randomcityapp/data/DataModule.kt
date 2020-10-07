package com.github.michalchojnacki.randomcityapp.data

import com.github.michalchojnacki.randomcityapp.domain.CityDataRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@Module
@InstallIn(ApplicationComponent::class)
internal abstract class DataModule {

    @Binds
    abstract fun bindCityDataRepository(
        cityDataRepositoryImpl: CityDataRepositoryImpl
    ): CityDataRepository
}