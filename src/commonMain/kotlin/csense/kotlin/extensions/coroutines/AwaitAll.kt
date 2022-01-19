package csense.kotlin.extensions.coroutines

import csense.kotlin.annotations.*
import kotlinx.coroutines.*

@ExperimentalCsenseApi
@Suppress("MissingTestFunction") //plugin bug
public suspend fun <T1, T2> awaitAll(
    first: Deferred<T1>,
    second: Deferred<T2>
): Pair<T1, T2> {
    return Pair(
        first = first.await(),
        second = second.await()
    )
}

@ExperimentalCsenseApi
@Suppress("MissingTestFunction") //plugin bug
public suspend fun <T1, T2, T3> awaitAll(
    first: Deferred<T1>,
    second: Deferred<T2>,
    third: Deferred<T3>
): Triple<T1, T2, T3> {
    return Triple(
        first = first.await(),
        second = second.await(),
        third = third.await()
    )
}