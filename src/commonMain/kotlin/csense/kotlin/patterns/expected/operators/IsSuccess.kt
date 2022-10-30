package csense.kotlin.patterns.expected.operators

import csense.kotlin.*
import csense.kotlin.patterns.expected.*
import kotlin.contracts.*


public fun <Value, Error> Expected<Value, Error>.isSuccess(): Boolean {
    contract {
        returns(true) implies (this@isSuccess is Expected.Success<Value>)
        returns(false) implies (this@isSuccess is Expected.Failed<Error>)
    }
    return this is Expected.Success<Value>
}

@Deprecated(
    level = DeprecationLevel.ERROR, message = "Success is success",
    replaceWith = ReplaceWith("true")
)
@Suppress("UnusedReceiverParameter")
public fun Expected.Success<*>.isSuccess(): Boolean = unexpected()

@Deprecated(
    level = DeprecationLevel.ERROR, message = "Failed is never a success",
    replaceWith = ReplaceWith("false")
)
@Suppress("UnusedReceiverParameter")
public fun Expected.Failed<*>.isSuccess(): Boolean = unexpected()
