package com.github.michalchojnacki.randomcityapp.ui.citydatalist

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.github.michalchojnacki.randomcityapp.domain.GetCityDataUseCase
import com.github.michalchojnacki.randomcityapp.domain.model.CityData
import com.nhaarman.mockitokotlin2.*
import io.reactivex.rxjava3.android.plugins.RxAndroidPlugins
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import junit.framework.TestCase.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

class CityDataListViewModelTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    private val cityDataListNavigator: CityDataListNavigator = mock()
    private val getCityDataUseCase: GetCityDataUseCase = mock()

    private val tested = CityDataListViewModel(cityDataListNavigator, getCityDataUseCase)

    @Before
    fun setUp() = RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }

    @Test
    fun `when tested is not started then getCityDataUseCase is not executed`() {
        // when
        tested

        // then
        verify(getCityDataUseCase, never()).execute()
    }

    @Test
    fun `given subscribed when tested is started then getCityDataUseCase is executed and subscribed is true`() {
        // given
        var subscribed = false
        whenever(getCityDataUseCase.execute()).thenReturn(
            Observable.never<CityData>()
                .doOnSubscribe { subscribed = true }
                .doOnDispose { subscribed = false }
        )

        // when
        tested.onStart()

        // then
        verify(getCityDataUseCase, times(1)).execute()
        assertTrue(subscribed)
    }

    @Test
    fun `given subscribed when tested is started and stopped then subscribed is false`() {
        // given
        var subscribed = false
        whenever(getCityDataUseCase.execute()).thenReturn(
            Observable.never<CityData>()
                .doOnSubscribe { subscribed = true }
                .doOnDispose { subscribed = false }
        )

        // when
        tested.onStart()
        tested.onStop()

        // then
        assertFalse(subscribed)
    }

    @Test
    fun `given subscribed when tested is restarted then subscribed is false`() {
        // given
        var subscribed = false
        whenever(getCityDataUseCase.execute()).thenReturn(
            Observable.never<CityData>()
                .doOnSubscribe { subscribed = true }
                .doOnDispose { subscribed = false }
        )

        // when
        tested.onStart()
        tested.onStop()
        tested.onStart()

        // then
        assertTrue(subscribed)
    }

    @Test
    fun `given getCityDataUseCase returns one item when tested is restarted then cityDataList in tested contains list with all elements`() {
        // given
        val cityData1 = CityData("city_name_1", "color_hex_1", 0)
        val cityData2 = CityData("city_name_2", "color_hex_2", 0)
        val cityData3 = CityData("city_name_3", "color_hex_3", 0)
        whenever(getCityDataUseCase.execute()).thenReturn(
            Observable.just(
                cityData1,
                cityData3,
                cityData2
            )
        )

        // when
        tested.onStart()

        // then
        assertEquals(listOf(cityData1, cityData2, cityData3), tested.cityDataList.value)
    }

    @Test
    fun `given getCityDataUseCase returns item when tested is started, stopped then cityDataList in tested contains sorted list2`() {
        // given
        val cityData1 = CityData("city_name_1", "color_hex_1", 0)
        val cityData2 = CityData("city_name_2", "color_hex_2", 0)
        whenever(getCityDataUseCase.execute()).thenReturn(Observable.just(cityData1))

        // when
        tested.onStart()
        tested.onStop()
        tested.onStart()

        // then
        assertEquals(listOf(cityData1, cityData1), tested.cityDataList.value)
    }

    @Test
    fun `given cityData when onCityDataSelected is invoked then navigateToCityDataDetails is executed`() {
        // given
        val cityData = CityData("city_name_1", "color_hex_1", 0)

        // when
        tested.onCityDataSelected(cityData)

        // then
        verify(cityDataListNavigator).navigateToCityDataDetails(cityData)
    }
}