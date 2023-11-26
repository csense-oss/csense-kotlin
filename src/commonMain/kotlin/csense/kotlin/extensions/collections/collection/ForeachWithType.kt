@file:Suppress("unused", "NOTHING_TO_INLINE", "INVISIBLE_MEMBER")

package csense.kotlin.extensions.collections.collection

import csense.kotlin.*
import csense.kotlin.extensions.general.*


/**
 *
 * @receiver [Collection]<[Any]?>
 * @param function [Function1]<[U], [Unit]>
 */
public inline fun <reified U> Collection<Any?>.forEachWithType(
    function: Function0<U>
): Unit = forEach { it: Any? ->
    it?.cast<U>()?.let(function)
}