@file:Suppress("NOTHING_TO_INLINE")

package csense.kotlin.extensions.`class`


@Suppress("UNCHECKED_CAST")
public inline fun <T> Class<T>.tryCast(other: Any): T? = when {
    other::class.java.isAssignableFrom(this) -> other as T
    else -> null
}