package org.csenseoss.kotlin.patterns.expected.operators

import org.csenseoss.kotlin.patterns.expected.*
import org.csenseoss.kotlin.tests.assertions.*
import org.csenseoss.kotlin.tests.assertions.general.*
import kotlin.test.*

class AsErrorTypeOrNullTest {
    @Test
    fun badTypeShouldGiveNull() {
        val failed: Expected.Failed<RuntimeException> = Expected.Failed(RuntimeException())
        failed.asErrorTypeOrNull<ErrorTypeException>().assertNull()
    }

    @Test
    fun castAbleTypeShouldGiveTheType() {
        val failed: Expected.Failed<RuntimeException> = Expected.Failed(RuntimeException())
        failed.asErrorTypeOrNull<RuntimeException>().assertByEquals(failed)
        failed.asErrorTypeOrNull<RuntimeException>()!!.error.assertIs<RuntimeException>()
        failed.asErrorTypeOrNull<Exception>()!!.error.assertIs<Exception>()
    }
}