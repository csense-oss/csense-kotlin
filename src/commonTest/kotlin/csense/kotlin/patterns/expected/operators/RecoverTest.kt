package csense.kotlin.patterns.expected.operators

import csense.kotlin.patterns.expected.*
import csense.kotlin.tests.assertions.*
import kotlin.test.*

class RecoverTest {
    class ExpectedValueErrorRecoverTransform {


        @Test
        fun success() {
            val exp: Expected<String, *> = Expected.Success(value = "42").asExpected()
            exp.recover { shouldNotBeCalled() }.value.assert(value = "42")

//            val nothingError: Expected<String, Nothing> = Expected.Success("test")
//            //should cause a compiler error
//            nothingError.recover {  }
//
//            //should cause a compiler error
//            Expected.Success(42).recover {  }
        }


        @Test
        fun failed() {
            val exp: Expected<String, Int> = Expected.Failed(error = 1)
            exp.recover { "test" }.value.assert(value = "test")

            val expNothing: Expected<Nothing, Int> = Expected.Failed(999)
            expNothing.recover { "hello" }.value.assert("hello")
        }
    }

    class ExpectedFailedErrorRecoverTransform {

        @Test
        fun expectedFailedErrorRecoverTransform(): Unit = assertCalled { shouldBeCalled: () -> Unit ->
            val result: Expected.Success<String> = Expected.Failed(error = "test").recover { _: String ->
                shouldBeCalled()
                "result"
            }
            result.assertSuccessWith("result")
        }
    }
}