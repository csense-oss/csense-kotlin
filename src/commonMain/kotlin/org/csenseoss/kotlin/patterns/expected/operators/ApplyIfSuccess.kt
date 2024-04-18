package org.csenseoss.kotlin.patterns.expected.operators

import org.csenseoss.kotlin.*
import org.csenseoss.kotlin.patterns.expected.*

public inline fun <Value, Error> Expected<Value, Error>.applyIfSuccess(
    onSuccess: ReceiverFunctionUnit<Expected.Success<Value>>
): Expected<Value, Error> = apply {
    if (this.isSuccess()) {
        onSuccess()
    }
}