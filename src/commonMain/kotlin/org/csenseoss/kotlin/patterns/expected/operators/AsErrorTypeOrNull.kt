package org.csenseoss.kotlin.patterns.expected.operators

import org.csenseoss.kotlin.extensions.general.*
import org.csenseoss.kotlin.patterns.expected.*


public inline fun <reified Error> Expected.Failed<Any?>.asErrorTypeOrNull(

): Expected.Failed<Error>? = when (error) {
    is Error -> this.cast()
    else -> null
}