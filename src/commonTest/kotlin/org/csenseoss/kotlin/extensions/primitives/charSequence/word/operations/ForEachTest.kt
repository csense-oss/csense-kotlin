package org.csenseoss.kotlin.extensions.primitives.charSequence.word.operations

import org.csenseoss.kotlin.extensions.primitives.charSequence.word.*
import org.csenseoss.kotlin.tests.assertions.general.*
import org.csenseoss.kotlin.tests.assertions.primitives.charSequence.*
import kotlin.test.*

class ForEachTest {

    @Test
    fun empty() {
        "".word.forEach { _: CharSequence -> shouldNotBeCalled("empty has no words") }
    }

    @Test
    fun singleWord() = assertCalled { shouldBeCalled: () -> Unit ->
        "test".word.forEach { it: CharSequence ->
            it.assert("test")
            shouldBeCalled()
        }
    }

    @Test
    fun twoWords() = assertCalled(times = 2) { shouldBeCalled: () -> Unit ->
        "test mig".word.forEach { _: CharSequence ->
            shouldBeCalled()
        }
    }

    @Test
    fun extraSpaces() = assertCalled(times = 1) { shouldBeCalled: () -> Unit ->
        " test ".word.forEach { _: CharSequence ->
            shouldBeCalled()
        }
    }
}