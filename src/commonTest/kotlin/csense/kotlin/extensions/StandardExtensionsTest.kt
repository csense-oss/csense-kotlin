package csense.kotlin.extensions

import csense.kotlin.*
import csense.kotlin.tests.assertions.*
import kotlin.test.*

class StandardExtensionsTest {

    @Test
    fun also() {
        var someCounter = 0

        val emptyFunc: EmptyFunction = { someCounter += 1 }
        "".also(emptyFunc)

        someCounter.assert(1)
    }
}