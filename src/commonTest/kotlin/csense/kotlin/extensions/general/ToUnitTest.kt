package csense.kotlin.extensions.general

import csense.kotlin.tests.assertions.*
import kotlin.test.*

class ToUnitTest {

    @Test
    fun toUnit() {
        ("".toUnit() == Unit).assertTrue()
        (null.toUnit() == Unit).assertTrue()
    }

    @Test
    fun toUnitFunction() {
        var counter = 0
        val fnc1 = { _: String -> counter += 1; "" }.toUnitFunction()

        @Suppress("UNUSED_VARIABLE")
        val data: Unit =
            fnc1("") //this would case a complie time error, and thus validating that the function does what
        //It's supposed to type wise.
        counter.assert(1, "should still execute function even though we discard the result")
    }

}