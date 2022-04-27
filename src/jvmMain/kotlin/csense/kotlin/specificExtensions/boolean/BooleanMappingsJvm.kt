package csense.kotlin.specificExtensions.boolean

import csense.kotlin.extensions.*


public fun BooleanMappings.toNewlineOrEmpty(): String {
    return boolean.map(
        ifTrue = System.lineSeparator(),
        ifFalse = ""
    )
}
