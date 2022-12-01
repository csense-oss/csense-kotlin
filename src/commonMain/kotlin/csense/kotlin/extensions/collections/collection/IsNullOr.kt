@file:Suppress("NOTHING_TO_INLINE")

package csense.kotlin.extensions.collections.collection

import csense.kotlin.general.*
import kotlin.contracts.*


/**
 * Tells if this collection is NOT null And NOT empty (size > 0)
 * @receiver [Collection]<T>? the nullable collection
 * @return [Boolean] true if the collection is NOT null AND NOT empty
 */
public inline fun <T> Collection<T>?.isNotNullOrEmpty(): Boolean {
    contract {
        returns(true) implies (this@isNotNullOrEmpty != null)
    }
    return !this.isNullOrEmpty()
}


@Deprecated(
    "Receiver known at compile time to not be null, thus isNotNull will always be true. Use isNotEmpty instead",
    level = DeprecationLevel.ERROR
)
public inline fun Collection<Any>.isNotNullOrEmpty(): Nothing = unexpected()


@Deprecated(
    "Receiver known at compile time to not be null, thus isNull will always fail. Use isEmpty instead",
    level = DeprecationLevel.ERROR
)
public inline fun Collection<Any>.isNullOrEmpty(): Nothing = unexpected()

