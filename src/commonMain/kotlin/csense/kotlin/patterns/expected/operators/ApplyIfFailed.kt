package csense.kotlin.patterns.expected.operators

import csense.kotlin.*
import csense.kotlin.patterns.expected.*


public inline fun <Value, Error> Expected<Value, Error>.applyIfFailed(
    onFailed: ReceiverFunctionUnit<Expected.Failed<Error>>
): Expected<Value, Error> = apply {
    if (this.isFailed()) {
        onFailed(this)
    }
}

