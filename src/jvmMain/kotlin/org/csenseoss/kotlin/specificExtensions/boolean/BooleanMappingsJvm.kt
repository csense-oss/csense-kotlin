package org.csenseoss.kotlin.specificExtensions.boolean

import org.csenseoss.kotlin.extensions.mapping.*


public fun BooleanMappings.toNewlineOrEmpty(): String = boolean.map(
    ifTrue = System.lineSeparator(),
    ifFalse = ""
)