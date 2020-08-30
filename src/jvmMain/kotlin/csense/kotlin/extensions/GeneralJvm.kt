package csense.kotlin.extensions

/**
 * Creates a java class based on the expected type (by type inference)
 *
 * so you do not have to spell out a particular class name if it is already known
 * @return [Class]<T>
 */
public inline fun <reified T : Any> type(): Class<T> = T::class.java

