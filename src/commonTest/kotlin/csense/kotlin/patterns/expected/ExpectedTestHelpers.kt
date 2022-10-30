package csense.kotlin.patterns.expected

import csense.kotlin.tests.assertions.*
import kotlin.contracts.*


fun <Value> Expected.Success<Value>.asExpected(): Expected<Value, *> {
    return this
}

fun <Error> Expected.Failed<Error>.asExpected(): Expected<*, Error> {
    return this
}

fun <Value, Error> Expected.Success<Value>.asExpectedValue(): Expected<Value, Error> {
    return this
}

fun <Value, Error> Expected.Failed<Error>.asExpectedValue(): Expected<Value, Error> {
    return this
}


fun <Value, Error> Expected<Value, Error>.assertFailed() {
    contract {
        returns() implies (this@assertFailed is Expected.Failed<Error>)
    }
    this.assertIs<Expected.Failed<Error>>()
}

fun <Value, Error : Comparable<Error>> Expected<Value, Error>.assertFailedWith(error: Error) {
    contract {
        returns() implies (this@assertFailedWith is Expected.Failed<Error>)
    }
    assertFailed()
    this.error.assert(error)
}

fun <Value, Error> Expected<Value, Error>.assertFailedWithByEquals(error: Error) {
    contract {
        returns() implies (this@assertFailedWithByEquals is Expected.Failed<Error>)
    }
    assertFailed()
    this.error.assertByEquals(error)
}

fun <Value, Error> Expected<Value, Error>.assertSuccess() {
    contract {
        returns() implies (this@assertSuccess is Expected.Success<Value>)
    }
    this.assertIs<Expected.Success<Value>>()
}

fun <Value : Comparable<Value>, Error> Expected<Value, Error>.assertSuccessWith(value: Value) {
    contract {
        returns() implies (this@assertSuccessWith is Expected.Success<Value>)
    }
    assertSuccess()
    this.value.assert(value)
}

fun <Value, Error> Expected<Value, Error>.assertSuccessWithByEquals(value: Value) {
    contract {
        returns() implies (this@assertSuccessWithByEquals is Expected.Success<Value>)
    }
    assertSuccess()
    this.value.assertByEquals(value)
}
