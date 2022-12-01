@file:Suppress("NOTHING_TO_INLINE")

package csense.kotlin.extensions.collections.iterable

import csense.kotlin.extensions.*
import csense.kotlin.extensions.mapping.*
import kotlin.contracts.*

/**
 * If this [Collection] is [isEmpty] or null then it will return null, otherwise the receiver (which is [isNotEmpty])
 * @receiver [Collection]?
 * @return [Collection]?
 */

public inline fun <T, Collection : Iterable<T>> Collection?.nullOnEmpty(): Collection? {
    contract {
        returnsNotNull() implies (this@nullOnEmpty != null)
    }
    return isNotNullOrEmpty().map(ifTrue = this, ifFalse = null)
}