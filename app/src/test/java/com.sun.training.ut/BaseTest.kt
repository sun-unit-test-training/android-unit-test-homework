package com.sun.training.ut

import androidx.annotation.CallSuper
import io.mockk.MockKAnnotations
import org.junit.Before
import org.mockito.MockitoAnnotations
import kotlin.jvm.Throws

open class BaseTest{
    @Before
    @Throws(Exception::class)
    @CallSuper
    open fun setUp() {
        // make annotation work
        MockitoAnnotations.initMocks(this)
        MockKAnnotations.init(this, relaxUnitFun = true)
    }
}