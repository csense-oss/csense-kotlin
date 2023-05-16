@file:Suppress("NOTHING_TO_INLINE")

package csense.kotlin.extensions.collections.iterable

/**
 * Returns [onEmpty] if this [isEmpty]
 * @receiver Iterable<T>
 * @param onEmpty Iterable<T>
 * @return Iterable<T>
 */
public inline fun <T> Iterable<T>.onEmpty(onEmpty: Iterable<T>): Iterable<T> = onEmpty { onEmpty }


public inline fun <T> Iterable<T>.onEmpty(vararg onEmpty: T): Iterable<T> = onEmpty {
    onEmpty.asIterable()
}

/**
 * Invokes [onEmptyAction] if this [isEmpty]
 * @receiver Iterable<T>
 * @param onEmptyAction Function0<Iterable<T>>
 * @return Iterable<T>
 */
public inline fun <T> Iterable<T>.onEmpty(onEmptyAction: () -> Iterable<T>): Iterable<T> = when {
    isEmpty() -> onEmptyAction()
    else -> this
}