@file:Suppress("unused", "NOTHING_TO_INLINE", "INVISIBLE_MEMBER", "INVISIBLE_REFERENCE")

package csense.kotlin.extensions.collections.array.generic


/**
 * Maps each element via [transform] into a [MutableList]
 * @receiver Array<Item>
 * @param transform Function1<Item, Result>
 * @return MutableList<Result>
 */
public inline fun <Item, Result> Array<Item>.mapToMutable(
    transform: (Item) -> Result
): MutableList<Result> = mapEachWith(ArrayList(size)) { it: Item ->
    this += transform(it)
}

/**
 * Apply [transform] on each item [with] the given [result]
 * @param result Result the result to apply each [transform] to
 * @param transform Function2<Result, Item, Unit> the processing of the given item on the [result]
 * @return Result
 */
public inline fun <Item, Result> Array<Item>.mapEachWith(
    result: Result,
    transform: Result.(Item) -> Unit
): Result {
    forEach { it: Item ->
        result.transform(it)
    }
    return result
}
