package csense.kotlin.extensions

import csense.kotlin.*
import csense.kotlin.tests.assertions.*


class JvmExceptionsTests {


    /**
     * Wraps the work of asserting the tag and calling a callback , and returning unit
     * @param name String
     * @param verifyCall Function0<Unit>
     * @return Function3<String, String, Throwable?, Unit>
     */
    private fun getLoggerForNameAssertion(
        name: String,
        verifyCall: csense.kotlin.EmptyFunction
    ): Function3<String, String, Throwable?, Unit> {
        return { tag: String, _: String, _: Throwable? ->
            tag.assert(name)
            verifyCall()
        }
    }


}