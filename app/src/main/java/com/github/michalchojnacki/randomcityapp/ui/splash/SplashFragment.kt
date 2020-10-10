package com.github.michalchojnacki.randomcityapp.ui.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.github.michalchojnacki.randomcityapp.R
import com.github.michalchojnacki.randomcityapp.ui.utils.supportActionBar
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SplashFragment : Fragment(R.layout.splash_fragment) {

    @Inject
    lateinit var splashNavigator: SplashNavigator

    private val splashViewModel: SplashViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        activity?.supportActionBar?.hide()
        splashNavigator.observe(splashViewModel)

        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onDestroyView() {
        activity?.supportActionBar?.show()
        super.onDestroyView()
    }
}