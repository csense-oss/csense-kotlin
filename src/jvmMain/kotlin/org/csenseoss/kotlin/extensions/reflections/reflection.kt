@file:Suppress("unused")

package org.csenseoss.kotlin.extensions.reflections

import org.csenseoss.kotlin.extensions.general.*
import java.lang.reflect.*

/**
 * Gets a field as the expected type.
 * @receiver [Field]
 * @param obj [Any]
 * @return T?
 */
public inline fun <reified T> Field.getAs(obj: Any): T? {
    return tryOrNull { get(obj) as T? }
}