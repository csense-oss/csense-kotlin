package csense.kotlin.extensions

import csense.kotlin.*
import csense.kotlin.tests.assertions.*
import org.junit.*


class JvmExceptionsTests {

    @Test
    fun tryAndLogKClazz() = assertCalled {
        tryAndLog(
            this::class, "",
            logger = getLoggerForNameAssertion(this::class.java.simpleName, it)
        ) {
            throw Exception()
        }
    }


    @Test
    fun tryAndLogClazz() = assertCalled {
        tryAndLog(
            this::class.java, "",
            logger = getLoggerForNameAssertion(this::class.java.simpleName, it)
        ) {
            throw Exception()
        }
    }

    /**
     * Wraps the work of asserting the tag and calling a callback , and returning unit
     * @param name String
     * @param verifyCall Function0<Unit>
     * @return Function3<String, String, Throwable?, Unit>
     */
    private fun getLoggerForNameAssertion(
        name: String,
        verifyCall: EmptyFunction
    ): Function3<String, String, Throwable?, Unit> {
        return { tag: String, _: String, _: Throwable? ->
            tag.assert(name)
            verifyCall()
        }
    }


}