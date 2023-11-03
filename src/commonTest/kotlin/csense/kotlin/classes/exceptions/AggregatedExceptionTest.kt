package csense.kotlin.classes.exceptions

import csense.kotlin.tests.assertions.*
import kotlin.test.*

class AggregatedExceptionTest {

    private val cause = NumberFormatException()
    private val relatedExceptions: List<Throwable> = listOf(
        NoSuchElementException("index out of bounds"),
        NotImplementedError()
    )
    private val aggregatedException = AggregatedException(
        message = "myMessage",
        cause = cause,
        relatedExceptions = relatedExceptions
    )
    private val expectedToString =
        "AggregatedException(message=myMessage, cause=NumberFormatException, relatedExceptions=[NoSuchElementException: index out of bounds, NotImplementedError: An operation is not implemented.])"

    @Test
    fun toStringContainsRelatedExceptions() {
        val toString: String = aggregatedException.toString()
        toString.removePackageNameSpaces().assert(expectedToString)
    }

    @Test
    fun containsRelatedExceptionsInStackTrace() {
        val stackTrace: String = aggregatedException.stackTraceToString()
        stackTrace.removePackageNameSpaces().assertStartsWith(expectedToString)
        stackTrace.assertContains("Caused by:")
    }

    //Due to platform differences, (say js / native) there "might" be a "kotlin." in front of the class types.
    private fun String.removePackageNameSpaces(): String {
        return replace(oldValue = "kotlin.", newValue = "")
            .replace("java.lang.", "")
            .replace("java.util.", "")
    }
}