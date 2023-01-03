package csense.kotlin.specificExtensions.boolean

import csense.kotlin.extensions.mapping.*


public fun BooleanMappings.toNewlineOrEmpty(): String = boolean.map(
    ifTrue = System.lineSeparator(),
    ifFalse = ""
)
