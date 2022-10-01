package csense.kotlin.patterns.expected

import csense.kotlin.tests.assertions.*
import kotlin.test.*

class RecoverTest {
    class ExpectedValueErrorRecoverTransform {


        @Test
        fun success() {
            val exp: Expected<String, Int> = Expected.Success("42")
            exp.recover { shouldNotBeCalled() }.value.assert("42")

            @Suppress("UNUSED_VARIABLE")
            val nothingError: Expected<String, Nothing> = Expected.Success("test")
            //should cause a compiler error
//            nothingError.recover {  }

            //should cause a compiler error
//            Expected.Success(42).recover {  }
        }


        @Test
        fun failed() {
            val exp: Expected<String, Int> = Expected.Failed(1)
            exp.recover { "test" }.value.assert("test")

            val expNothing: Expected<Nothing, Int> = Expected.Failed(999)
            expNothing.recover { "hello" }.value.assert("hello")
        }
    }

}