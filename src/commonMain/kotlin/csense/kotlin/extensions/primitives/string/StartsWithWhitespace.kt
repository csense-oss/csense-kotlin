package csense.kotlin.extensions.primitives.string

public fun String.startsWithWhitespace(): Boolean {
    val first: Char = firstOrNull() ?: return false
    return first.isWhitespace()
}