package com.github.michalchojnacki.randomcityapp.data

import com.github.michalchojnacki.randomcityapp.data.model.RawCityData
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.rxjava3.core.Observable
import org.junit.Test

class CityDataRepositoryImplTest {

    private val cityDataGenerator: CityDataGenerator = mock()
    private val colorNameToHexMapper: ColorNameToHexMapper = mock()

    private val tested = CityDataRepositoryImpl(cityDataGenerator, colorNameToHexMapper)

    @Test
    fun `when getCityDatas is called then generateCityDatas is called`() {
        // given
        whenever(cityDataGenerator.generateCityDatas()).thenReturn(Observable.empty())

        // when
        tested.getCityDatas().test()

        // then
        verify(cityDataGenerator, times(1)).generateCityDatas()
    }

    @Test
    fun `given rawCityData and mappedColorHex when getCityDatas is called value is streamed then value is mapped`() {
        // given
        val rawCityData = RawCityData("fake_city_name", "fake_color_name")
        val mappedColorHex = "mapped_color_hex"
        whenever(cityDataGenerator.generateCityDatas()).thenReturn(Observable.just(rawCityData))
        whenever(colorNameToHexMapper.map(rawCityData.colorName)).thenReturn(mappedColorHex)

        // when
        val observer = tested.getCityDatas().test()

        // then
        observer
            .assertNoErrors()
            .assertValueCount(1)
            .assertValue { it.cityName == rawCityData.cityName && it.colorHex == mappedColorHex }
    }
}