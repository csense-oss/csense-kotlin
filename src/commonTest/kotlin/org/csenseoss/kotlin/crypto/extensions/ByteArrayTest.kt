package org.csenseoss.kotlin.crypto.extensions

import org.csenseoss.kotlin.tests.assertions.collections.array.typed.byte.*
import org.csenseoss.kotlin.tests.assertions.primitives.byte.*
import kotlin.random.*
import kotlin.test.*

class ByteArrayTest {

    class WipeRandom {
        @Test
        fun empty() {
            val array: ByteArray = byteArrayOf()
            array.wipeRandom(random = Random(111))
            array.assertEmpty()
        }

        @Test
        fun single() {
            val array: ByteArray = byteArrayOf(42)
            array.wipeRandom(random = Random(111))
            array[0].assert(113)
        }

        @Test
        fun multiple() {
            val array: ByteArray = byteArrayOf(42, 42)
            array.wipeRandom(random = Random(888))
            array[0].assert(65)
            array[1].assert(-54)
        }
    }

    class UseThenWipe {
        @Test
        fun empty() {
            val array: ByteArray = byteArrayOf()
            array.useThenWipe(Random(888)) { it: ByteArray ->
                it.assertEmpty()
            }
            array.assertEmpty()
        }

        @Test
        fun single() {
            val array: ByteArray = byteArrayOf(42)
            array.useThenWipe(Random(123)) { it: ByteArray ->
                it[0].assert(42)
            }
            array[0].assert(92)
        }

        @Test
        fun multiple() {
            val array: ByteArray = byteArrayOf(42, 43)
            array.useThenWipe(Random(555)) { it: ByteArray ->
                it[0].assert(42)
                it[1].assert(43)
            }
            array[0].assert(60)
            array[1].assert(19)
        }
    }
}