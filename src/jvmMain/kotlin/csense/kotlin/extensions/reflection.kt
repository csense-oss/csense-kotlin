@file:Suppress("unused")

package csense.kotlin.extensions

import java.lang.reflect.Field

/**
 * Gets a field as the expected type.
 */
inline fun <reified T> Field.getAs(obj: Any): T? {
    return get(obj) as T?
}