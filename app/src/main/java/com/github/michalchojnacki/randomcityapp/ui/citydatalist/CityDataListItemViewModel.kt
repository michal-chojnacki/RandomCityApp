package com.github.michalchojnacki.randomcityapp.ui.citydatalist

import android.graphics.Color
import com.github.michalchojnacki.randomcityapp.domain.model.CityData
import java.text.SimpleDateFormat
import java.util.*

class CityDataListItemViewModel(
    private val cityData: CityData,
    private val onItemSelected: ((CityData) -> Unit)? = null
) {

    val cityName = cityData.cityName

    val cityNameColor = Color.parseColor(cityData.colorHex)

    val creationTime: String
        get() = SimpleDateFormat(
            "dd/MM/yyyy HH:mm:ss",
            Locale.forLanguageTag("pl-pl")
        ).format(Date(cityData.emissionTimeInMillis))

    fun onClick() {
        onItemSelected?.invoke(cityData)
    }
}