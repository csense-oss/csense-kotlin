package csense.kotlin.extensions

import csense.kotlin.logger.*
import csense.kotlin.test.assertions.*
import org.junit.*

class LExtensionsKtTest {

    @Test
    fun testLogDebug() {
        L.debugLoggers.clear()
        var counter = 0
        L.debugLoggers.add { tag, message, ex ->
            tag.assert("LExtensionsKtTest")
            message.assert("test")
            counter += 1
            Unit
        }
        logClassDebug("test")
        counter.assert(1)
    }

    @Test
    fun testLogError() {

    }

    @Test
    fun testLogWarning() {

    }

    @Test
    fun testLogProduction() {

    }

}