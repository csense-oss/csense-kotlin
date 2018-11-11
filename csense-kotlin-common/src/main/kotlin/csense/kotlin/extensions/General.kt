@file:Suppress("unused", "NOTHING_TO_INLINE")

package csense.kotlin.extensions

import kotlin.reflect.*


/**
 * Creates a simpler cast than regular due to the reified type T.
 * @receiver Any the value to cast
 * @return T? the potentical cast, if it is unable, null will be returned
 */
inline fun <reified T> Any.cast(): T? = this as? T

/**
 * simple convinience for getting a kclass for a given type, witout having to write "MyClass::class" which could be very long names,
 * and in case of generic code, it allows type changes without changing "MyClass" to "SomeOtherClass" later :)
 *
 * @return KClass<T>
 */
inline fun <reified T : Any> typeK(): KClass<T> = T::class
