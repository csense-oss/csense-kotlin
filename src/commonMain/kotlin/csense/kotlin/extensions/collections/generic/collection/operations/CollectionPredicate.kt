@file:Suppress("UnusedReceiverParameter")

package csense.kotlin.extensions.collections.generic.collection.operations

import csense.kotlin.extensions.collections.*
import csense.kotlin.extensions.collections.generic.collection.*
import csense.kotlin.extensions.mapping.*
import csense.kotlin.general.*

public sealed interface SatisfyPredicateResult {

    public fun combineWith(
        otherPredicateResult: Boolean
    ): SatisfyPredicateResult

    public data object Empty : SatisfyPredicateResult {
        override fun combineWith(
            otherPredicateResult: Boolean
        ): SatisfyPredicateResult = unexpected("hmm!")
    }

    public data object None : SatisfyPredicateResult {
        override fun combineWith(
            otherPredicateResult: Boolean
        ): SatisfyPredicateResult = when (otherPredicateResult) {
            true -> Some
            false -> None
        }
    }

    public data object Some : SatisfyPredicateResult {
        override fun combineWith(
            otherPredicateResult: Boolean
        ): Some = Some
    }

    public data object All : SatisfyPredicateResult {
        override fun combineWith(
            otherPredicateResult: Boolean
        ): SatisfyPredicateResult = when (otherPredicateResult) {
            true -> All
            false -> Some
        }
    }

    public companion object {
        public fun startingWith(predicateResult: Boolean): SatisfyPredicateResult =
            predicateResult.map(ifTrue = All, ifFalse = None)
    }
}


public inline fun <T> GenericCollections.satisfyPredicate(
    predicate: Predicate<T>,
    length: Int,
    getElement: (Int) -> T
): SatisfyPredicateResult = GenericCollections.combineEachStartingWith(
    onFirstItem = { it: T ->
        SatisfyPredicateResult.startingWith(predicateResult = predicate(it))
    },
    onEach = { it: T, result: SatisfyPredicateResult ->
        val newResult: SatisfyPredicateResult = result.combineWith(
            otherPredicateResult = predicate(it)
        )
        if (newResult == SatisfyPredicateResult.Some) {
            return@combineEachStartingWith SatisfyPredicateResult.Some
        }
        newResult
    },
    length = length,
    getElement = getElement,
    //    any == false on empty
//    all == true on empty
//    none == true on empty
    onEmpty = { SatisfyPredicateResult.Empty }
)

//TODO move?!
public inline fun <T, R> GenericCollections.combineEachStartingWith(
    onFirstItem: (T) -> R,
    onEach: (T, R) -> R,
    length: Int,
    getElement: (Int) -> T,
    onEmpty: () -> R
): R {
    if (length <= 0) {
        return onEmpty()
    }
    val first: T = getElement(0)
    var result: R = onFirstItem(first)
    for (i: Int in 1 until length) {
        val element: T = getElement(i)
        result = onEach(element, result)
    }
    return result
}

