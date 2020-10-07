package com.github.michalchojnacki.randomcityapp.domain

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.rxjava3.core.Observable
import org.junit.Test

class GetCityDataUseCaseTest {
    private val cityDataRepository: CityDataRepository = mock()

    private val tested = GetCityDataUseCase(cityDataRepository)

    @Test
    fun `when tested is executed then getCityDatas is called`() {
        // given
        whenever(cityDataRepository.getCityDatas()).thenReturn(Observable.empty())

        // when
        tested.execute().test()

        // then
        verify(cityDataRepository, times(1)).getCityDatas()
    }

}