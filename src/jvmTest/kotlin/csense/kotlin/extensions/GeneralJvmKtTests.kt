package csense.kotlin.extensions

import csense.kotlin.tests.assert
import org.junit.jupiter.api.Test

class GeneralJvmKtTests {

    @Test
    fun testType() {
        //Unable to test primitives as kotlin does not expose "java.lang"
        // thus we are unable to fix any mismatch between boxing and no boxing
        val stringClass: Class<String> = type()
        stringClass.assert("".javaClass)

        val sbClass = type<StringBuilder>()
        sbClass.assert(StringBuilder::class.java)
    }
}