package com.github.michalchojnacki.randomcityapp.ui

import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.github.michalchojnacki.randomcityapp.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(R.layout.activity_main) {

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}