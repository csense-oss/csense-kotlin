package org.csenseoss.kotlin.patterns.expected.operators

import org.csenseoss.kotlin.patterns.expected.*
import org.csenseoss.kotlin.tests.assertions.*
import org.csenseoss.kotlin.tests.assertions.comparable.*
import org.csenseoss.kotlin.tests.assertions.general.*
import kotlin.test.*

class SuccessOrFailedTest {
    @Test
    fun nullSuccess() {
        val value: Expected<Nothing, String> = Expected.successOrFailed(
            potentialSuccess = null,
            potentialErrorOrFallback = "error"
        )
        value.assertIs<Expected.Failed<String>>()
        value.error.assert("error")
    }


    @Test
    fun success() {
        val value: Expected<String, String> = Expected.successOrFailed(
            potentialSuccess = "test",
            potentialErrorOrFallback = "error"
        )
        value.assertIs<Expected.Success<String>>()
        value.value.assert("test")
    }
}