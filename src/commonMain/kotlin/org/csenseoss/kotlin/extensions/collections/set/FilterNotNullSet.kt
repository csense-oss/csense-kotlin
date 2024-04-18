package org.csenseoss.kotlin.extensions.collections.set

import org.csenseoss.kotlin.extensions.collections.collection.*


public fun <T> Set<T?>.filterNotNullSet(): Set<T> {
    val result: MutableSet<T> = mutableSetOf()
    forEachNotNull { it: T ->
        result.add(it)
    }
    return result
}