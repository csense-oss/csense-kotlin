package org.csenseoss.kotlin.extensions.general

import kotlin.reflect.*

public val KClass<*>.simpleNameOrUnknown: String
    get() = simpleName ?: "<unknown class type>"