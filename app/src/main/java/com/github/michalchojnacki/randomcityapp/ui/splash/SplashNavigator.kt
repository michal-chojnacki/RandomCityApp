package com.github.michalchojnacki.randomcityapp.ui.splash

import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.commit
import com.github.michalchojnacki.randomcityapp.R
import com.github.michalchojnacki.randomcityapp.ui.citydatalist.CityDataListFragment
import com.github.michalchojnacki.randomcityapp.ui.common.EventObserver
import javax.inject.Inject

class SplashNavigator @Inject constructor(private val activity: FragmentActivity) {

    fun observe(publisher: SplashEventPublisher) {
        publisher.navigateToCityDataList.observe(activity, EventObserver { cityData ->
            activity.supportFragmentManager.commit {
                replace(R.id.container, CityDataListFragment.newInstance(listOf(cityData)))
            }
        })
    }
}