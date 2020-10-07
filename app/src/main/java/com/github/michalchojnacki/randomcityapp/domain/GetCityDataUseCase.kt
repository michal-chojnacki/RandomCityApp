package com.github.michalchojnacki.randomcityapp.domain

import com.github.michalchojnacki.randomcityapp.domain.model.CityData
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class GetCityDataUseCase @Inject constructor(private val cityDataRepository: CityDataRepository) {

    fun execute(): Observable<CityData> = cityDataRepository.getCityDatas()
}