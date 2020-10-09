package com.github.michalchojnacki.randomcityapp.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CityData(val cityName: String, val colorHex: String, val emissionTimeInMillis: Long) : Parcelable