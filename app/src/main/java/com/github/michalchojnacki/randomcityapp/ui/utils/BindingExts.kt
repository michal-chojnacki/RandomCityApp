package com.github.michalchojnacki.randomcityapp.ui.utils

import android.view.View
import androidx.databinding.BindingConversion

@BindingConversion
fun convertBooleanToVisibility(visible: Boolean?) = if (visible == true) View.VISIBLE else View.GONE