package org.csenseoss.kotlin.extensions.primitives.charSequence.word.operations

import org.csenseoss.kotlin.extensions.primitives.charSequence.word.*

public fun CharSequenceWord.count(): Int {
    var result = 0
    forEach { _: CharSequence ->
        result += 1
    }
    return result
}