package csense.kotlin.extensions.coroutines.channel

import csense.kotlin.*
import kotlinx.coroutines.channels.*


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