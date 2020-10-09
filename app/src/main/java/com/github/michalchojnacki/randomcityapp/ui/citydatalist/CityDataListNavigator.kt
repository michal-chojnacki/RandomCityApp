package com.github.michalchojnacki.randomcityapp.ui.citydatalist

import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.commit
import com.github.michalchojnacki.randomcityapp.R
import com.github.michalchojnacki.randomcityapp.domain.model.CityData
import com.github.michalchojnacki.randomcityapp.ui.citydatadetails.CityDataDetailsFragment
import com.github.michalchojnacki.randomcityapp.ui.common.ActivityExts.twoPane
import com.github.michalchojnacki.randomcityapp.ui.common.EventObserver
import javax.inject.Inject

class CityDataListNavigator @Inject constructor(private val activity: FragmentActivity) {

    fun observe(publisher: CityDataListEventPublisher) {
        publisher.navigateToCityDataDetails.observe(activity, EventObserver {
            navigateToCityDataDetails(it)
        })
    }

    private fun navigateToCityDataDetails(cityData: CityData) {
        if (activity.twoPane) {
            activity.supportFragmentManager.commit {
                replace(
                    R.id.city_data_details_container,
                    CityDataDetailsFragment.newInstance(cityData)
                )
            }
        } else {
            activity.supportFragmentManager.commit {
                replace(R.id.container, CityDataDetailsFragment.newInstance(cityData))
                addToBackStack(null)
            }
        }
    }
}