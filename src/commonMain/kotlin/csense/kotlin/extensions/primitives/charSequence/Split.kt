@file:Suppress("NOTHING_TO_INLINE")

package csense.kotlin.extensions.primitives.charSequence

import csense.kotlin.extensions.collections.generic.collectionBounds.operations.*


/**
 * Splits this [CharSequence] by the given Chars
 * @receiver [CharSequence]
 * @param delimiters [Set]<[Char]>
 * @return [List]<[String]>
 */
public inline fun CharSequence.split(delimiters: Set<Char>): List<String> = splitBy {
    it in delimiters
}

/**
 * Splits this [CharSequence] by the given [shouldSplit]
 * @receiver [CharSequence]
 * @param shouldSplit [Function1]<[Char], [Boolean]>
 * @return [List]<[String]>
 */
public inline fun CharSequence.splitBy(shouldSplit: Function1<Char, Boolean>): List<String> {
    val results = mutableListOf<String>()
    var currentStartIndex = 0
    forEachIndexed { index, char ->
        if (shouldSplit(char)) {
            val splitLength = index - currentStartIndex
            if (splitLength > 0) {
                results += substring(startIndex = currentStartIndex, endIndex = index)
            }
            currentStartIndex = index + 1 // +1 since we are "taking" the "char" that gets "split"
        }
    }

    if (isIndex.inBounds(currentStartIndex, isEndInBounds = false)) {
        results += substring(startIndex = currentStartIndex)
    }

    return results
}
