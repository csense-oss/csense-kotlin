@file:Suppress("unused")

package csense.kotlin.extensions

import java.lang.reflect.Field

/**
 * Gets a field as the expected type.
 */
inline fun <reified T> Field.getAs(obj: Any): T? {
    return try {
        get(obj) as T?
    } catch (_: Exception) {
        null
    }
}