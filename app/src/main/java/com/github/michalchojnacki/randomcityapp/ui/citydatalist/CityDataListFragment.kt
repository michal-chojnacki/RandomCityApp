package com.github.michalchojnacki.randomcityapp.ui.citydatalist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.getColor
import androidx.fragment.app.viewModels
import com.github.michalchojnacki.randomcityapp.R
import com.github.michalchojnacki.randomcityapp.databinding.CityDataListFragmentBinding
import com.github.michalchojnacki.randomcityapp.domain.model.CityData
import com.github.michalchojnacki.randomcityapp.ui.common.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class CityDataListFragment : BaseFragment() {

    companion object {
        fun newInstance(initialData: List<CityData>) = CityDataListFragment().apply {
            arguments = Bundle().apply {
                putParcelableArrayList(ARG_INITIAL_DATA, ArrayList(initialData))
            }
        }
    }

    @Inject
    lateinit var cityDataListNavigator: CityDataListNavigator

    override val toolbarTitle: String?
        get() = getString(R.string.app_name)

    override val toolbarColor: Int?
        get() = getColor(requireContext(), R.color.colorPrimary)

    private val cityDataListViewModel: CityDataListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        lifecycle.addObserver(cityDataListViewModel)
        cityDataListNavigator.observe(cityDataListViewModel)
        return CityDataListFragmentBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = cityDataListViewModel
        }.root
    }
}