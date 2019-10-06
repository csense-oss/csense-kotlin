@file:Suppress("unused", "NOTHING_TO_INLINE", "MissingTestFunction")

package csense.kotlin.test.assertions

import kotlin.test.*


fun failTest(message: String = ""): Nothing {
    fail(message)
}

fun <T> Any.assertAs(otherValue: T, message: String = "") {
    @Suppress("UNCHECKED_CAST") //this is expected
    // we are just making life easier for testing, if it throws, then its "all right" for a test.
    assertEquals(this as? T, otherValue, message)
}


fun Any?.assertNotNull(message: String = "") {
    assertNotNull(this, message)
}

fun Any?.assertNull(message: String = "") {
    assertNull(this, message)
}


fun <T> T?.assertNotNullApply(message: String = "", action: T.() -> Unit) {
    this.assertNotNull(message)
    this?.let(action)
}

fun <T> T?.assertNotNullAndEquals(other: T?, message: String = "value was $this, expected $other") {
    this.assertNotNull(message)
    assertEquals(other, this, message)
}

inline fun <reified T : Exception> assertThrows(
        message: String = "should throw",
        messageWrongException: String = "wrong exception type",
        crossinline action: () -> Unit) {

    try {
        action()
        failTest("Expected an exception of type ${T::class} but got no exceptions\r$message")
    } catch (exception: Exception) {
        if (exception !is T) {
            failTest("Expected an exception of type \"${T::class}\" " +
                    "but got exception of type \"${exception::class}\" instead." +
                    "\r$messageWrongException")
        }
        //all is good / expected.
    }
}

/**
 * Asserts that the given action calls the callback function "times" times otherwise fails with the given message
 * @param message String the error message if it fails (times the callback called != times)
 * @param times Int the number of times we expected the callback to be called
 * @param action Function1<[@kotlin.ParameterName] Function0<Unit>, Unit> the action, getting the callback function
 */
fun assertCalled(
        message: String = GeneralStrings.assertCalledMessage,
        times: Int = 1,
        action: (callback: () -> Unit) -> Unit
) {
    var counter = 0
    action { counter += 1 }
    counter.assert(times, message)
}

/**
 * Asserts that the given action calls the callback function 1 times otherwise fails with the given message
 * @param message String the error message if it fails (times the callback called != 1)
 * @param action Function1<[@kotlin.ParameterName] Function0<Unit>, Unit> the action, getting the callback function
 */
fun assertCalled(
        message: String = GeneralStrings.assertCalledMessage,
        action: (callback: () -> Unit) -> Unit
) = assertCalled(message, 1, action)

/**
 * Asserts that the given action calls the callback function 0 times / not gets called otherwise fails with the given message
 * @param message String
 * @param action Function1<[@kotlin.ParameterName] Function0<Unit>, Unit>
 */
fun assertNotCalled(
        message: String = GeneralStrings.assertNotCalledMessage,
        action: (callback: () -> Unit) -> Unit
) = assertCalled(message, 0, action)

private object GeneralStrings {
    const val assertCalledMessage = ""
    const val assertNotCalledMessage = ""
}