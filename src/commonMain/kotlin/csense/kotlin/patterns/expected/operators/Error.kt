package csense.kotlin.patterns.expected.operators

import csense.kotlin.patterns.expected.*

//Nothing as value type means it can never happen.
public inline val <Error> Expected<Nothing, Error>.error: Error
    get() = asFailed.error