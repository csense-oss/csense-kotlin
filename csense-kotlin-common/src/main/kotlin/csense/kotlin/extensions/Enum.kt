@file:Suppress("unused", "NOTHING_TO_INLINE")

package csense.kotlin.extensions


/**
 *
 * @param name String?
 * @return T?
 */
inline fun <reified T : kotlin.Enum<T>> enumFromOrNull(name: String?): T? {
    return enumFromOr<T> { it.name == name }
}

/**
 *
 * @param findAction Function1<T, Boolean>
 * @param ifNotFound T?
 * @return T?
 */
inline fun <reified T : kotlin.Enum<T>> enumFromOr(ifNotFound: T? = null,
                                                   findAction: Function1<T, Boolean>): T? {
    return enumValues<T>().find(findAction) ?: ifNotFound
}

/**
 *
 * @param name String?
 * @param orElse T
 * @return T
 */
inline fun <reified T : kotlin.Enum<T>> enumFromOr(name: String?, orElse: T): T {
    return enumFromOrNull<T>(name) ?: orElse
}


/**
 *
 * @param value Int
 * @return T?
 */
inline fun <reified T : Enum<T>> enumFromOrNull(value: Int): T? {
    return enumFromOr<T> { it.ordinal == value }
}


/**
 *
 * @param value Int
 * @param orElse T
 * @return T
 */
inline fun <reified T : Enum<T>> enumFromOr(value: Int, orElse: T): T {
    return enumFromOrNull<T>(value) ?: orElse
}
