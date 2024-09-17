package org.csenseoss.kotlin.patterns.expected.operators

import csense.kotlin.tests.assertions.*
import org.csenseoss.kotlin.patterns.expected.*
import kotlin.test.*

class SuccessOrFailedTest {
    @Test
    fun nullSuccess() {
        Expected.successOrFailed(potentialSuccess = null, potentialErrorOrFallback = "error")
            .assertIsApply<Expected.Failed<String>> {
                error.assert("error")
            }

    }


    @Test
    fun success() {
        Expected.successOrFailed(potentialSuccess = "test", potentialErrorOrFallback = "error")
            .assertIsApply<Expected.Success<String>> {
                value.assert("test")
            }
    }
}