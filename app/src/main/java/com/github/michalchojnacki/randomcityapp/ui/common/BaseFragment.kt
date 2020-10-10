package com.github.michalchojnacki.randomcityapp.ui.common

import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import androidx.annotation.ColorInt
import androidx.fragment.app.Fragment
import com.github.michalchojnacki.randomcityapp.ui.utils.supportActionBar

abstract class BaseFragment : Fragment() {
    open val toolbarTitle: String? = null

    @ColorInt
    open val toolbarColor: Int? = null
    open val displayHomeAsUpEnabled: Boolean = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.supportActionBar?.apply {
            toolbarTitle?.let { title = it }
            toolbarColor?.let { setBackgroundDrawable(ColorDrawable(it)) }
            setDisplayHomeAsUpEnabled(displayHomeAsUpEnabled)
        }
    }

}