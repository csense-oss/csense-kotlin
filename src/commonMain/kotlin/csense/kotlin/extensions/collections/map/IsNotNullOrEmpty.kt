@file:Suppress("NOTHING_TO_INLINE")
package csense.kotlin.extensions.collections.map

import csense.kotlin.general.*
import kotlin.contracts.*
import kotlin.jvm.*


/**
 * Tells if this nullable map is not null and have content
 * @receiver Map<Key, Value>
 * @return Boolean
 */
public inline fun <Key, Value> Map<Key, Value>?.isNotNullOrEmpty(): Boolean {
    contract {
        returns(true) implies (this@isNotNullOrEmpty != null)
    }
    @Suppress("VerboseNullabilityAndEmptiness")
    return this != null && this.isNotEmpty()
}


@Suppress("UnusedReceiverParameter")
@Deprecated(
    message = "Compile time known not null is never null.",
    level = DeprecationLevel.ERROR,
    replaceWith = ReplaceWith("isNotEmpty()")
)
@JvmName("isNotNullOrEmptyNotNull")
public inline fun <Key, Value> Map<Key, Value>.isNotNullOrEmpty(): Boolean = unexpected()
