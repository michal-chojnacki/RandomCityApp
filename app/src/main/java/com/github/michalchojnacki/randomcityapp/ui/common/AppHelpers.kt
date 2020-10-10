package com.github.michalchojnacki.randomcityapp.ui.common

import android.view.View
import androidx.fragment.app.FragmentActivity
import com.github.michalchojnacki.randomcityapp.R

object AppHelpers {

    @JvmStatic
    val FragmentActivity.twoPane: Boolean
        get() = findViewById<View>(R.id.city_data_details_container) != null
}