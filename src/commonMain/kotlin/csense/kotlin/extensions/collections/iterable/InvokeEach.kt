@file:Suppress("unused", "NOTHING_TO_INLINE", "INVISIBLE_MEMBER", "INVISIBLE_REFERENCE", "MemberVisibilityCanBePrivate")

package csense.kotlin.extensions.collections.iterable

import csense.kotlin.*
import kotlin.Function1
import kotlin.Function2
import kotlin.Function3
import kotlin.Function4
import kotlin.Function5
import kotlin.Function6


//region Invoke each with

/**
 * Invokes each callable argument and discards the result
 * @receiver [Iterable]<[Function0R]<O>>
 */
public inline fun <O> Iterable<Function0R<O>>.invokeEach(): Unit =
    forEach { it() }

/**
 * Invokes each function with the given arguments
 * @receiver [Iterable]<[Function1]<I1, O>>
 * @param element I1
 */

public inline fun <@kotlin.internal.OnlyInputTypes I1, O> Iterable<Function1<I1, O>>.invokeEachWith(
    element: I1
): Unit =
    forEach { it(element) }

/**
 * Invokes each function with the given arguments
 * @receiver [Iterable]<[Function2]<I1, I2, O>>
 * @param firstElement I1
 * @param secondElement I2
 */


public inline fun <
        @kotlin.internal.OnlyInputTypes I1,
        @kotlin.internal.OnlyInputTypes I2,
        O> Iterable<Function2<I1, I2, O>>.invokeEachWith(
    firstElement: I1,
    secondElement: I2
): Unit =
    forEach { it(firstElement, secondElement) }

/**
 * Invokes each function with the given arguments
 * @receiver [Iterable]<[Function3]<I1, I2, I3, O>>
 * @param firstElement I1
 * @param secondElement I2
 * @param thirdElement I3
 */

public inline fun <
        @kotlin.internal.OnlyInputTypes I1,
        @kotlin.internal.OnlyInputTypes I2,
        @kotlin.internal.OnlyInputTypes I3,
        O>
        Iterable<Function3<I1, I2, I3, O>>.invokeEachWith(
    firstElement: I1,
    secondElement: I2,
    thirdElement: I3
): Unit =
    forEach {
        it(
            firstElement,
            secondElement,
            thirdElement
        )
    }

/**
 *  Invokes each function with the given arguments
 * @receiver [Iterable]<[Function4]<I1, I2, I3, I4, O>>
 * @param firstElement I1
 * @param secondElement I2
 * @param thirdElement I3
 * @param fourthElement I4
 */

public inline fun <
        @kotlin.internal.OnlyInputTypes I1,
        @kotlin.internal.OnlyInputTypes I2,
        @kotlin.internal.OnlyInputTypes I3,
        @kotlin.internal.OnlyInputTypes I4,
        O>
        Iterable<Function4<I1, I2, I3, I4, O>>.invokeEachWith(
    firstElement: I1,
    secondElement: I2,
    thirdElement: I3,
    fourthElement: I4
): Unit =
    forEach {
        it(
            firstElement,
            secondElement,
            thirdElement,
            fourthElement
        )
    }

/**
 * Invokes each function with the given arguments
 * @receiver [Iterable]<[Function5]<I1, I2, I3, I4, I5, O>>
 * @param firstElement I1
 * @param secondElement I2
 * @param thirdElement I3
 * @param fourthElement I4
 * @param fifthElement I5
 */

public inline fun <
        @kotlin.internal.OnlyInputTypes I1,
        @kotlin.internal.OnlyInputTypes I2,
        @kotlin.internal.OnlyInputTypes I3,
        @kotlin.internal.OnlyInputTypes I4,
        @kotlin.internal.OnlyInputTypes I5,
        O>
        Iterable<Function5<I1, I2, I3, I4, I5, O>>.invokeEachWith(
    firstElement: I1,
    secondElement: I2,
    thirdElement: I3,
    fourthElement: I4,
    fifthElement: I5
): Unit =
    forEach {
        it(
            firstElement,
            secondElement,
            thirdElement,
            fourthElement,
            fifthElement
        )
    }

/**
 * Invokes each function with the given arguments
 * @receiver [Iterable]<[Function6]<I1, I2, I3, I4, I5, I6, O>>
 * @param firstElement I1
 * @param secondElement I2
 * @param thirdElement I3
 * @param fourthElement I4
 * @param fifthElement I5
 * @param sixthElement I6
 */

public inline fun <
        @kotlin.internal.OnlyInputTypes I1,
        @kotlin.internal.OnlyInputTypes I2,
        @kotlin.internal.OnlyInputTypes I3,
        @kotlin.internal.OnlyInputTypes I4,
        @kotlin.internal.OnlyInputTypes I5,
        @kotlin.internal.OnlyInputTypes I6,
        O>
        Iterable<Function6<I1, I2, I3, I4, I5, I6, O>>.invokeEachWith(
    firstElement: I1,
    secondElement: I2,
    thirdElement: I3,
    fourthElement: I4,
    fifthElement: I5,
    sixthElement: I6
): Unit =
    forEach {
        it(
            firstElement,
            secondElement,
            thirdElement,
            fourthElement,
            fifthElement,
            sixthElement
        )
    }
//endregion
