@file:Suppress("unused", "NOTHING_TO_INLINE", "INVISIBLE_MEMBER", "INVISIBLE_REFERENCE") // see https://youtrack.jetbrains.com/issue/KT-60866/Phase-out-usages-of-SuppressINVISIBLEREFERENCE-INVISIBLEMEMBER-in-libraries



package csense.kotlin.extensions.collections.set.mutable

/**
 * Toggles whenever a set contains the given item;
 * if the set contains the item it will be removed.
 * if it does not contain the item, the item will be inserted.
 * @receiver [MutableSet]<T>
 * @param item T
 * @timecomplexity O(1)
 */
public inline fun <@kotlin.internal.OnlyInputTypes T> MutableSet<T>.toggleExistence(item: T) {
    setExistence(item, !contains(item))
}

