@file:Suppress("unused", "NOTHING_TO_INLINE", "INVISIBLE_MEMBER")

package org.csenseoss.kotlin.extensions.collections.collection

import org.csenseoss.kotlin.*
import org.csenseoss.kotlin.extensions.general.*


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