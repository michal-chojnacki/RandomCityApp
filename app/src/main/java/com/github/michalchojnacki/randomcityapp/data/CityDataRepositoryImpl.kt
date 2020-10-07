package com.github.michalchojnacki.randomcityapp.data

import com.github.michalchojnacki.randomcityapp.domain.model.CityData
import com.github.michalchojnacki.randomcityapp.domain.CityDataRepository
import io.reactivex.rxjava3.core.Observable

internal class CityDataRepositoryImpl(
    private val cityDataGenerator: CityDataGenerator,
    private val colorNameToHexMapper: ColorNameToHexMapper
) : CityDataRepository {

    override fun getCityDatas(): Observable<CityData> = cityDataGenerator.generateCityDatas().map {
        CityData(
            it.cityName,
            colorNameToHexMapper.map(it.colorName),
            System.currentTimeMillis()
        )
    }
}