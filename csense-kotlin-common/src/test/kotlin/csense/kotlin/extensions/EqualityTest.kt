package csense.kotlin.extensions

import csense.kotlin.test.assertions.*
import kotlin.test.*


class EqualityTest {

    @Test
    fun isNullOrEqualTo() {
        val opt: String? = null
        opt.isNullOrEqualTo("test").assert(true, "is null")
        val nonOpt: String? = "test"
        nonOpt.isNullOrEqualTo("test").assert(true, "is equal")
        nonOpt.isNullOrEqualTo("test2").assert(false, "is not equal to test2")
        opt.isNullOrEqualTo("test2").assert(true, "is still null")
    }
}