package org.csenseoss.kotlin.extensions.primitives.number

import org.csenseoss.kotlin.extensions.primitives.boolean.*
import org.csenseoss.kotlin.tests.assertions.*
import org.csenseoss.kotlin.tests.assertions.general.*
import org.csenseoss.kotlin.tests.assertions.primitives.boolean.*
import kotlin.test.*

class NumberTest {
    class NumberNullOnZero {
        @Test
        fun byte() {
            (-1).toByte().nullOnZero().assertByEquals((-1).toByte())
            (0).toByte().nullOnZero().assertNull()
            (1).toByte().nullOnZero().assertByEquals((1).toByte())
        }

        @Test
        fun short() {
            (-1).toShort().nullOnZero().assertByEquals((-1).toShort())
            (0).toShort().nullOnZero().assertNull()
            (1).toShort().nullOnZero().assertByEquals((1).toShort())
        }

        @Test
        fun int() {
            (-1).nullOnZero().assertByEquals((-1))
            (0).nullOnZero().assertNull()
            (1).nullOnZero().assertByEquals((1))
        }

        @Test
        fun long() {
            (-1L).nullOnZero().assertByEquals((-1L))
            (0L).nullOnZero().assertNull()
            (1L).nullOnZero().assertByEquals((1L))
        }

        @Test
        fun float() {
            (-1F).nullOnZero().assertByEquals((-1F))
            (0F).nullOnZero().assertNull()
            (1F).nullOnZero().assertByEquals((1F))
        }

        @Test
        fun double() {
            (-1.0).nullOnZero().assertByEquals((-1.0))
            (0.0).nullOnZero().assertNull()
            (1.0).nullOnZero().assertByEquals((1.0))
        }
    }

    class NumberIsZero {
        @Test
        fun byte() {
            (-1).toByte().isZero.assertFalse()
            0.toByte().isZero.assertTrue()
            1.toByte().isZero.assertFalse()
        }

        @Test
        fun short() {
            (-1).toShort().isZero.assertFalse()
            0.toShort().isZero.assertTrue()
            1.toShort().isZero.assertFalse()
        }

        @Test
        fun int() {
            (-1).isZero.assertFalse()
            0.isZero.assertTrue()
            1.isZero.assertFalse()
        }

        @Test
        fun long() {
            (-1L).isZero.assertFalse()
            0L.isZero.assertTrue()
            1L.isZero.assertFalse()
        }

        @Test
        fun float() {
            (-1f).isZero.assertFalse()
            0f.isZero.assertTrue()
            1f.isZero.assertFalse()
        }

        @Test
        fun double() {
            (-1.0).isZero.assertFalse()
            (0.0).isZero.assertTrue()
            (1.0).isZero.assertFalse()
        }
    }
}