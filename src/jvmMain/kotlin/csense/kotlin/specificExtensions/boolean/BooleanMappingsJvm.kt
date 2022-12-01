package csense.kotlin.specificExtensions.boolean

import csense.kotlin.extensions.mapping.*


public fun BooleanMappings.toNewlineOrEmpty(): String {
    return boolean.map(
        ifTrue = System.lineSeparator(),
        ifFalse = ""
    )
}
