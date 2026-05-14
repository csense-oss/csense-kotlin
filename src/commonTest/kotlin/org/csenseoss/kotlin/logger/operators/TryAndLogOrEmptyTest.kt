package org.csenseoss.kotlin.logger.operators

import org.csenseoss.kotlin.tests.assertions.collections.iterable.assert
import kotlin.test.Test

class TryAndLogOrEmptyTest {
    @Test
    fun onSuccess() {
        val result: List<String> = tryAndLogOrEmpty {
            listOf("42")
        }
        result.assert("42")
    }

}