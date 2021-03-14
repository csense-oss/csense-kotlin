package csense.kotlin.logger

import csense.kotlin.tests.assertions.*
import org.junit.jupiter.api.*

class LExtensionsTest {
    private val defaultLogAsserter: LoggingFunctionType<Any> = { tag: String, message: String, throwable: Throwable? ->
        tag.assert(this::class.java.simpleName)
        message.assert("message")
        throwable.assertNull()
    }

    @Test
    fun lLoggerDebugCls() {
        LLogger().apply {
            debugLoggers.add(defaultLogAsserter)
            debug(this@LExtensionsTest::class.java, "message")
        }
    }

    @Test
    fun lLoggerDebugKClass() {
        LLogger().apply {
            debugLoggers.add(defaultLogAsserter)
            debug(this@LExtensionsTest::class, "message")
        }
    }


    @Test
    fun lLoggerDebugLazyCls() {
        LLogger().apply {
            debugLoggers.add(defaultLogAsserter)
            debugLazy(this@LExtensionsTest::class.java, { "message" })
        }
    }

    @Test
    fun lLoggerDebugLazyKClass() {
        LLogger().apply {
            debugLoggers.add(defaultLogAsserter)
            debugLazy(this@LExtensionsTest::class, { "message" })
        }
    }


    @Test
    fun lLoggerWarningCls() {
        LLogger().apply {
            warningLoggers.add(defaultLogAsserter)
            warning(this@LExtensionsTest::class.java, "message")
        }
    }

    @Test
    fun lLoggerWarningKClass() {
        LLogger().apply {
            warningLoggers.add(defaultLogAsserter)
            warning(this@LExtensionsTest::class, "message")
        }
    }

    @Test
    fun lLoggerWarningLazy() {
        LLogger().apply {
            warningLoggers.add(defaultLogAsserter)
            warningLazy(this@LExtensionsTest::class.java, { "message" })
        }

    }

    @Test
    fun lLoggerWarningLazyKClass() {
        LLogger().apply {
            warningLoggers.add(defaultLogAsserter)
            warningLazy(this@LExtensionsTest::class, { "message" })
        }
    }

    @Test
    fun lLoggerErrorCls() {
        LLogger().apply {
            errorLoggers.add(defaultLogAsserter)
            error(this@LExtensionsTest::class.java, "message")
        }
    }

    @Test
    fun lLoggerErrorKClass() {
        LLogger().apply {
            errorLoggers.add(defaultLogAsserter)
            error(this@LExtensionsTest::class, "message")
        }
    }


    @Test
    fun lLoggerErrorLazyCls() {
        LLogger().apply {
            errorLoggers.add(defaultLogAsserter)
            errorLazy(this@LExtensionsTest::class.java, {"message"})
        }
    }


    @Test
    fun lLoggerErrorLazyKClass() {
        LLogger().apply {
            errorLoggers.add(defaultLogAsserter)
            errorLazy(this@LExtensionsTest::class, {"message"})
        }
    }


    @Test
    fun lLoggerLogProdCls() {
        LLogger().apply {
            productionLoggers.add(defaultLogAsserter)
            logProd(this@LExtensionsTest::class.java, "message")
        }
    }


    @Test
    fun lLoggerLogProdKClass() {
        LLogger().apply {
            productionLoggers.add(defaultLogAsserter)
            logProd(this@LExtensionsTest::class, "message")
        }
    }


    @Test
    fun lLoggerLogProdLazyCls() {
        LLogger().apply {
            productionLoggers.add(defaultLogAsserter)
            logProdLazy(this@LExtensionsTest::class.java, {"message"})
        }
    }

    @Test
    fun lLoggerLogProdLazyKClass() {
        LLogger().apply {
            productionLoggers.add(defaultLogAsserter)
            logProdLazy(this@LExtensionsTest::class, { "message" })
        }
    }


}