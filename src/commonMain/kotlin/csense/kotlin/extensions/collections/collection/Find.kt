@file:Suppress("unused", "NOTHING_TO_INLINE", "INVISIBLE_MEMBER")

package csense.kotlin.extensions.collections.collection

import csense.kotlin.*


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
        if (findAction(it)) {
            return@findWithType it
        }
    }
    return null
}
