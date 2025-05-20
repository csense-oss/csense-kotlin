package org.csenseoss.kotlin.extensions.primitives.charSequence.word.operations

import org.csenseoss.kotlin.extensions.primitives.char.*
import org.csenseoss.kotlin.extensions.primitives.charSequence.*
import org.csenseoss.kotlin.extensions.primitives.charSequence.word.*

public fun CharSequenceWord.forEach(onEachWord: (CharSequence) -> Unit) {
    var startOfString: Int = charSequence.indexOfFirstOrNull(startIndex = 0, predicate = Char::isNotWhitespace) ?: return
    do {

        val endOfString: Int = charSequence.indexOfFirstOrNull(
            startIndex = startOfString,
            predicate = Char::isWhitespace
        ) ?: charSequence.length

        val subString: String = charSequence.substring(startIndex = startOfString, endIndex = endOfString)
        onEachWord(subString)

        startOfString = charSequence.indexOfFirstOrNull(startIndex = endOfString + 1) { it: Char ->
            it.isNotWhitespace()
        } ?: return
    } while (true)
}