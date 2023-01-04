package csense.kotlin.extensions.java.io.inputstream

import csense.kotlin.extensions.*
import csense.kotlin.extensions.mapping.*
import csense.kotlin.extensions.primitives.*
import csense.kotlin.extensions.primitives.int.*
import java.io.*

/**
 * Reads this [InputStream] to the end (until read() gives -1 back)
 * @throws [IOException] if any reading error occurs
 */
@Throws(IOException::class)
public fun InputStream.readToEnd() {
    @Suppress("ControlFlowWithEmptyBody")
    while (readOrNullOnEnd() != null) {
    }
}

/**
 * Performs a read on the this [InputStream]
 * @throws [IOException] if any reading error occurs
 * @return [Int]? null if the end has been reach, otherwise the next byte read
 */
@Throws(IOException::class)
public fun InputStream.readOrNullOnEnd(): Int? {
    val result = read()
    return result.isNegative.map(
        ifTrue = null,
        ifFalse = result
    )
}