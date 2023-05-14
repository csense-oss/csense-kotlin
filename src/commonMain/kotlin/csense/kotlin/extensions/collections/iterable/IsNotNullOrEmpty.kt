@file:Suppress("NOTHING_TO_INLINE")

package csense.kotlin.extensions.collections.iterable

import csense.kotlin.general.*
import kotlin.contracts.*


/**
 * Tells if this iterable has content (meaning it is not null nor is it empty)
 * @receiver [Collection]? the optional type
 * @return [Boolean] true if this has content (is not null or empty) false if it is null or empty
 */

public inline fun <T, Collection : Iterable<T>> Collection?.isNotNullOrEmpty(): Boolean {
    contract {
        returns(true) implies (this@isNotNullOrEmpty != null)
    }
    return this != null && this.isNotEmpty()
}


@Deprecated(
    "Receiver known at compile time to not be null, thus isNotNull will always be true. Use isNotEmpty instead",
    level = DeprecationLevel.ERROR
)
@Suppress("UnusedReceiverParameter")
public inline fun <Collection : Iterable<Any>> Collection.isNotNullOrEmpty(): Nothing = unexpected()
