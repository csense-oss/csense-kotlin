@file:Suppress("unused", "NOTHING_TO_INLINE", "INVISIBLE_MEMBER", "INVISIBLE_REFERENCE")
package csense.kotlin.extensions.collections.collection

import csense.kotlin.extensions.primitives.boolean.*


/**
 *
 * @receiver [Collection]<[Any]?>
 * @param findAction [Function1]<[U], [Boolean]>
 * @return [U]?
 */
public inline fun <reified U> Collection<Any?>.findWithType(
    findAction: Function1<U, Boolean>
): U? {
    forEachWithType<U> {
        findAction(it).ifTrue {
            return@findWithType it
        }
    }
    return null
}