package csense.kotlin.patterns.lists

import csense.kotlin.extensions.collections.iterable.*
import csense.kotlin.general.*

public fun <T> List<T>.toLists(): Lists<T> {
    val firstElement = firstOr { return@toLists Lists.Empty }
    return Lists.Content(firstElement, this)
}


@Deprecated(
    message = "converting a know Lists to Lists is meaningless",
    replaceWith = ReplaceWith("this"),
    level = DeprecationLevel.ERROR
)
@Suppress("UnusedReceiverParameter")
public fun <T> Lists<T>.toLists(): Lists<T> = unexpected()