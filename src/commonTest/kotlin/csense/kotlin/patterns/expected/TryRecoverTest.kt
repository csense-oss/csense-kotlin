package csense.kotlin.patterns.expected

import csense.kotlin.tests.assertions.*
import kotlin.test.*

class TryRecoverTest {
    class ExpectedNothingErrorTryRecoverTransform {

        @Test
        fun success() {
            val exp: Expected<String, Int> = Expected.Success("test")
            val res = exp.tryRecover { shouldNotBeCalled() }
            res.assertIs<Expected.Success<String>>()
            res.value.assert("test")
            //should cause a compiler error

//            Expected.Success(42).tryRecover {  }
//            val asNothing: Expected<String, Nothing> = Expected.Success("")
//            //should cause a compiler error
//            asNothing.tryRecover {  }
        }


        @Test
        fun failedToSuccess() {
            val exp: Expected<String, Int> = Expected.Failed(89)
            exp.tryRecover { it.asSuccess() }.assertIsApply<Expected.Success<Int>> { value.assert(89) }
            val complex: Expected<String, Int> = exp.tryRecover {
                "1234".asSuccess()
            }
            complex.assertIs<Expected.Success<String>>()
            complex.value.assert("1234")
        }


        @Test
        fun failedToFailed() {
            val exp: Expected<String, Int> = Expected.Failed(89)
            exp.tryRecover { it.asFailed() }.assertIsApply<Expected.Failed<Int>> {
                error.assert(89)
            }
        }

    }

}