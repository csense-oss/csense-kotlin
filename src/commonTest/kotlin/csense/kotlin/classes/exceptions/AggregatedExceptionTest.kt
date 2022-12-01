package csense.kotlin.classes.exceptions

import csense.kotlin.tests.assertions.*
import kotlin.test.*

class AggregatedExceptionTest {

    private val cause = NumberFormatException()
    private val relatedExceptions = listOf(
        NoSuchElementException("index out of bounds"),
        NotImplementedError()
    )
    private val aggregatedException = AggregatedException(
        message = "myMessage",
        cause = cause,
        relatedExceptions = relatedExceptions
    )
    private val expectedToString =
        "AggregatedException(message=myMessage, cause=java.lang.NumberFormatException, relatedExceptions=[java.util.NoSuchElementException: index out of bounds, kotlin.NotImplementedError: An operation is not implemented.])"

    @Test
    fun toStringContainsRelatedExceptions() {
        val toString = aggregatedException.toString()
        toString.assert(expectedToString)
    }

    @Test
    fun containsRelatedExceptionsInStackTrace() {
        val stackTrace = aggregatedException.stackTraceToString()
        stackTrace.assertStartsWith(expectedToString)
        stackTrace.assertContains("Caused by:")
    }
}