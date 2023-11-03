package csense.kotlin.extensions.primitives.charSequence.word.operations

import csense.kotlin.extensions.primitives.charSequence.word.*

public fun CharSequenceWord.count(): Int {
    var result = 0
    forEach { _: CharSequence ->
        result += 1
    }
    return result
}