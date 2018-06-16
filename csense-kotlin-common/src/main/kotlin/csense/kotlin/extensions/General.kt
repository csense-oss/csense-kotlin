package csense.kotlin.extensions


/**
 * Creates a simpler cast than regular due to the reified type T.
 */
inline fun <reified T> Any.cast(): T? = this as? T