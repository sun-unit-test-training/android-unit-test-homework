package com.sun.training.ut

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.sun.training.ut.data.Constant
import com.sun.training.ut.ui.exercise_two.ExerciseTwoViewModel
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ExerciseTowViewModelTest {

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
    fun calculateFee_vip() {
        viewModel.onVipChecked(true)
        viewModel.onDateChanged(21,3)
        viewModel.onTimeChanged(16, 30)
        viewModel.calculateFee()
        assertEquals(0, viewModel.feeLiveData.value)
    }

    @Test
    fun calculateFee_holidays_2_9() {
        viewModel.onVipChecked(false)
        viewModel.onDateChanged(2,8)
        viewModel.onTimeChanged(16, 50)
        viewModel.calculateFee()
        assertEquals(Constant.FEE_110, viewModel.feeLiveData.value)
    }

    @Test
    fun calculateFee_holidays_1_1() {
        viewModel.onVipChecked(false)
        viewModel.onDateChanged(1,0)
        viewModel.onTimeChanged(16, 50)
        viewModel.calculateFee()
        assertEquals(Constant.FEE_110, viewModel.feeLiveData.value)
    }

    @Test
    fun calculateFee_holidays_30_4() {
        viewModel.onVipChecked(false)
        viewModel.onDateChanged(30,3)
        viewModel.onTimeChanged(16, 50)
        viewModel.calculateFee()
        assertEquals(Constant.FEE_110, viewModel.feeLiveData.value)
    }

    @Test
    fun calculateFee_holidays_1_5() {
        viewModel.onVipChecked(false)
        viewModel.onDateChanged(1,4)
        viewModel.onTimeChanged(16, 50)
        viewModel.calculateFee()
        assertEquals(Constant.FEE_110, viewModel.feeLiveData.value)
    }

    @Test
    fun calculateFee_HourRegularDay_min() {
        viewModel.onVipChecked(false)
        viewModel.onDateChanged(1,6)
        viewModel.onTimeChanged(8, 46)
        viewModel.calculateFee()
        assertEquals(0, viewModel.feeLiveData.value)
    }

    @Test
    fun calculateFee_HourRegularDay_min_out() {
        viewModel.onVipChecked(false)
        viewModel.onDateChanged(1,6)
        viewModel.onTimeChanged(8, 45)
        viewModel.calculateFee()
        assertEquals(Constant.FEE_110, viewModel.feeLiveData.value)
    }


    @Test
    fun calculateFee_HourRegularDay_max() {
        viewModel.onVipChecked(false)
        viewModel.onDateChanged(1,6)
        viewModel.onTimeChanged(17, 59)
        viewModel.calculateFee()
        assertEquals(0, viewModel.feeLiveData.value)
    }

    @Test
    fun calculateFee_HourRegularDay_max_out() {
        viewModel.onVipChecked(false)
        viewModel.onDateChanged(1,6)
        viewModel.onTimeChanged(18, 0)
        viewModel.calculateFee()
        assertEquals(Constant.FEE_110, viewModel.feeLiveData.value)
    }

    @Test
    fun calculateFee_HourRegularDay_center() {
        viewModel.onVipChecked(false)
        viewModel.onDateChanged(1,6)
        viewModel.onTimeChanged(8, 49)
        viewModel.calculateFee()
        assertEquals(0, viewModel.feeLiveData.value)
    }
}