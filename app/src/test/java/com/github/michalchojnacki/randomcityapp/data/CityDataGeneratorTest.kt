package com.github.michalchojnacki.randomcityapp.data

import io.reactivex.rxjava3.plugins.RxJavaPlugins
import io.reactivex.rxjava3.schedulers.TestScheduler
import org.junit.After
import org.junit.Before
import org.junit.Test
import java.util.concurrent.TimeUnit

private const val TEST_GENERATION_PERIOD_IN_SECS = 10L

class CityDataGeneratorTest {

    private val scheduler = TestScheduler()

    private val tested = CityDataGenerator(generationPeriodInSecs = TEST_GENERATION_PERIOD_IN_SECS)

    @Before
    fun setUp() = RxJavaPlugins.setComputationSchedulerHandler { scheduler }

    @After
    fun tearDown() = RxJavaPlugins.reset()

    @Test
    fun `given advanceTime that is lower than generation period when generateCityDatas is called then CityData is not generated and tested is not completed`() {
        // given
        val advanceTime = TEST_GENERATION_PERIOD_IN_SECS - 2

        // when
        val observer = tested.generateCityDatas().test()
        scheduler.advanceTimeBy(advanceTime, TimeUnit.SECONDS)

        // then
        observer
            .assertNoErrors()
            .assertNoValues()
            .assertNotComplete()
    }

    @Test
    fun `given advanceTime that is equal to one generation period when generateCityDatas is called then CityData is generated and tested is not completed`() {
        // given
        val advanceTime = TEST_GENERATION_PERIOD_IN_SECS

        // when
        val observer = tested.generateCityDatas().test()
        scheduler.advanceTimeBy(advanceTime, TimeUnit.SECONDS)

        // then
        observer
            .assertNoErrors()
            .assertValueCount(1)
            .assertNotComplete()
    }

    @Test
    fun `given advanceTime that is equal to two generation periods when generateCityDatas is called then two CityDatas are generated and tested is not completed`() {
        val advanceTime = 2 * TEST_GENERATION_PERIOD_IN_SECS

        // when
        val observer = tested.generateCityDatas().test()
        scheduler.advanceTimeBy(advanceTime, TimeUnit.SECONDS)

        // then
        observer
            .assertNoErrors()
            .assertValueCount(2)
            .assertNotComplete()
    }
}