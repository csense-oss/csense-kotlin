package csense.kotlin.extensions.primitives.string

import csense.kotlin.extensions.stringBuilder.*

public inline fun String.forEachSplitIndexed(
    shouldSplit: (index: Int, char: Char) -> Boolean,
    onSplit: (startingIndex: Int, split: String) -> Unit
) {
    if (isEmpty()) {
        return
    }
    val splitContainer = StringSplitIndexedContainer(
        initialCapacityOf = this
    )
    splitContainer.forEachSplitOnString(
        string = this,
        shouldSplit = shouldSplit,
        onSplit = onSplit
    )
}

//TODO hm..... can avoid exposing this?
//Todo name etc?? hmm...
public class StringSplitIndexedContainer(
    capacity: Int = 100
) {
    public constructor (initialCapacityOf: String) : this(initialCapacityOf.length)

    private var startingIndex: Int = 0

    private val currentStringBuilder: StringBuilder = StringBuilder(capacity)

    public fun append(char: Char) {
        currentStringBuilder.append(char)
    }

    public fun split(): Pair<Int, String> {
        val splitString = currentStringBuilder.toStringAndClear()
        return Pair(startingIndex, splitString).also {
            startingIndex += splitString.length
        }
    }


    public fun isNotEmpty(): Boolean =
        !isEmpty()

    private fun isEmpty(): Boolean =
        currentStringBuilder.isEmpty()

    public inline fun forEachSplitOnString(
        string: String,
        shouldSplit: (index: Int, char: Char) -> Boolean,
        onSplit: (startingIndex: Int, split: String) -> Unit
    ) {
        string.forEachIndexed { index: Int, char: Char ->
            if (shouldSplit(index, char)) {
                splitWithCallback(onSplit)
            }
            append(char)
        }
        splitWithCallbackIfNotEmpty(onSplit)
    }

    public inline fun splitWithCallbackIfNotEmpty(
        callback: (startingIndex: Int, split: String) -> Unit
    ) {
        if (isNotEmpty()) {
            splitWithCallback(callback)
        }
    }

    public inline fun splitWithCallback(
        callback: (startingIndex: Int, split: String) -> Unit
    ) {
        val (startingIndex: Int, string: String) = split()
        callback(startingIndex, string)
    }

}