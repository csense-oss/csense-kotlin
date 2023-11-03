package csense.kotlin.extensions.primitives.charSequence.word

import kotlin.jvm.*

@JvmInline
public value class CharSequenceWord(public val charSequence: CharSequence)

public inline val CharSequence.word: CharSequenceWord
    get() = CharSequenceWord(charSequence = this)

