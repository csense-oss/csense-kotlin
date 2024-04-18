package org.csenseoss.kotlin.patterns.expected.operators

import org.csenseoss.kotlin.*
import org.csenseoss.kotlin.patterns.expected.*


public inline fun <Value, Error> Expected<Value, Error>.applyIfFailed(
    onFailed: ReceiverFunctionUnit<Expected.Failed<Error>>
): Expected<Value, Error> = apply {
    if (this.isFailed()) {
        onFailed(this)
    }
}