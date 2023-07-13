@file:Suppress("NOTHING_TO_INLINE")

package csense.kotlin.extensions.general

import csense.kotlin.general.*

public inline fun <T> T?.valueOr(or: () -> Nothing): T = when (this) {
    null -> or()
    else -> this
}

@Suppress("UnusedReceiverParameter", "MissingTestFunction", "UNUSED_PARAMETER")
@Deprecated(
    message = "Non-null value will always be non-null",
    replaceWith = ReplaceWith(expression = "this"),
    level = DeprecationLevel.ERROR
)
public inline fun <T : Any> T.valueOr(or: () -> Nothing): Nothing =
    unexpected("Non-null value will always be non-null")
