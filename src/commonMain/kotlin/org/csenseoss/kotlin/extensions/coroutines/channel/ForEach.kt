package org.csenseoss.kotlin.extensions.coroutines.channel

import kotlinx.coroutines.channels.*
import org.csenseoss.kotlin.*


/**
 * Iterates over the given [Channel], executing the given function each time
 * @receiver [Channel]<E>
 * @param function [FunctionUnit]<E>
 */
public suspend inline fun <E> Channel<E>.forEach(function: FunctionUnit<E>) {
    for (item: E in this) {
        function(item)
    }
}