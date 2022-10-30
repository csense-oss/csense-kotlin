package csense.kotlin.patterns.expected.operators

import csense.kotlin.*
import csense.kotlin.patterns.expected.*

public inline fun <Value, Error> Expected<Value, Error>.applyIfSuccess(
    onSuccess: ReceiverFunctionUnit<Expected.Success<Value>>
): Expected<Value, Error> = apply {
    if (this.isSuccess()) {
        onSuccess()
    }
}