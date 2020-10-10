package com.github.michalchojnacki.randomcityapp.ui.splash

import androidx.lifecycle.LiveData
import com.github.michalchojnacki.randomcityapp.domain.model.CityData
import com.github.michalchojnacki.randomcityapp.ui.common.Event

interface SplashEventPublisher {

    val navigateToCityDataList: LiveData<Event<CityData>>
}