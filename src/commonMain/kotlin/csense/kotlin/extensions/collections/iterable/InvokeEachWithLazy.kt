package csense.kotlin.extensions.collections.iterable

import csense.kotlin.*
import kotlin.Function1
import kotlin.Function2
import kotlin.Function3
import kotlin.Function4
import kotlin.Function5
import kotlin.Function6
import kotlin.contracts.*


/**
 * Invokes each function with the given argument(s) computed lazy
 * @receiver [Iterable]<[Function1]<I1, O>>
 * @param element [Function0R]<I1>
 */

public inline fun <I1, O> Iterable<Function1<I1, O>>.invokeEachWithLazy(
    element: Function0R<I1>
) {
    contract {
        callsInPlace(element, InvocationKind.AT_MOST_ONCE)
    }
    skipIfEmptyOr {
        invokeEachWith(element())
    }
}

/**
 * Invokes each function with the given arguments
 * @receiver [Iterable]<[Function2]<I1, I2, O>>
 * @param firstElement I1
 * @param secondElement I2
 */


public inline fun <I1, I2, O> Iterable<Function2<I1, I2, O>>.invokeEachWithLazy(
    firstElement: Function0R<I1>,
    secondElement: Function0R<I2>
) {
    contract {
        callsInPlace(firstElement, InvocationKind.AT_MOST_ONCE)
        callsInPlace(secondElement, InvocationKind.AT_MOST_ONCE)
    }
    skipIfEmptyOr {
        invokeEachWith(firstElement(), secondElement())
    }
}


/**
 * Invokes each function with the given arguments
 * @receiver [Iterable]<[Function3]<I1, I2, I3, O>>
 * @param firstElement I1
 * @param secondElement I2
 * @param thirdElement I3
 */

public inline fun <I1, I2, I3, O>
        Iterable<Function3<I1, I2, I3, O>>.invokeEachWithLazy(
    firstElement: Function0R<I1>,
    secondElement: Function0R<I2>,
    thirdElement: Function0R<I3>
) {
    contract {
        callsInPlace(firstElement, InvocationKind.AT_MOST_ONCE)
        callsInPlace(secondElement, InvocationKind.AT_MOST_ONCE)
        callsInPlace(thirdElement, InvocationKind.AT_MOST_ONCE)
    }
    skipIfEmptyOr {
        invokeEachWith(firstElement(), secondElement(), thirdElement())
    }
}

/**
 *  Invokes each function with the given arguments
 * @receiver [Iterable]<[Function4]<I1, I2, I3, I4, O>>
 * @param firstElement I1
 * @param secondElement I2
 * @param thirdElement I3
 * @param fourthElement I4
 */

public inline fun <I1, I2, I3, I4, O>
        Iterable<Function4<I1, I2, I3, I4, O>>.invokeEachWithLazy(
    firstElement: Function0R<I1>,
    secondElement: Function0R<I2>,
    thirdElement: Function0R<I3>,
    fourthElement: Function0R<I4>
) {
    contract {
        callsInPlace(firstElement, InvocationKind.AT_MOST_ONCE)
        callsInPlace(secondElement, InvocationKind.AT_MOST_ONCE)
        callsInPlace(thirdElement, InvocationKind.AT_MOST_ONCE)
        callsInPlace(fourthElement, InvocationKind.AT_MOST_ONCE)
    }
    skipIfEmptyOr {
        invokeEachWith(
            firstElement(),
            secondElement(),
            thirdElement(),
            fourthElement()
        )
    }
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

public inline fun <I1, I2, I3, I4, I5, O>
        Iterable<Function5<I1, I2, I3, I4, I5, O>>.invokeEachWithLazy(
    firstElement: Function0R<I1>,
    secondElement: Function0R<I2>,
    thirdElement: Function0R<I3>,
    fourthElement: Function0R<I4>,
    fifthElement: Function0R<I5>
) {
    contract {
        callsInPlace(firstElement, InvocationKind.AT_MOST_ONCE)
        callsInPlace(secondElement, InvocationKind.AT_MOST_ONCE)
        callsInPlace(thirdElement, InvocationKind.AT_MOST_ONCE)
        callsInPlace(fourthElement, InvocationKind.AT_MOST_ONCE)
        callsInPlace(fifthElement, InvocationKind.AT_MOST_ONCE)
    }
    skipIfEmptyOr {
        invokeEachWith(
            firstElement(),
            secondElement(),
            thirdElement(),
            fourthElement(),
            fifthElement()
        )
    }
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

public inline fun <I1, I2, I3, I4, I5, I6, O>
        Iterable<Function6<I1, I2, I3, I4, I5, I6, O>>.invokeEachWithLazy(
    firstElement: Function0R<I1>,
    secondElement: Function0R<I2>,
    thirdElement: Function0R<I3>,
    fourthElement: Function0R<I4>,
    fifthElement: Function0R<I5>,
    sixthElement: Function0R<I6>
) {
    contract {
        callsInPlace(firstElement, InvocationKind.AT_MOST_ONCE)
        callsInPlace(secondElement, InvocationKind.AT_MOST_ONCE)
        callsInPlace(thirdElement, InvocationKind.AT_MOST_ONCE)
        callsInPlace(fourthElement, InvocationKind.AT_MOST_ONCE)
        callsInPlace(fifthElement, InvocationKind.AT_MOST_ONCE)
        callsInPlace(sixthElement, InvocationKind.AT_MOST_ONCE)
    }
    skipIfEmptyOr {
        invokeEachWith(
            firstElement(),
            secondElement(),
            thirdElement(),
            fourthElement(),
            fifthElement(),
            sixthElement()
        )
    }
}
