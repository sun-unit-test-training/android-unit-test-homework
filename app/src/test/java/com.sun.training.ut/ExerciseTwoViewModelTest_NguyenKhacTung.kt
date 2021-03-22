package com.sun.training.ut

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.sun.training.ut.data.Constant
import com.sun.training.ut.ui.exercise_two.ExerciseTwoViewModel
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ExerciseTwoViewModelTest_NguyenKhacTung {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    private lateinit var viewModel: ExerciseTwoViewModel

    @Before
    @Throws(Exception::class)
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        viewModel = ExerciseTwoViewModel()
    }

    @Test
    fun validateVip_returnFree() {
        viewModel.onVipChecked(true)
        viewModel.onDateChanged(22, 3)
        viewModel.onTimeChanged(22, 33)
        viewModel.calculateFee()
        Assert.assertEquals(0, viewModel.feeLiveData.value)
    }

    @Test
    fun validateHoliday_1_1returnFEE_110() {
        viewModel.onVipChecked(false)
        viewModel.onDateChanged(1, 1)
        viewModel.onTimeChanged(22, 33)
        viewModel.calculateFee()
        Assert.assertEquals(Constant.FEE_110, viewModel.feeLiveData.value)
    }

    @Test
    fun validateHoliday_2_9_returnFEE_110() {
        viewModel.onVipChecked(false)
        viewModel.onDateChanged(2, 9)
        viewModel.onTimeChanged(22, 33)
        viewModel.calculateFee()
        Assert.assertEquals(Constant.FEE_110, viewModel.feeLiveData.value)
    }

    @Test
    fun validateHoliday_30_4_returnFEE_110() {
        viewModel.onVipChecked(false)
        viewModel.onDateChanged(30, 4)
        viewModel.onTimeChanged(22, 33)
        viewModel.calculateFee()
        Assert.assertEquals(Constant.FEE_110, viewModel.feeLiveData.value)
    }

    @Test
    fun validateHoliday_1_5_returnFEE_110() {
        viewModel.onVipChecked(false)
        viewModel.onDateChanged(1, 5)
        viewModel.onTimeChanged(22, 33)
        viewModel.calculateFee()
        Assert.assertEquals(Constant.FEE_110, viewModel.feeLiveData.value)
    }

    @Test
    fun validateVip_holiday_1_5_returnFree() {
        viewModel.onVipChecked(true)
        viewModel.onDateChanged(1, 5)
        viewModel.onTimeChanged(22, 33)
        viewModel.calculateFee()
        Assert.assertEquals(0, viewModel.feeLiveData.value)
    }

    @Test
    fun validateSunday_returnFEE_110() {
        viewModel.onVipChecked(false)
        viewModel.onTimeChanged(10, 20)
        viewModel.onDateChanged(21, 3)
        viewModel.calculateFee()
        Assert.assertEquals(Constant.FEE_110, viewModel.feeLiveData.value)
    }

    @Test
    fun validateSaturday_returnFEE_110() {
        viewModel.onVipChecked(false)
        viewModel.onTimeChanged(10, 20)
        viewModel.onDateChanged(20, 3)
        viewModel.calculateFee()
        Assert.assertEquals(Constant.FEE_110, viewModel.feeLiveData.value)
    }

    @Test
    fun validateMonday_0hour() {
        viewModel.onVipChecked(false)
        viewModel.onTimeChanged(0, 0)
        viewModel.onDateChanged(20, 3)
        viewModel.calculateFee()
        Assert.assertEquals(Constant.FEE_110, viewModel.feeLiveData.value)
    }

    @Test
    fun validateMonday_8hour44() {
        viewModel.onVipChecked(false)
        viewModel.onTimeChanged(8, 44)
        viewModel.onDateChanged(20, 3)
        viewModel.calculateFee()
        Assert.assertEquals(Constant.FEE_110, viewModel.feeLiveData.value)
    }

    @Test
    fun validateMonday_from_0h_to_8hour44() {
        viewModel.onVipChecked(false)
        viewModel.onTimeChanged(5, 10)
        viewModel.onDateChanged(20, 3)
        viewModel.calculateFee()
        Assert.assertEquals(Constant.FEE_110, viewModel.feeLiveData.value)
    }

    @Test
    fun validateMonday_8hour45() {
        viewModel.onVipChecked(false)
        viewModel.onTimeChanged(8, 45)
        viewModel.onDateChanged(20, 3)
        viewModel.calculateFee()
        Assert.assertEquals(0, viewModel.feeLiveData.value)
    }

    @Test
    fun validateMonday_17hour49() {
        viewModel.onVipChecked(false)
        viewModel.onTimeChanged(17, 49)
        viewModel.onDateChanged(20, 3)
        viewModel.calculateFee()
        Assert.assertEquals(0, viewModel.feeLiveData.value)
    }

    @Test
    fun validateMonday_from_8hour45_to_17hour49() {
        viewModel.onVipChecked(false)
        viewModel.onTimeChanged(12, 30)
        viewModel.onDateChanged(20, 3)
        viewModel.calculateFee()
        Assert.assertEquals(0, viewModel.feeLiveData.value)
    }

    @Test
    fun validateMonday_18hour() {
        viewModel.onVipChecked(false)
        viewModel.onTimeChanged(18, 0)
        viewModel.onDateChanged(20, 3)
        viewModel.calculateFee()
        Assert.assertEquals(Constant.FEE_110, viewModel.feeLiveData.value)
    }

    @Test
    fun validateMonday_23hour59() {
        viewModel.onVipChecked(false)
        viewModel.onTimeChanged(23, 59)
        viewModel.onDateChanged(20, 3)
        viewModel.calculateFee()
        Assert.assertEquals(Constant.FEE_110, viewModel.feeLiveData.value)
    }

    @Test
    fun validateMonday_from_18hour00_to_23hour59() {
        viewModel.onVipChecked(false)
        viewModel.onTimeChanged(18, 30)
        viewModel.onDateChanged(20, 3)
        viewModel.calculateFee()
        Assert.assertEquals(Constant.FEE_110, viewModel.feeLiveData.value)
    }
}
