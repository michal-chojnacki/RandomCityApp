package com.github.michalchojnacki.randomcityapp.ui.citydatalist

import androidx.lifecycle.LiveData
import com.github.michalchojnacki.randomcityapp.domain.model.CityData
import com.github.michalchojnacki.randomcityapp.ui.common.Event

interface CityDataListEventPublisher {

    val navigateToCityDataDetails: LiveData<Event<CityData>>
}