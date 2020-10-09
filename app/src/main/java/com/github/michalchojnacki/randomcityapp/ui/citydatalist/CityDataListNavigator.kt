package com.github.michalchojnacki.randomcityapp.ui.citydatalist

import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.commit
import com.github.michalchojnacki.randomcityapp.R
import com.github.michalchojnacki.randomcityapp.domain.model.CityData
import com.github.michalchojnacki.randomcityapp.ui.citydatadetails.CityDataDetailsFragment
import kotlinx.android.synthetic.main.activity_main.view.*
import javax.inject.Inject

class CityDataListNavigator @Inject constructor(private val activity: FragmentActivity) {

    fun navigateToCityDataDetails(cityData: CityData) {
        activity.supportFragmentManager.commit {
            replace(R.id.container, CityDataDetailsFragment.newInstance(cityData))
            addToBackStack(null)
        }
    }
}