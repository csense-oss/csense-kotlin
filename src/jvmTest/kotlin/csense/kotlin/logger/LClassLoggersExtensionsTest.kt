package csense.kotlin.logger

import csense.kotlin.tests.assertions.*
import org.junit.*

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

    @Test
    fun tLogClassError() {
        val exception = Throwable("my exception")
        var resultTag: String? = null
        var resultThrowable: Throwable? = null
        val logger = { tag: String, message: String, throwable: Throwable? ->
            if (message == "tLogClassError") {
                resultTag = tag
                resultThrowable = throwable
            }
        }
        L.errorLoggers.add(logger)
        logClassError(message = "tLogClassError", exception = exception)
        resultTag.assertNotNullAndEquals(LClassLoggersExtensionsTest::class.java.simpleName)
        resultThrowable.assertNotNullAndEquals(exception)
        L.errorLoggers.remove(logger)
    }

    @Test
    fun tLogClassWarning() {
        val exception = Throwable("my exception")
        var resultTag: String? = null
        var resultThrowable: Throwable? = null
        val logger = { tag: String, message: String, throwable: Throwable? ->
            if (message == "tLogClassWarning") {
                resultTag = tag
                resultThrowable = throwable
            }
        }
        L.warningLoggers.add(logger)
        logClassWarning(message = "tLogClassWarning", exception = exception)
        resultTag.assertNotNullAndEquals(LClassLoggersExtensionsTest::class.java.simpleName)
        resultThrowable.assertNotNullAndEquals(exception)
        L.warningLoggers.remove(logger)

    }

    @Test
    fun tLogClassDebug() {
        val exception = Throwable("my exception")
        var resultTag: String? = null
        var resultThrowable: Throwable? = null
        val logger = { tag: String, message: String, throwable: Throwable? ->
            if (message == "tLogClassDebug") {
                resultTag = tag
                resultThrowable = throwable
            }
        }
        L.debugLoggers.add(logger)
        logClassDebug(message = "tLogClassDebug", exception = exception)
        resultTag.assertNotNullAndEquals(LClassLoggersExtensionsTest::class.java.simpleName)
        resultThrowable.assertNotNullAndEquals(exception)
        L.debugLoggers.remove(logger)

    }

    @Test
    fun tLogClassProduction() {
        val exception = Throwable("my exception")
        var resultTag: String? = null
        var resultThrowable: Throwable? = null
        val logger = { tag: String, message: String, throwable: Throwable? ->
            if (message == "tLogClassProduction") {
                resultTag = tag
                resultThrowable = throwable
            }
        }
        L.productionLoggers.add(logger)
        logClassProduction(message = "tLogClassProduction", exception = exception)
        resultTag.assertNotNullAndEquals(LClassLoggersExtensionsTest::class.java.simpleName)
        resultThrowable.assertNotNullAndEquals(exception)
        L.productionLoggers.remove(logger)
    }

    @Test
    fun logCurrentStackTraceDebug() {
        var resultMessage: String? = null
        var resultThrowable: Throwable? = null
        val logger = { tag: String, message: String, throwable: Throwable? ->
            if (tag == "logCurrentStackTraceDebug") {
                resultMessage = message
                resultThrowable = throwable
            }
        }
        L.debugLoggers.add(logger)
        logCurrentStackTraceDebug(tag = "logCurrentStackTraceDebug")
        resultMessage.assertNotNullApply {
            assertContainsInOrder(
                listOf(LClassLoggersExtensionsTest::class.java.name + ".logCurrentStackTraceDebug"),
                ignoreCase = false
            )
        }
        resultThrowable.assertNull()
        L.debugLoggers.remove(logger)
    }

    @Test
    fun logCurrentStackTraceWarning() {
        var resultMessage: String? = null
        var resultThrowable: Throwable? = null
        val logger = { tag: String, message: String, throwable: Throwable? ->
            if (tag == "logCurrentStackTraceWarning") {
                resultMessage = message
                resultThrowable = throwable
            }
        }
        L.warningLoggers.add(logger)
        logCurrentStackTraceWarning(tag = "logCurrentStackTraceWarning")
        resultMessage.assertNotNullApply {
            assertContainsInOrder(
                listOf(LClassLoggersExtensionsTest::class.java.name + ".logCurrentStackTraceWarning"),
                ignoreCase = false
            )
        }
        resultThrowable.assertNull()
        L.warningLoggers.remove(logger)
    }

    @Test
    fun logCurrentStackTraceError() {
        var resultMessage: String? = null
        var resultThrowable: Throwable? = null
        val logger = { tag: String, message: String, throwable: Throwable? ->
            if (tag == "logCurrentStackTraceError") {
                resultMessage = message
                resultThrowable = throwable
            }
        }
        L.errorLoggers.add(logger)
        logCurrentStackTraceError(tag = "logCurrentStackTraceError")
        resultMessage.assertNotNullApply {
            assertContainsInOrder(
                listOf(LClassLoggersExtensionsTest::class.java.name + ".logCurrentStackTraceError"),
                ignoreCase = false
            )
        }
        resultThrowable.assertNull()
        L.errorLoggers.remove(logger)
    }

    @Test
    fun logCurrentStackTraceProd() {
        var resultMessage: String? = null
        var resultThrowable: Throwable? = null
        val logger = { tag: String, message: String, throwable: Throwable? ->
            if (tag == "logCurrentStackTraceProd") {
                resultMessage = message
                resultThrowable = throwable
            }
        }
        L.productionLoggers.add(logger)
        logCurrentStackTraceProd(tag = "logCurrentStackTraceProd")
        resultMessage.assertNotNullApply {
            assertContainsInOrder(
                listOf(LClassLoggersExtensionsTest::class.java.name + ".logCurrentStackTraceProd"),
                ignoreCase = false
            )
        }
        resultThrowable.assertNull()
        L.productionLoggers.remove(logger)
    }
}