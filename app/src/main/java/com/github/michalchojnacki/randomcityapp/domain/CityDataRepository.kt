package com.github.michalchojnacki.randomcityapp.domain

import com.github.michalchojnacki.randomcityapp.domain.model.CityData
import io.reactivex.rxjava3.core.Observable

interface CityDataRepository {

    fun getCityDatas(): Observable<CityData>
}