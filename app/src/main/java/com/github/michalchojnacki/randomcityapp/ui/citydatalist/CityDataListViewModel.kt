package com.github.michalchojnacki.randomcityapp.ui.citydatalist

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.github.michalchojnacki.randomcityapp.domain.GetCityDataUseCase
import com.github.michalchojnacki.randomcityapp.domain.model.CityData
import com.github.michalchojnacki.randomcityapp.ui.common.Event
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers

const val ARG_INITIAL_DATA = "initial_data"

class CityDataListViewModel(
    initialData: List<CityData>,
    private val getCityDataListUseCase: GetCityDataUseCase
) : ViewModel(), LifecycleObserver, CityDataListEventPublisher {

    @ViewModelInject
    constructor(
        @Assisted savedStateHandle: SavedStateHandle,
        getCityDataListUseCase: GetCityDataUseCase
    ) : this(
        savedStateHandle.get<List<CityData>>(ARG_INITIAL_DATA) ?: emptyList(),
        getCityDataListUseCase
    )


    val cityDataList: LiveData<List<CityData>> get() = _cityDataList
    override val navigateToCityDataDetails: LiveData<Event<CityData>> get() = _navigateToCityDataDetails
    val onCityDataSelected =
        { cityData: CityData -> _navigateToCityDataDetails.value = Event(cityData) }

    private val _cityDataList = MutableLiveData(initialData)
    private val _navigateToCityDataDetails = MutableLiveData<Event<CityData>>()
    private var getCityDataListDisposable: Disposable? = null

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onStart() {
        getCityDataListDisposable = getCityDataListUseCase.execute()
            .scan(cityDataList.value ?: emptyList(), { t1, t2 -> t1 + t2 })
            .map { it.sortedBy { cityData -> cityData.cityName } }
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { _cityDataList.value = it }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun onStop() {
        getCityDataListDisposable?.dispose()
    }
}