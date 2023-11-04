package csense.kotlin.extensions.general

import csense.kotlin.tests.assertions.*
import kotlin.test.*

class IfNullTest {


    @Test
    fun tOrIfNull() {
        val optString: String? = null
        optString.orIfNull("test").assert("test")
        optString.orIfNull("1234").assert("1234")

        @Suppress("RedundantNullableReturnType")
        val str: String? = "test"
        str.orIfNull("1234").assert("test")

        @Suppress("RedundantNullableReturnType")
        val number: Int? = 42
        number.orIfNull(11).assert(42)
        val nullNumber: Int? = null
        nullNumber.orIfNull(11).assert(11)
    }

    @Test
    fun tOrIfNullLazy() {
        val optInt: Int? = null
        optInt.orIfNullLazy { 99 }.assert(99)

        @Suppress("RedundantNullableReturnType")
        val optInt2: Int? = 42
        optInt2.orIfNullLazy { 111 }.assert(42)
    }
}