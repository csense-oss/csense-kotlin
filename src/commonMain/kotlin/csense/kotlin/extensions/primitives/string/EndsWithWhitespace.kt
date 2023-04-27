package csense.kotlin.extensions.primitives.string

public fun String.endsWithWhitespace(): Boolean {
    val last: Char = lastOrNull() ?: return false
    return last.isWhitespace()
}