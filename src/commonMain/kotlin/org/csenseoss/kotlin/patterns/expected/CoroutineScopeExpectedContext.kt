@file:Suppress("NOTHING_TO_INLINE")

package org.csenseoss.kotlin.patterns.expected

import kotlinx.coroutines.*
import kotlin.coroutines.*


public class CoroutineScopeExpectedContext internal constructor(
    private val scope: CoroutineScope
) : CoroutineScope, Expected.Companion.ExpectedContext {
    override val coroutineContext: CoroutineContext
        get() = scope.coroutineContext

    public companion object
}

internal inline fun CoroutineScope.toExpectedContext(): CoroutineScopeExpectedContext {
    return CoroutineScopeExpectedContext(this)
}