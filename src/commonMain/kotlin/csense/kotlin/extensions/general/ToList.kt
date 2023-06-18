package csense.kotlin.extensions.general

import csense.kotlin.general.*


public fun <T> T?.toListOrEmpty(): List<T> {
    return listOfNotNull(this)
}

@Deprecated(
    message = "Known compile-time notNull value will always return a list with a single element",
    level = DeprecationLevel.ERROR,
    replaceWith = ReplaceWith("toList()")
)
@Suppress("UnusedReceiverParameter")
public fun <T : Any> T.toListOrEmpty(): Nothing = unexpected()

public fun <T : Any> T.toList(): List<T> {
    return listOf(this)
}