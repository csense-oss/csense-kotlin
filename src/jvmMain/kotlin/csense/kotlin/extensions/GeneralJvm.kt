package csense.kotlin.extensions

/**
 * Like typeK except it converts to java class types instead.
 *
 * see typeK
 * @return Class<T>
 */
inline fun <reified T : Any> type(): Class<T> = T::class.java

