package csense.kotlin.patterns

import csense.kotlin.test.*
import csense.kotlin.test.assertions.*
import kotlin.test.*

class ExpectedKtTest {

    @Test
    fun expectedSucceded() {
        val testSuccess = Expected.success(42)
        testSuccess.isValid.assert(true)
        testSuccess.isError.assert(false)
        testSuccess.error.assertNull()
        testSuccess.value.assert(42)
    }

    @Test
    fun expectedFailed() {
        val testFailed = Expected.failed<Int>()
        testFailed.isValid.assert(false)
        testFailed.isError.assert(true)
        assertThrows<Exception> {
            testFailed.value.assertNull()
        }
        testFailed.error.assertNotNull()
    }

    @Test
    fun use() {
        val success20 = expectedSucceded(20)
        success20.isValid.assert(true)
        success20.isError.assert(false)
        success20.error.assertNull()
        success20.value.assert(20)

        val failedText = "failed"
        val failedException = RuntimeException(failedText)
        val failedInt = expectedFailed<Int>(failedException)
        (failedInt.error == failedException).assert(true)
        failedInt.error.message.assertNotNullAndEquals(failedText)
        failedInt.isError.assert(true)
        failedInt.isValid.assert(false)


        var successCounter = 1
        success20.use { successCounter -= 1 }

        successCounter.assert(0, "should 'use' on success")

        var failedCounter = 1
        failedInt.use { failedCounter -= 1 }
        failedCounter.assert(1, "should not 'use' on success")

    }

    @Test
    fun useAsync() = testAsync {
        val success42 = expectedSucceded(42)
        var counter = 1
        success42.useAsync {
            counter -= 1
        }
        counter.assert(0, "should run async first")

        var failedCounter = 1
        val failed = expectedFailed<Int>()
        failed.useAsync {
            failedCounter -= 1
        }
        failedCounter.assert(1, "cannot use a failed result.")
    }

    @Test
    fun ifValid() {
        val success = expectedSucceded(42)
        var counter = 0
        success.ifValid {
            counter += 1
        }
        counter.assert(1, "should have executed the action")


        val failure = expectedFailed<Int>(Exception())
        failure.ifValid {
            counter += 1
        }
        counter.assert(1, "should not have executed the action")

    }

    @Test
    fun ifError() {
        val success = expectedSucceded(42)
        var counter = 0
        success.ifError {
            counter += 1
        }
        counter.assert(0, "shsould not have executed the action")


        val failure = expectedFailed<Int>(Exception())
        failure.ifError {
            counter += 1
        }
        counter.assert(1, "should  have executed the action")
    }

    @Test
    fun ifValidOr() {
        val success = expectedSucceded(42)
        success.ifValidOr({ it.assert(42) }, { failTest("should not be called") })

        val failed = expectedFailed<Int>(Exception("errorMessage"))
        failed.ifValidOr({ failTest("should not be called") }, { it.message.assertNotNullAndEquals("errorMessage") })
    }

    @Test
    fun mapIfValidOr() {
        val success = expectedSucceded(42)
        success.mapIfValidOr({
            it.toString()
        }, {
            failTest("should not be called")
            ""
        }).assert("42")

        val failed = expectedFailed<Int>(Exception("errorMessage"))
        failed.mapIfValidOr({
            failTest("should not be called")
            ""
        }, {
            it.message
        }).assertNotNullAndEquals("errorMessage")
    }
}