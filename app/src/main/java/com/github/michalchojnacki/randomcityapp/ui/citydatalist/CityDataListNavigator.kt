package com.github.michalchojnacki.randomcityapp.ui.citydatalist

import androidx.fragment.app.FragmentActivity
import com.github.michalchojnacki.randomcityapp.domain.model.CityData
import javax.inject.Inject

class CityDataListNavigator @Inject constructor(private val activity: FragmentActivity) {

    fun navigateToCityDataDetails(cityData: CityData) {
        cityData.toString()
        // handle navigation
    }
}