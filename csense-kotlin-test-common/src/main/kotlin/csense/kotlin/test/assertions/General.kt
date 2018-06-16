package csense.kotlin.test.assertions

import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertNull
import kotlin.test.fail


fun failTest(message: String = "") {
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
        failTest("Expected an exception of type ${T::class.simpleName} but got no exceptions\r$message")
    } catch (exception: Exception) {
        if (exception is T) {
            //all is good / expected.
        } else {
            failTest("Expected an exception of type \"${T::class.simpleName}\" " +
                    "but got exception of type \"${exception::class.simpleName}\" instead." +
                    "\r$messageWrongException")
        }
    }
}
