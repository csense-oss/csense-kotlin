package csense.kotlin.extensions.primitives.charSequence.word.operations

import csense.kotlin.extensions.primitives.char.*
import csense.kotlin.extensions.primitives.charSequence.*
import csense.kotlin.extensions.primitives.charSequence.word.*

public fun CharSequenceWord.forEach(onEachWord: (CharSequence) -> Unit) {
    var startOfString: Int = charSequence.indexOfFirstOrNull(startIndex = 0) { it: Char ->
        it.isNotWhitespace()
    } ?: return
    do {
        val endOfString: Int = charSequence.indexOfFirstOrNull(startIndex = startOfString) { it: Char ->
            it.isWhitespace()
        } ?: return

        val subString: String = charSequence.substring(startIndex = startOfString, endIndex = endOfString)
        onEachWord(subString)

        startOfString = charSequence.indexOfFirstOrNull(startIndex = endOfString + 1) { it: Char ->
            it.isNotWhitespace()
        } ?: return
    } while (true)
}