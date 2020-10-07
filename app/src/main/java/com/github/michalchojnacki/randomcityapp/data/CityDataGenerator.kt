package com.github.michalchojnacki.randomcityapp.data

import com.github.michalchojnacki.randomcityapp.data.model.RawCityData
import io.reactivex.rxjava3.core.Observable
import java.util.concurrent.TimeUnit

internal class CityDataGenerator(private val generationPeriodInSecs: Long = 5L) {

    private val cities =
        listOf("Gdańsk", "Warszawa", "Poznań", "Białystok", "Wrocław", "Katowice", "Kraków")
    private val colors = listOf("Yellow", "Green", "Blue", "Red", "Black", "White")

    fun generateCityDatas(): Observable<RawCityData> =
        Observable.interval(generationPeriodInSecs, TimeUnit.SECONDS)
            .map { RawCityData(cities.random(), colors.random()) }
}