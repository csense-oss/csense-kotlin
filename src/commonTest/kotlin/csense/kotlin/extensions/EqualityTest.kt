package csense.kotlin.extensions

import csense.kotlin.tests.assertions.*
import kotlin.test.*


class EqualityTest {

    @Test
    fun tIsNullOrEqualToOther() {
        val opt: String? = null
        opt.isNullOrEqualTo("test").assert(true, "is null")
        @Suppress("RedundantNullableReturnType")
        val nonOpt: String? = "test"
        nonOpt.isNullOrEqualTo("test").assert(true, "is equal")
        nonOpt.isNullOrEqualTo("test2").assert(false, "is not equal to test2")
        opt.isNullOrEqualTo("test2").assert(true, "is still null")
    }

}