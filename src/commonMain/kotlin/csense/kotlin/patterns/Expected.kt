@file:Suppress("unused", "NOTHING_TO_INLINE")

package csense.kotlin.patterns

import csense.kotlin.*

//TODO polish the experince.

/**
 * Created by Kasper Tvede
 *
 *
 * The Expected "pattern" is a construct that allows the api (writter) to avoid thinking in terms
 * of either "throw exceptions " or return "values" indicating function failure.
 * The idea is that you instead return this, and then letting the user of that function decide
 * how to handle errors
 *
 *
 * example:
 * Java's string.indexOf function, which returns either an index or "-1" if not found
 * here the users are to know that -1 means "not found" akk an error / bad result
 * that could be expressed as a "ExpectedFailed" and a found could be "ExpectedSuccess"
 *
 * another example is:
 * java's String.getBytes( charset) where if the charset is not found an exception is thrown.
 * however, try catches are slow in general, and clutters up what could be a simple "if" statement
 * instead.
 *
 * sample:
 * val bytes :ByteArray? = try {"".getBytes("unknownCharset") }catch(Exception) {null}
 * whereas if it returned Expected it would look like this:
 * val expectedBytes : Expectetd<Bytes> = "".getBytes("unknownCharset")
 * expectedBytes.use{
 *    //if expectedBytes is actually valid
 * }
 *
 * and if an exception is expected, one can simply use the value directly,
 * which throws in case its an error
 *
 */
@Deprecated("Will be removed in 0.40, as it will be placed in either datastructures / algorithm module", level = DeprecationLevel.WARNING)
sealed class Expected<out Value> {

    /**
     * if this expectation is actually an error
     */
    abstract val isError: Boolean
    /**
     * The potential value of this expectation
     */
    abstract val value: Value

    /**
     * the potential error of this expectation
     */
    abstract val error: Throwable?
    /**
     * If this is valid (not an error)
     */
    inline val isValid: Boolean
        get() = !isError

    /**
     *
     */
    companion object {
        /**
         * Create a successfull expectation from the given value.
         * @param value T
         * @return ExpectedSuccess<T>
         */
        fun <T> success(value: T): ExpectedSuccess<T> {
            return ExpectedSuccess(value)
        }

        /**
         * Creates a failed expectation from the given error (or default Exception message if non is given)
         * @param exception Throwable
         * @return ExpectedFailed<T>
         */
        fun <T> failed(exception: Throwable = Exception(defaultExceptionMessage)): ExpectedFailed<T> {
            return ExpectedFailed(exception)
        }
    }
}

/**
 * Creates an expected success result from the given value
 *
 */

inline fun <T> expectedSucceded(value: T): ExpectedSuccess<T> =
        Expected.success(value)

/**
 * Creates an expected failed result from the given error / exception .
 */

inline fun <T> expectedFailed(
        exception: Throwable = Exception(defaultExceptionMessage)): ExpectedFailed<T> =
        Expected.failed(exception)

/**
 * a default message in case non is provided
 */
const val defaultExceptionMessage = "Failed with no exception"


/**
 * Models the failure of some expected computation
 */
class ExpectedFailed<out T>(exception: Throwable) : Expected<T>() {
    override val isError: Boolean = true

    override val error: Throwable = exception

    override val value: T
        get() = throw Exception("", error)

}

/**
 * Models the success of some expected computation
 */
class ExpectedSuccess<out T>(successValue: T) : Expected<T>() {
    override val value: T = successValue
    //TODO consider removing this, and instead only let extensions make this "based" on type switching.
    override val error: Exception? = null

    override val isError: Boolean = false

}

/**
 * Use the expected value iff it was a success
 */
inline fun <T, U> Expected<T>.use(action: (T) -> U): U? {
    return if (isValid) {
        action(value)
    } else {
        null
    }
}

/**
 * Performs the given action iff this is valid.
 * @receiver Expected<T>
 * @param action FunctionUnit<T>
 */
inline fun <T> Expected<T>.ifValid(action: FunctionUnit<T>) {
    if (this is ExpectedSuccess) {
        action(value)
    }
}

/**
 * Performs the given function iff this is an error
 * @receiver Expected<T>
 * @param action FunctionUnit<Throwable>
 */
inline fun <T> Expected<T>.ifError(action: FunctionUnit<Throwable>) {
    if (this is ExpectedFailed) {
        action(error)
    }
}

/**
 * A simple "if else" wrapper
 * @receiver Expected<T>
 * @param onValid FunctionUnit<T>
 * @param onError FunctionUnit<Throwable>
 */
inline fun <T> Expected<T>.ifValidOr(onValid: FunctionUnit<T>,
                                     onError: FunctionUnit<Throwable>) {
    when (this) {
        is ExpectedFailed -> onError(this.error)
        is ExpectedSuccess -> onValid(this.value)
    }
}

/**
 * a simple mapper for the "if else" case of an expected.
 * @receiver Expected<T>
 * @param onValid Function1<T, U>
 * @param onError Function1<Throwable, U>
 * @return U
 */
inline fun <T, U> Expected<T>.mapIfValidOr(onValid: Function1<T, U>,
                                           onError: Function1<Throwable, U>): U {
    return when (this) {
        is ExpectedFailed -> onError(this.error)
        is ExpectedSuccess -> onValid(this.value)
    }
}
