package org.csenseoss.kotlin.patterns.expected.operators

import kotlinx.coroutines.*
import org.csenseoss.kotlin.extensions.coroutines.coroutineScope.*
import org.csenseoss.kotlin.patterns.expected.*
import kotlin.coroutines.*


public fun <Value, Error> CoroutineScope.asyncExpected(
    context: CoroutineContext,
    action: suspend CoroutineScopeExpectedContext.() -> Expected<Value, Error>
): Deferred<Expected<Value, Error>> = async(context) {
    action(toExpectedContext())
}

public fun <Value, Error> CoroutineScope.asyncExpected(
    action: suspend CoroutineScopeExpectedContext.() -> Expected<Value, Error>
): Deferred<Expected<Value, Error>> = async {
    action(toExpectedContext())
}

public fun <Value, Error> CoroutineScope.asyncDefaultExpected(
    action: suspend CoroutineScopeExpectedContext.() -> Expected<Value, Error>
): Deferred<Expected<Value, Error>> = asyncDefault {
    action(toExpectedContext())
}

public fun <Value, Error> CoroutineScope.asyncMainExpected(
    action: suspend CoroutineScopeExpectedContext.() -> Expected<Value, Error>
): Deferred<Expected<Value, Error>> = asyncMain {
    action(toExpectedContext())
}