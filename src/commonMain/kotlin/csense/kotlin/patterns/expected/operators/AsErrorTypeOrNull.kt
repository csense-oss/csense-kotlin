package csense.kotlin.patterns.expected.operators

import csense.kotlin.extensions.general.*
import csense.kotlin.patterns.expected.*


public inline fun <reified Error> Expected.Failed<Any?>.asErrorTypeOrNull(

): Expected.Failed<Error>? = when (error) {
    is Error -> this.cast()
    else -> null
}
