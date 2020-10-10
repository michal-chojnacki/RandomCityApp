package com.github.michalchojnacki.randomcityapp.ui.splash

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.michalchojnacki.randomcityapp.domain.GetCityDataUseCase
import com.github.michalchojnacki.randomcityapp.domain.model.CityData
import com.github.michalchojnacki.randomcityapp.ui.common.Event
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.plusAssign
import io.reactivex.rxjava3.schedulers.Schedulers

class SplashViewModel @ViewModelInject constructor(private val getCityDataUseCase: GetCityDataUseCase) :
    ViewModel(), SplashEventPublisher {

    override val navigateToCityDataList: LiveData<Event<CityData>>
        get() = _navigateToCityDataList

    private val _navigateToCityDataList = MutableLiveData<Event<CityData>>()
    private val disposables = CompositeDisposable()

    init {
        disposables += getCityDataUseCase.execute().subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { _navigateToCityDataList.value = Event(it) }
    }

    override fun onCleared() {
        disposables.dispose()
        super.onCleared()
    }
}