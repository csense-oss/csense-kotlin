package csense.kotlin.extensions

import csense.kotlin.EmptyFunction
import csense.kotlin.tests.assertions.assert
import kotlin.test.Test

class StandardExtensionsTest {

    @Test
    fun also() {
        var someCounter = 0

        val emptyFunc: EmptyFunction = { someCounter += 1 }
        "".also(emptyFunc)

        someCounter.assert(1)
    }
}