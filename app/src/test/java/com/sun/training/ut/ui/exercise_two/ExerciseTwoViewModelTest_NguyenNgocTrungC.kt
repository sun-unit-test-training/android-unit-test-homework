package com.sun.training.ut.ui.exercise_two

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.sun.training.ut.data.Constant
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ExerciseTwoViewModelTest_NguyenNgocTrungC {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    lateinit var excerciseTwoViewModel: ExerciseTwoViewModel

    @Before
    @Throws(Exception::class)
    fun setUp(){
        MockitoAnnotations.initMocks(this)
        excerciseTwoViewModel = ExerciseTwoViewModel()
    }

    // case
    //    normal day         hour           vip
    //        T              0:00～8:44      F  => 110
    //        T              8:45～17:59     F  => 0
    //        T              18:00～23:59    F  => 110
    //        F              any             F  => 110
    //        any            any             T  => 0

    @Test
    fun calculateFee_normalDay_firstTimePeriod_notVip_return110() {
       excerciseTwoViewModel.onDateChanged(2,1)
       excerciseTwoViewModel.onTimeChanged(0,1)
       excerciseTwoViewModel.onVipChecked(false)
       excerciseTwoViewModel.calculateFee()
       Assert.assertEquals(Constant.FEE_110, excerciseTwoViewModel.feeLiveData.value)
    }

    @Test
    fun calculateFee_normalDay_secondTimePeriod_notVip_return0() {
       excerciseTwoViewModel.onDateChanged(2,1)
       excerciseTwoViewModel.onTimeChanged(9,0)
       excerciseTwoViewModel.onVipChecked(false)
       excerciseTwoViewModel.calculateFee()
       Assert.assertEquals(0, excerciseTwoViewModel.feeLiveData.value)
    }

    @Test
    fun calculateFee_normalDay_thirdTimePeriod_notVip_return110() {
       excerciseTwoViewModel.onDateChanged(2,1)
       excerciseTwoViewModel.onTimeChanged(18,0)
       excerciseTwoViewModel.onVipChecked(false)
       excerciseTwoViewModel.calculateFee()
       Assert.assertEquals(Constant.FEE_110, excerciseTwoViewModel.feeLiveData.value)
    }

    @Test
    fun calculateFee_holiday_anyTimePeriod_notVip_return110() {
       excerciseTwoViewModel.onDateChanged(2,9)
       excerciseTwoViewModel.onTimeChanged(18,0)
       excerciseTwoViewModel.onVipChecked(false)
       excerciseTwoViewModel.calculateFee()
       Assert.assertEquals(Constant.FEE_110, excerciseTwoViewModel.feeLiveData.value)
    }


    @Test
    fun calculateFee_anyDay_anyTimePeriod_Vip_return0() {
       excerciseTwoViewModel.onDateChanged(2,9)
       excerciseTwoViewModel.onTimeChanged(18,0)
       excerciseTwoViewModel.onVipChecked(true)
       excerciseTwoViewModel.calculateFee()
       Assert.assertEquals(0, excerciseTwoViewModel.feeLiveData.value)
    }

}