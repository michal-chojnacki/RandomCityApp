package com.github.michalchojnacki.randomcityapp.ui.utils

import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity

val FragmentActivity.supportActionBar: ActionBar? get() = (this as? AppCompatActivity)?.supportActionBar
