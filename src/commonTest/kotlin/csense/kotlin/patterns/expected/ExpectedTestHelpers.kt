package csense.kotlin.patterns.expected


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