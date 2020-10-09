package com.github.michalchojnacki.randomcityapp.ui.citydatadetails

import android.graphics.Color
import androidx.annotation.ColorInt
import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.github.michalchojnacki.randomcityapp.domain.model.CityData

class CityDataDetailsViewModel(
    private val cityData: CityData
) : ViewModel(), LifecycleObserver {

    companion object {
        const val ARG_CITY_DATA = "city_data"
    }

    @ViewModelInject
    constructor(@Assisted savedStateHandle: SavedStateHandle) : this(
        savedStateHandle.get<CityData>(
            ARG_CITY_DATA
        ) ?: throw Exception("CityDataDetailsViewModel needs cityData!")
    )

    val toolbarTitle: String
        get() = cityData.cityName

    @get:ColorInt
    val toolbarTitleColor: Int
        get() = Color.parseColor(cityData.colorHex)
}