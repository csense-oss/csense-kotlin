@file:Suppress("unused", "NOTHING_TO_INLINE", "INVISIBLE_MEMBER", "INVISIBLE_REFERENCE") // see https://youtrack.jetbrains.com/issue/KT-60866/Phase-out-usages-of-SuppressINVISIBLEREFERENCE-INVISIBLEMEMBER-in-libraries


package csense.kotlin.extensions.collections.list


/**
 * Tells if there are no element of the given
 *
 * @receiver [List]<T> the list to check
 * @param element T the element to search for
 * @return [Boolean] true if the element was not found, false if it was found.
 */
public inline fun <@kotlin.internal.OnlyInputTypes T> List<T>.doesNotContain(element: T): Boolean =
    !contains(element)
