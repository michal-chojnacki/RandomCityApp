package com.github.michalchojnacki.randomcityapp.ui.citydatadetails

import android.graphics.Color
import androidx.annotation.ColorInt
import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.github.michalchojnacki.randomcityapp.domain.model.CityData

class CityDataDetailsViewModel(
    private val cityData: CityData
) : ViewModel(), LifecycleObserver {

    val errorLoadingMap: LiveData<Boolean> get() = _errorLoadingMap

    private val _errorLoadingMap = MutableLiveData(false)

    companion object {
        const val ARG_CITY_DATA = "city_data"
    }

    @ViewModelInject
    constructor(@Assisted savedStateHandle: SavedStateHandle) : this(
        savedStateHandle.get<CityData>(
            ARG_CITY_DATA
        ) ?: throw Exception("CityDataDetailsViewModel needs cityData!")
    )

    val cityName: String
        get() = cityData.cityName

    val cityNameWithCountry: String
        get() = "${cityData.cityName}, Polska"

    @get:ColorInt
    val toolbarTitleColor: Int
        get() = Color.parseColor(cityData.colorHex)

    fun onErrorLoadingMap() {
        _errorLoadingMap.value = true
    }
}