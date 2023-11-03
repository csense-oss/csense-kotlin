@file:Suppress("NOTHING_TO_INLINE")

package csense.kotlin.extensions.primitives.string

import csense.kotlin.*
import csense.kotlin.annotations.numbers.*
import csense.kotlin.extensions.collections.generic.collection.*
import csense.kotlin.extensions.collections.generic.collection.operations.*


/**
 *
 * @receiver [String]
 * @param action [Function1]<[Char]>
 * @timecomplexity O(n)
 */
public inline fun String.forEachBackwards(
    action: FunctionUnit<Char>
): Unit =
    GenericCollections.forEachBackwards(count(), this::elementAt, action)

/**
 *
 * @receiver [String]
 * @param action [Function2]<[Int], [Char]>
 * @timecomplexity O(n)
 */
public inline fun String.forEachBackwardsIndexed(
    action: Function2Unit<@IntLimit(from = 0) Int, Char>
): Unit =
    GenericCollections.forEachBackwardsIndexed(count(), this::elementAt, action)
