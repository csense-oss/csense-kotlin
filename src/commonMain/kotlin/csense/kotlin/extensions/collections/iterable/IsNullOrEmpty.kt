@file:Suppress("NOTHING_TO_INLINE")

package csense.kotlin.extensions.collections.iterable

import csense.kotlin.extensions.nullabillity.*
import csense.kotlin.general.*
import kotlin.contracts.*


/**
 * Tells if this iteration is null or empty (size = 0)
 * @receiver [Iterable]<T>? the nullable iteration
 * @return [Boolean] true if the iteration is null or empty
 */

public inline fun <T, Collection : Iterable<T>> Collection?.isNullOrEmpty(): Boolean {
    contract {
        returns(false) implies (this@isNullOrEmpty != null)
    }
    return isNull() || isEmpty()
}


@Deprecated(
    message = "Receiver known at compile time to not be null, thus isNull will always fail. Use isEmpty instead",
    replaceWith = ReplaceWith("isEmpty()"),
    level = DeprecationLevel.ERROR
)
@Suppress("UnusedReceiverParameter")
public inline fun <Collection : Iterable<Any>> Collection.isNullOrEmpty(): Nothing = unexpected()

