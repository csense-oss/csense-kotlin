@file:Suppress("unused", "NOTHING_TO_INLINE")
package csense.kotlin.extensions


/**
 * Creates a simpler cast than regular due to the reified type T.
 * @receiver Any the value to cast
 * @return T? the potentical cast, if it is unable, null will be returned
 */
inline fun <reified T> Any.cast(): T? = this as? T