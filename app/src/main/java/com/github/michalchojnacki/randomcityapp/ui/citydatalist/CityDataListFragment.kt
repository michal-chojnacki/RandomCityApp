package com.github.michalchojnacki.randomcityapp.ui.citydatalist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.github.michalchojnacki.randomcityapp.databinding.CityDataListFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CityDataListFragment : Fragment() {

    private val cityDataListViewModel: CityDataListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        lifecycle.addObserver(cityDataListViewModel)
        return CityDataListFragmentBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = cityDataListViewModel
        }.root
    }
}