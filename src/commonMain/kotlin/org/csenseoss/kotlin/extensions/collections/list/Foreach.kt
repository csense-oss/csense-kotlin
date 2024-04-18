package org.csenseoss.kotlin.extensions.collections.list

import org.csenseoss.kotlin.*
import org.csenseoss.kotlin.extensions.general.*


/**
 * invokes the given action on each item that is of the expected type (U)
 * @receiver [List]<*>
 * @param indices [IntProgression] the indexes to go over.
 * @param action [FunctionUnit]<U, *>
 */
public inline fun <reified U> List<*>.forEachIsInstance(
    indices: IntProgression,
    action: FunctionUnit<U>
): Unit = indices.forEach { index ->
    getOrNull(index = index)?.invokeIsInstance(action = action)
}