package csense.kotlin.specificExtensions.boolean

import kotlin.jvm.*

@JvmInline
public value class BooleanMappings(public val boolean: Boolean)

public inline val Boolean.mappings: BooleanMappings
    get() = BooleanMappings(this)
