@file:Suppress("unused", "NOTHING_TO_INLINE", "INVISIBLE_MEMBER", "INVISIBLE_REFERENCE") // see https://youtrack.jetbrains.com/issue/KT-60866/Phase-out-usages-of-SuppressINVISIBLEREFERENCE-INVISIBLEMEMBER-in-libraries


package csense.kotlin.extensions.collections.set.mutable


/**
 * like [toggleExistence], except you control the action by the "[shouldExists]";
 * if that is true, then the element is added, if false the element is removed.
 * @receiver [MutableSet]<T>
 * @param item T
 * @param shouldExists [Boolean]
 * @timecomplexity O(1)
 */
public inline fun <@kotlin.internal.OnlyInputTypes T> MutableSet<T>.setExistence(item: T, shouldExists: Boolean) {
    when (shouldExists) {
        true -> add(item)
        false -> remove(item)
    }
}