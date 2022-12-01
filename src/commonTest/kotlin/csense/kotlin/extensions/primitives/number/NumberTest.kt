package csense.kotlin.extensions.primitives.number

import csense.kotlin.extensions.primitives.boolean.*
import csense.kotlin.tests.assertions.*
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
            (-1).toByte().isZero.isFalse()
            0.toByte().isZero.isTrue()
            1.toByte().isZero.isFalse()
        }

        @Test
        fun short() {
            (-1).toShort().isZero.isFalse()
            0.toShort().isZero.isTrue()
            1.toShort().isZero.isFalse()
        }

        @Test
        fun int() {
            (-1).isZero.isFalse()
            0.isZero.isTrue()
            1.isZero.isFalse()
        }

        @Test
        fun long() {
            (-1L).isZero.isFalse()
            0L.isZero.isTrue()
            1L.isZero.isFalse()
        }

        @Test
        fun float() {
            (-1f).isZero.isFalse()
            0f.isZero.isTrue()
            1f.isZero.isFalse()
        }

        @Test
        fun double() {
            (-1.0).isZero.isFalse()
            (0.0).isZero.isTrue()
            (1.0).isZero.isFalse()
        }
    }
}