package csense.kotlin.patterns

import csense.kotlin.tests.assertions.*
import kotlin.test.Test

class ExpectedTest {

    @Test
    fun expectedSucceded() {
        val testSuccess = expectedSucceded(42)
        testSuccess.isValid.assert(true)
        testSuccess.isError.assert(false)
        testSuccess.error.assertNull()
        testSuccess.value.assert(42)
    }

    @Test
    fun expectedFailed() {
        val testFailed = expectedFailed<Int>()
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
        }).assert("42")

        val failed = expectedFailed<Int>(Exception("errorMessage"))
        failed.mapIfValidOr({
            failTest("should not be called")
        }, {
            it.message
        }).assertNotNullAndEquals("errorMessage")
    }

    @Test
    fun isValid() {
        val success = expectedSucceded(42)
        success.isValid.assertTrue()
        val failed = expectedFailed<Int>(Exception("errorMessage"))
        failed.isValid.assertFalse()
    }

    @Test
    fun value() {
        val success = expectedSucceded(42)
        success.value.assert(42)
        val failed = expectedFailed<Int>(Exception("errorMessage"))
        assertThrows<Exception> {
            //should throw on access
            val failedValue = failed.value
            println(failedValue)
        }


    }

    @Test
    fun success() {
        val testSuccess = Expected.success(42)
        testSuccess.isValid.assert(true)
        testSuccess.isError.assert(false)
        testSuccess.error.assertNull()
        testSuccess.value.assert(42)

    }

    @Test
    fun failed() {
        val testFailed = Expected.failed<Int>()
        testFailed.isValid.assert(false)
        testFailed.isError.assert(true)
        assertThrows<Exception> {
            testFailed.value.assertNull()
        }
        testFailed.error.assertNotNull()
    }
}

class ExpectedFailedTest {
    @Test
    fun value() {
        val expected = expectedFailed<String>()
        expected.isError.assertTrue()
        assertThrowsCause<Exception, Exception> { expected.value }

        val expectedWith = expectedFailed<String>(MyException())
        expectedWith.isError.assertTrue()
        assertThrowsCause<Exception, MyException> { expectedWith.value }
    }

    private class MyException : Exception()
}