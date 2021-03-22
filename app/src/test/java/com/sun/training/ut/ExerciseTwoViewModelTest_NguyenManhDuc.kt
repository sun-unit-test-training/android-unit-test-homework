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
import kotlin.jvm.Throws


@RunWith(MockitoJUnitRunner::class)
class ExerciseTwoViewModelTest_NguyenManhDuc {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    private lateinit var viewModel: ExerciseTwoViewModel

    @Before
    @Throws(Exception::class)
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        viewModel = ExerciseTwoViewModel()
    }

    /**
    No.	                        1	2	3    4   5
    VIP	                        Y	N	N    N   N
    Weekend & Special Day	    _	Y	Y    N   N
    WorkTime                    _	Y	N    N   Y
    0円/yên	                    X	-	_    -   X
    110円/yên	                -	X	X    X   _
     **/

    @Test
    fun validateFee_withVip__returnFeeIsFree() {
        viewModel.onVipChecked(true)
        viewModel.calculateFee()
        assertEquals(Constant.FEE_FREE, viewModel.feeLiveData.value)
    }

    @Test
    fun validateFee_withoutVip_withWeekendOrSpecialDay_withWorkTime_returnFeeIs110() {
        viewModel.onVipChecked(false)
        viewModel.onDateChanged(20,3)
        viewModel.onTimeChanged(8, 50)
        viewModel.calculateFee()
        assertEquals(Constant.FEE_110, viewModel.feeLiveData.value)
    }

    @Test
    fun validateFee_withoutVip_withWeekendOrSpecialDay_withoutWorkTime_returnFeeIs110() {
        viewModel.onVipChecked(false)
        viewModel.onDateChanged(20,3)
        viewModel.onTimeChanged(19, 50)
        viewModel.calculateFee()
        assertEquals(Constant.FEE_110, viewModel.feeLiveData.value)
    }

    @Test
    fun validateFee_withoutVip_withRegularDay_withWorkTime_returnFeeIsFree() {
        viewModel.onVipChecked(false)
        viewModel.onDateChanged(2,3)
        viewModel.onTimeChanged(8, 50)
        viewModel.calculateFee()
        assertEquals(Constant.FEE_FREE, viewModel.feeLiveData.value)
    }

    @Test
    fun validateFee_withoutVip_withRegularDay_withoutWorkTime_returnFeeIs110() {
        viewModel.onVipChecked(false)
        viewModel.onDateChanged(2,3)
        viewModel.onTimeChanged(1, 50)
        viewModel.calculateFee()
        assertEquals(Constant.FEE_110, viewModel.feeLiveData.value)
    }
}
