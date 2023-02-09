@file:Suppress("unused")

package csense.kotlin.extensions.reflections

import csense.kotlin.extensions.general.*
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
