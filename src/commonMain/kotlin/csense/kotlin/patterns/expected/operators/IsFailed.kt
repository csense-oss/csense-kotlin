package csense.kotlin.patterns.expected.operators

import csense.kotlin.*
import csense.kotlin.patterns.expected.*
import kotlin.contracts.*


public fun <Value, Error> Expected<Value, Error>.isFailed(): Boolean {
    contract {
        returns(false) implies (this@isFailed is Expected.Success<Value>)
        returns(true) implies (this@isFailed is Expected.Failed<Error>)
    }
    return this is Expected.Failed<Error>
}


@Deprecated(
    level = DeprecationLevel.ERROR, message = "Success is never a failed",
    replaceWith = ReplaceWith("false")
)
@Suppress("UnusedReceiverParameter")
public fun Expected.Success<*>.isFailed(): Boolean = unexpected()

@Deprecated(
    level = DeprecationLevel.ERROR, message = "Failed is always a failed",
    replaceWith = ReplaceWith("true")
)
@Suppress("UnusedReceiverParameter")
public fun Expected.Failed<*>.isFailed(): Boolean = unexpected()
