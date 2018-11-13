package csense.kotlin.extensions

import csense.kotlin.test.*
import org.junit.*

class GeneralJvmKtTests {

    @Test
    fun testType() {
        //Unable to test primitives as kotlin does not expose "java.lang"
        // thus we are unable to fix any mismatch between boxing and no boxing
        val stringClass: Class<String> = type()
        stringClass.assert("".javaClass)

        val SbClass = type<StringBuilder>()
        SbClass.assert(StringBuilder::class.java)


    }

}