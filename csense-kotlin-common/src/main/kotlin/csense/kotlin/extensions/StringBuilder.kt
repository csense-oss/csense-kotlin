package csense.kotlin.extensions

import csense.kotlin.extensions.collections.array.forEachDiscard


@Suppress("NOTHING_TO_INLINE")
inline fun StringBuilder.clear(): StringBuilder = apply {
    this.removeRange(0, length - 1)
}

@Suppress("NOTHING_TO_INLINE")
inline fun StringBuilder.set(content: String): StringBuilder = apply {
    clear()
    append(content)
}

@Suppress("NOTHING_TO_INLINE")
inline fun StringBuilder.set(content: CharArray): StringBuilder = apply {
    clear()
    appendContentOf(content)
}


@Suppress("NOTHING_TO_INLINE")
inline fun StringBuilder.appendContentOf(charArray: CharArray): StringBuilder = apply {
    charArray.forEachDiscard(this::append)
}
