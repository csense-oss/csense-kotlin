package csense.kotlin.extensions.collections.collection

import csense.kotlin.tests.assertions.*
import kotlin.test.*

class CollectionBooleanTest {

    class IsAllTrue {
        @Test
        fun emptyTest() {
            val empty: Collection<Boolean> = listOf()
            empty.isAllTrue().assertTrue()
        }

        @Test
        fun singleTrue() {
            val single: Collection<Boolean> = listOf(true)
            single.isAllTrue().assertTrue()
        }

        @Test
        fun singleFalse() {
            val single: Collection<Boolean> = listOf(false)
            single.isAllTrue().assertFalse()
        }

        @Test
        fun multipleTrue() {
            val multiple: Collection<Boolean> = listOf(true, true)
            multiple.isAllTrue().assertTrue()
        }

        @Test
        fun multipleFalse() {
            val multiple: Collection<Boolean> = listOf(false, false)
            multiple.isAllTrue().assertFalse()
        }

        @Test
        fun multipleMixed() {
            val multiple: Collection<Boolean> = listOf(true, false)
            multiple.isAllTrue().assertFalse()
        }
    }

    class IsAllFalse {
        @Test
        fun emptyTest() {
            val empty: Collection<Boolean> = listOf()
            empty.isAllFalse().assertTrue()
        }

        @Test
        fun singleTrue() {
            val single: Collection<Boolean> = listOf(true)
            single.isAllFalse().assertFalse()
        }

        @Test
        fun singleFalse() {
            val single: Collection<Boolean> = listOf(false)
            single.isAllFalse().assertTrue()
        }

        @Test
        fun multipleTrue() {
            val multiple: Collection<Boolean> = listOf(true, true)
            multiple.isAllFalse().assertFalse()
        }

        @Test
        fun multipleFalse() {
            val multiple: Collection<Boolean> = listOf(false, false)
            multiple.isAllFalse().assertTrue()
        }

        @Test
        fun multipleMixed() {
            val multiple: Collection<Boolean> = listOf(true, false)
            multiple.isAllFalse().assertFalse()
        }
    }

}