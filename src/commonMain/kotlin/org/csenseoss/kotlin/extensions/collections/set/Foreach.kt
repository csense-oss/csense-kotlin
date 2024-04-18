package org.csenseoss.kotlin.extensions.collections.set

import org.csenseoss.kotlin.*
import org.csenseoss.kotlin.extensions.collections.generic.collection.*
import org.csenseoss.kotlin.extensions.collections.generic.collection.operations.*


/**
 * Performs backwards traversal on this
 * @receiver [List]<T>
 * @param action [FunctionUnit]<T>
 * @timecomplexity O(N)
 */
public inline fun <T> Set<T>.foreachBackwards(action: FunctionUnit<T>): Unit =
    GenericCollections.forEachBackwards(size, this::elementAt, action)