package org.csenseoss.kotlin.extensions.general

import org.csenseoss.kotlin.*
import org.csenseoss.kotlin.tests.assertions.*
import org.csenseoss.kotlin.tests.assertions.comparable.*
import kotlin.test.*

class AlsoTest {
    @Test
    fun also() {
        var someCounter = 0

        val emptyFunc: EmptyFunction = { someCounter += 1 }
        "".also(emptyFunc)

        someCounter.assert(1)
    }
}