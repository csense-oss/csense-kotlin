package csense.kotlin.patterns.expected.expectedMapCatchingError.operations

import csense.kotlin.patterns.expected.expectedMapCatchingError.*
import csense.kotlin.tests.assertions.*
import kotlin.test.*

class IsTest {
    @Test
    fun expectedMapCatchingErrorErrorIsFailed() {
        ExpectedMapCatchingError.Exception<String>(Exception()).isFailed().assertFalse()
        ExpectedMapCatchingError.Failed("").isFailed().assertTrue()
        //for contracts
        val forContracts: ExpectedMapCatchingError<String> = ExpectedMapCatchingError.Failed("")
        if (forContracts.isFailed()) {
            forContracts.error.assertIs<String>()
        } else {
            forContracts.exception.assertIs<Exception>()
        }

    }

    @Test
    fun expectedMapCatchingErrorErrorIsException() {
        ExpectedMapCatchingError.Exception<String>(Exception()).isException().assertTrue()
        ExpectedMapCatchingError.Failed("").isException().assertFalse()
        //for contracts
        val forContracts: ExpectedMapCatchingError<String> = ExpectedMapCatchingError.Exception(Exception())
        if (forContracts.isException()) {
            forContracts.exception.assertIs<Exception>()
        } else {
            forContracts.error.assertIs<String>()
        }
    }


}