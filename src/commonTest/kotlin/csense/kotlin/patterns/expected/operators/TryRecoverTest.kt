package csense.kotlin.patterns.expected.operators

import csense.kotlin.patterns.expected.*
import csense.kotlin.tests.assertions.*
import kotlin.test.*

class TryRecoverTest {
    class ExpectedValueErrorTryRecoverTransform {

        @Test
        fun success() {
            val exp: Expected<String, Int> = Expected.Success("test")
            val res: Expected<String, Int> = exp.tryRecover { shouldNotBeCalled() }
            res.assertSuccessWith("test")

//            //should cause a compiler error
//            Expected.Success(42).tryRecover {  }
//            val asNothing: Expected<String, Nothing> = Expected.Success("")
//            //should cause a compiler error
//            asNothing.tryRecover {  }
        }


        @Test
        fun failedToSuccess() {
            val exp: Expected<String, Int> = Expected.Failed(89)

            exp.tryRecover { it: Int ->
                "$it".asSuccess()
            }.assertSuccessWith("89")

            val complex: Expected<String, Int> = exp.tryRecover { _: Int ->
                "1234".asSuccess()
            }
            complex.assertSuccessWith("1234")
        }


        @Test
        fun failedToFailed() {
            val exp: Expected<String, Int> = Expected.Failed(89)
            exp.tryRecover { it: Int ->
                it.asFailed()
            }.assertFailedWith(89)
        }

    }

}

