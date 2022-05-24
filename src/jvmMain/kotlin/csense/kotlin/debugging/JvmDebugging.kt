@file:Suppress("NOTHING_TO_INLINE")
@file:OptIn(ExperimentalCsenseApi::class)

package csense.kotlin.debugging

import csense.kotlin.annotations.*

@ExperimentalCsenseApi
@JvmInline
public value class Debugging(
    public val caller: StackTraceElement
) {
    public companion object {

        @Throws(RuntimeException::class)
        public fun fromCallingMethod(): Debugging {
            val stackTraceElement: StackTraceElement = Thread.currentThread()
                .stackTrace
                .findCallerMethod() ?: throw RuntimeException("Could not find caller method")
            return Debugging(stackTraceElement)
        }

        private fun Array<StackTraceElement>.findCallerMethod(): StackTraceElement? {
            val ourClassName = this@Companion::class.java.name
            return findItemFollowingSeriesBy {
                it.className.equals(ourClassName)
            }
        }

        /**
         * Searches (incrementally) for a "series" (predicate returns true) and then returns the element following that series (if any).
         * @receiver Array<T>
         * @param predicate Function1<T, Boolean>
         * @return T?
         */
        //TODO consider this as a "searching" specific extension etc?
        private fun <T> Array<T>.findItemFollowingSeriesBy(predicate: (T) -> Boolean): T? {
            var haveFoundSeries = false
            forEach { item ->
                val isSeries = predicate(item)
                if (haveFoundSeries && !isSeries) {
                    return@findItemFollowingSeriesBy item
                }
                haveFoundSeries = isSeries
            }
            return null
        }
    }
}

@ExperimentalCsenseApi
public inline val debugging: Debugging
    get() = Debugging.fromCallingMethod()

public inline fun Debugging.getCurrentMethodName(): String {
    return caller.methodName
}

public inline fun Debugging.getCurrentMethodLineNumber(): Int {
    return caller.lineNumber
}

public inline fun Debugging.getCurrentMethodFileName(): String {
    return caller.fileName ?: ""
}

public inline fun Debugging.getCurrentMethodFileNameAndLineNumber(): String {
    return "${caller.fileName ?: "-"}:${caller.lineNumber}"
}

public inline fun Debugging.getCurrentMethodCanonicalName(): String {
    return caller.className + "." + caller.methodName
}

public inline fun Debugging.getCurrentMethodInformation(): String {
    return caller.toString()
}