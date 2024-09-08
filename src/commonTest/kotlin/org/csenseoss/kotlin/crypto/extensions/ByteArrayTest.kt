package org.csenseoss.kotlin.crypto.extensions

import csense.kotlin.tests.assertions.*
import kotlin.random.*
import kotlin.test.*

class ByteArrayTest {

    class WipeRandom {
        @Test
        fun empty() {
            val array = byteArrayOf()
            array.wipeRandom(random = Random(111))
            array.assertSize(0)
        }

        @Test
        fun single() {
            val array = byteArrayOf(42)
            array.wipeRandom(random = Random(111))
            array[0].assert(113)
        }

        @Test
        fun multiple() {
            val array = byteArrayOf(42, 42)
            array.wipeRandom(random = Random(888))
            array[0].assert(65)
            array[1].assert(-54)
        }
    }

    class UseThenWipe {
        @Test
        fun empty() {
            val array = byteArrayOf()
            array.useThenWipe(Random(888), { it: ByteArray ->
                it.assertSize(0)
            })
            array.assertSize(0)
        }

        @Test
        fun single() {
            val array = byteArrayOf(42)
            array.useThenWipe(Random(123), { it: ByteArray ->
                it[0].assert(42)
            })
            array[0].assert(92)
        }

        @Test
        fun multiple() {
            val array = byteArrayOf(42,43)
            array.useThenWipe(Random(555), { it: ByteArray ->
                it[0].assert(42)
                it[1].assert(43)
            })
            array[0].assert(60)
            array[1].assert(19)
        }
    }
}