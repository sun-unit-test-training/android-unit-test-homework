package com.sun.training.ut

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.sun.training.ut.data.Constant
import com.sun.training.ut.ui.exercise_two.ExerciseTwoViewModel
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import java.lang.Exception
import kotlin.jvm.Throws

@RunWith(MockitoJUnitRunner::class)
class ExerciseTwoViewModelTest_NguyenVanHieuC {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    private lateinit var viewModel : ExerciseTwoViewModel

    @Before
    @Throws(Exception::class)
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        viewModel = ExerciseTwoViewModel()
    }

    @Test
    fun validateFee_withIsVip_normalDay_return0() {
        viewModel.onVipChecked(true)
        viewModel.onDateChanged(22,3)
        viewModel.onTimeChanged(9,10)
        viewModel.calculateFee()
        assertEquals(0, viewModel.feeLiveData.value)
    }

    @Test
    fun validateFee_withNoVip_holiday_return110() {
        viewModel.onVipChecked(false)
        viewModel.onDateChanged(2,9)
        viewModel.calculateFee()
        assertEquals(Constant.FEE_110, viewModel.feeLiveData.value)
    }

    @Test
    fun validateFee_withNoVip_normalDay_in845to1759_return0() {
        viewModel.onVipChecked(false)
        viewModel.onDateChanged(4,10)
        viewModel.onTimeChanged(8,46)
        viewModel.calculateFee()
        assertEquals(0, viewModel.feeLiveData.value)
    }

    @Test
    fun validateFee_withNoVip_normalDay_out845to1759_return110() {
        viewModel.onVipChecked(false)
        viewModel.onDateChanged(6,9)
        viewModel.onTimeChanged(1,30)
        viewModel.calculateFee()
        assertEquals(Constant.FEE_110, viewModel.feeLiveData.value)
    }

    @Test
    fun validateFee_withNoVip_saturday_sunday_return110() { // test fail because unspecified cases Saturday and Sunday
        viewModel.onVipChecked(false)
        viewModel.onDateChanged(21,3)
        viewModel.onTimeChanged(9,30)
        viewModel.calculateFee()
        assertEquals(Constant.FEE_110, viewModel.feeLiveData.value)
    }
}