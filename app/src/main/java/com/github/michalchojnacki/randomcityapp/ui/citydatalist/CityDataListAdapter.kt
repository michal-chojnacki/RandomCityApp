package com.github.michalchojnacki.randomcityapp.ui.citydatalist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.github.michalchojnacki.randomcityapp.R
import com.github.michalchojnacki.randomcityapp.databinding.CityDataListItemBinding
import com.github.michalchojnacki.randomcityapp.domain.model.CityData
import com.github.michalchojnacki.randomcityapp.ui.utils.getLazyAdapter

class CityDataListAdapter(private val onCityDataSelected: ((CityData) -> Unit)? = null) :
    ListAdapter<CityData, CityDataListAdapter.ViewHolder>(
        object : DiffUtil.ItemCallback<CityData>() {
            override fun areItemsTheSame(oldItem: CityData, newItem: CityData) = oldItem == newItem

            override fun areContentsTheSame(oldItem: CityData, newItem: CityData) =
                oldItem == newItem
        }) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        LayoutInflater.from(parent.context)
            .inflate(R.layout.city_data_list_item, parent, false)
            .let { ViewHolder(CityDataListItemBinding.bind(it)) }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.viewModel =
            CityDataListItemViewModel(currentList[position], onCityDataSelected)
    }

    class ViewHolder(val binding: CityDataListItemBinding) : RecyclerView.ViewHolder(binding.root)
}

@BindingAdapter("cityDataList", "onCityDataSelected", requireAll = false)
fun RecyclerView.bindCityDataListAdapter(
    cityDataList: List<CityData>,
    onCityDataSelected: ((CityData) -> Unit)?
) = getLazyAdapter { CityDataListAdapter(onCityDataSelected) }.submitList(cityDataList)


