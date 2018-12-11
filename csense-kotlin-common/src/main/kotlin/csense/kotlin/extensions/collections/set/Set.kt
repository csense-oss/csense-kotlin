package csense.kotlin.extensions.collections.set

import csense.kotlin.*
import csense.kotlin.extensions.collections.generic.*


//region Generic collection extensions
/**
 * Performs traversal in pairs of 2  (with the first index as well)
 * @receiver List<T>
 * @param action Function2IndexedUnit<T, T>
 */
inline fun <T> Set<T>.foreach2Indexed(action: Function2IndexedUnit<T, T>) =
        GenericCollectionExtensions.forEach2Indexed(
                size,
                ::elementAt,
                action
        )

/**
 * Performs traversal in pairs of 2
 * @receiver List<T>
 * @param action Function2Unit<T, T>
 */
inline fun <T> Set<T>.foreach2(action: Function2Unit<T, T>) =
        GenericCollectionExtensions.forEach2(size, ::elementAt, action)

/**
 * Performs backwards traversal on this
 * @receiver List<T>
 * @param action FunctionUnit<T>
 */
inline fun <T> Set<T>.foreachBackwards(action: FunctionUnit<T>) =
        GenericCollectionExtensions.forEachBackwards(size, this::elementAt, action)
//endregion
