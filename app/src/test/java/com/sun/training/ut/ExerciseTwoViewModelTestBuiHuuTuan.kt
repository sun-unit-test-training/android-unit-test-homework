package com.sun.training.ut

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.sun.training.ut.data.model.Customer
import com.sun.training.ut.ui.exercise_two.ExerciseTwoViewModel
import org.junit.Before
import org.junit.Rule
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import org.junit.Assert.assertEquals
import org.junit.Test

@RunWith(MockitoJUnitRunner::class)
class ExerciseTwoViewModelTestBuiHuuTuan {

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
    fun validatePrice_vip_dayOfWeek_inTime() {
        val input = Customer(hour = 8, minute = 45, isVip = true, dayOfMonth = 1, monthOfYear = 3)
        viewModel.onVipChecked(input.isVip)
        viewModel.onDateChanged(input.dayOfMonth, input.monthOfYear)
        viewModel.onTimeChanged(input.hour, input.minute)
        viewModel.calculateFee()
        assertEquals(0, viewModel.feeLiveData.value)
    }

    @Test
    fun validatePrice_vip_dayOfWeek_timeOutLower() {
        val input = Customer(hour = 8, minute = 15, isVip = true, dayOfMonth = 1, monthOfYear = 3)
        viewModel.onVipChecked(input.isVip)
        viewModel.onDateChanged(input.dayOfMonth, input.monthOfYear)
        viewModel.onTimeChanged(input.hour, input.minute)
        viewModel.calculateFee()
        assertEquals(0, viewModel.feeLiveData.value)
    }

    @Test
    fun validatePrice_vip_dayOfWeek_timeOutHigher() {
        val input = Customer(hour = 18, minute = 0, isVip = true, dayOfMonth = 1, monthOfYear = 3)
        viewModel.onVipChecked(input.isVip)
        viewModel.onDateChanged(input.dayOfMonth, input.monthOfYear)
        viewModel.onTimeChanged(input.hour, input.minute)
        viewModel.calculateFee()
        assertEquals(0, viewModel.feeLiveData.value)
    }

    @Test
    fun validatePrice_vip_holiday_inTime() {
        val input = Customer(hour = 8, minute = 45, isVip = true, dayOfMonth = 1, monthOfYear = 5)
        viewModel.onVipChecked(input.isVip)
        viewModel.onDateChanged(input.dayOfMonth, input.monthOfYear)
        viewModel.onTimeChanged(input.hour, input.minute)
        viewModel.calculateFee()
        assertEquals(0, viewModel.feeLiveData.value)
    }

    @Test
    fun validatePrice_vip_holiday_timeOutLower() {
        val input = Customer(hour = 8, minute = 15, isVip = true, dayOfMonth = 1, monthOfYear = 5)
        viewModel.onVipChecked(input.isVip)
        viewModel.onDateChanged(input.dayOfMonth, input.monthOfYear)
        viewModel.onTimeChanged(input.hour, input.minute)
        viewModel.calculateFee()
        assertEquals(0, viewModel.feeLiveData.value)
    }

}