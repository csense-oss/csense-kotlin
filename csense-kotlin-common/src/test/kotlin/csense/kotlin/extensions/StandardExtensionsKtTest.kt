package csense.kotlin.extensions

import csense.kotlin.EmptyFunction
import csense.kotlin.test.assertions.assert
import kotlin.test.Test

class StandardExtensionsKtTest {

    @Test
    fun runIfNotNull() {
        var counter = 0
        val potentialString = ""
        potentialString.runIfNotNull { counter += 1 }
        counter.assert(1)
        //the following should not crash..
        potentialString.runIfNotNull<String, String>(null)


    }

    @Test
    fun also() {
        var someCounter = 0

        val emptyFunc: EmptyFunction = { someCounter += 1 }
        "".also(emptyFunc)

        someCounter.assert(1)
    }
}