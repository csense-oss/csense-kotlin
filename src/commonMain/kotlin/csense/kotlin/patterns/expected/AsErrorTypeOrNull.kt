package csense.kotlin.patterns.expected


public inline fun <reified Error> Expected.Failed<Any?>.asErrorTypeOrNull(): Expected.Failed<Error>? {
    return if (error is Error) {
        @Suppress("UNCHECKED_CAST")
        return this as Expected.Failed<Error>
    } else {
        null
    }
}
