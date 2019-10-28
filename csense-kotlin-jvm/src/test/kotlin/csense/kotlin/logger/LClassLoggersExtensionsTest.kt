package csense.kotlin.logger

import csense.kotlin.test.assertions.assert
import csense.kotlin.test.assertions.assertContains
import org.junit.Test

class LClassLoggersExtensionsTest {

    @Test
    fun testLogCurrentStackTrace() {

        var stackString = ""
        var tagString = ""
        fun inner() {
            logCurrentStackTrace(
                    "tag",
                    { tag: String, message: String, _: Throwable? ->
                        stackString = message
                        tagString = tag
                    })
        }
        inner()
        stackString.assertContains(::testLogCurrentStackTrace.name)
        stackString.assertContains(LClassLoggersExtensionsTest::class.java.name)
        tagString.assert("tag")
    }

}