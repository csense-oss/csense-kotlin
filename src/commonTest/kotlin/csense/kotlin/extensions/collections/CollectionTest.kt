@file:Suppress("unused")

package csense.kotlin.extensions.collections

import csense.kotlin.extensions.primitives.asDigit
import csense.kotlin.extensions.primitives.forEach
import csense.kotlin.tests.assertions.*
import kotlin.test.Test

class CollectionTest {

    @Test
    fun isIndexValid() {
        val collection: MutableCollection<String> = mutableListOf()
        collection.isIndexValid(0).assertFalse()
        collection.isIndexValid(-1).assertFalse()
        collection.add("test")
        collection.isIndexValid(0).assertTrue()
        collection.isIndexValid(-1).assertFalse()

        10.forEach {
            collection.add("")
        }
        for (i in 0 until 11) {
            collection.isIndexValid(i).assertTrue()
        }
        collection.isIndexValid(12).assertFalse()

    }

    @Test
    fun isIndexValidForInsert() {
        val collection: MutableCollection<String> = mutableListOf()
        collection.isIndexValidForInsert(0).assertTrue()
        collection.isIndexValidForInsert(-1).assertFalse()
        collection.add("test")
        collection.isIndexValidForInsert(0).assertTrue()
        collection.isIndexValidForInsert(1).assertTrue()
        collection.isIndexValidForInsert(2).assertFalse()
        collection.isIndexValidForInsert(12).assertFalse()
    }

//    @Test
//    fun getSafe() {
//        val collection: MutableCollection<String> = mutableListOf()
//        collection.getSafe(-1).assertNull()
//        collection.getSafe(0).assertNull("collection is empty.")
//        collection.getSafe(1).assertNull()
//        collection.add("test")
//        collection.getSafe(-1).assertNull()
//        collection.getSafe(0).assertNotNullAndEquals("test")
//        collection.getSafe(1).assertNull()
//        collection.add("1")
//        collection.add("2")
//        collection.add("3")
//        collection.getSafe(3).assertNotNullAndEquals("3")
//        collection.getSafe(4).assertNull()
//        collection.getSafe(2).assertNotNullAndEquals("2")
//        collection.getSafe(1).assertNotNullAndEquals("1")
//    }

    class GetSafeTests {
        @Test
        fun getSafeEmpty() {
            val collection: MutableCollection<String> = mutableListOf()
            collection.getSafe(-1).assertNull()
            collection.getSafe(0).assertNull("collection is empty.")
            collection.getSafe(1).assertNull()
        }

        @Test
        fun getSafeSingle() {
            val collection: MutableCollection<String> = mutableListOf()
            collection.add("test")
            collection.getSafe(-1).assertNull()
            collection.getSafe(0).assertNotNullAndEquals("test")
            collection.getSafe(1).assertNull()
        }

        @Test
        fun getSafeMultiple() {
            val collection: MutableCollection<String> = mutableListOf()
            collection.add("test")
            collection.add("1")
            collection.add("2")
            collection.add("3")
            collection.getSafe(3).assertNotNullAndEquals("3")
            collection.getSafe(4).assertNull()
            collection.getSafe(2).assertNotNullAndEquals("2")
            collection.getSafe(1).assertNotNullAndEquals("1")
        }

    }

    @Test
    fun isRangeValid() {
        val collection: MutableCollection<String> = mutableListOf()
        collection.isRangeValid(0 until 0).assertFalse("no range is valid in an empty collection")
        collection.isRangeValid(0 until 1).assertFalse("no range is valid in an empty collection")

        collection.addAll(listOf("a", "b"))
        collection.isRangeValid(0 until 0).assertFalse("0  -> -1 is not a valid range.")
        collection.isRangeValid(IntRange(-1, 1)).assertFalse("-1 -> 1 is not a valid range.")
        collection.isRangeValid(0 until 1).assertTrue()
        collection.isRangeValid(0 until 2).assertTrue()
        collection.isRangeValid(0 until 3).assertFalse()

        collection.isRangeValid(1 until 2).assertTrue()
        collection.isRangeValid(1 until 3).assertFalse()

        collection.add("c")
        collection.isRangeValid(1 until 3).assertTrue()
        collection.isRangeValid(1 until 2).assertTrue()
        collection.isRangeValid(0 until 1).assertTrue()
        collection.isRangeValid(3 until 1).assertTrue()
        collection.isRangeValid(2 until 1).assertTrue()
        collection.isRangeValid(4 until 1).assertFalse("even if the range is inverse, it should still be within bounds.")

    }


    @Test
    fun categorizeIntoMultiple() {
        val someData = listOf("a", "1", "qwerty", "cow", "blue", "red", "cat", "3", "b")
        //try filter only single characters and single numbers
        val (singleChars, singleDigits) = someData.categorizeIntoMultiple({
            it.length == 1 && it.first().asDigit() == null
        }, {
            it.length == 1 && it.first().asDigit() != null
        })
        singleChars.assertSize(2)
        singleDigits.assertSize(2)

        val (all, colors) = someData.categorizeIntoMultiple({
            true
        }, {
            setOf("blue", "red").contains(it)
        })

        all.assertSize(9)
        colors.assertSize(2)
        val (all1, all2, all3) = someData.categorizeIntoMultiple(
                { true },
                { true },
                { true })

        all1.assertSize(9)
        all2.assertSize(9)
        all3.assertSize(9)
        val (falseAll1, falseAll2) = someData.categorizeIntoMultiple(
                { false },
                { false })
        falseAll1.assertSize(0)
        falseAll2.assertSize(0)
    }

    @Test
    fun categorizeIntoSingle() {
        val someData = listOf("a", "1", "qwerty", "cow", "blue", "red", "cat", "3", "b")
        //try filter only single characters and single numbers
        val (singleChars, singleDigits) = someData.categorizeIntoSingle({
            it.length == 1 && it.first().asDigit() == null
        }, {
            it.length == 1 && it.first().asDigit() != null
        })
        singleChars.assertSize(2)
        singleDigits.assertSize(2)

        val (all, colors) = someData.categorizeIntoSingle({
            true
        }, {
            failTest("should never get called, since the other filter will eat this")
        })

        all.assertSize(9)
        colors.assertSize(0, "since all eats the item")
        val (all1, all2, all3) = someData.categorizeIntoSingle(
                { true },
                { failTest() },
                { failTest() })

        all1.assertSize(9)
        all2.assertSize(0)
        all3.assertSize(0)
        val (falseAll1, falseAll2) = someData.categorizeIntoSingle(
                { false },
                { false })
        falseAll1.assertSize(0)
        falseAll2.assertSize(0)

        val (falseAll11, falseAll21, trueAll21) = someData.categorizeIntoSingle(
                { false },
                { false },
                { true })
        falseAll11.assertSize(0)
        falseAll21.assertSize(0)
        trueAll21.assertSize(9)

    }

    @Test
    fun categorize() {
        val empty = listOf<String>()
        empty.categorize { failTest("should never get called") }.assertSize(0)

        val single = listOf("test")
        single.categorize { it }.apply {
            assertSize(1)
            this["test"].assertNotNullAndEquals(listOf("test"))
        }


        val twoOfTwo = listOf("test-1", "test-2", "asd-1", "asd-2")
        val twoOfTwoCat = twoOfTwo.categorize {
            if (it.startsWith("test")) {
                "test"
            } else {
                "asd"
            }
        }
        twoOfTwoCat.assertSize(2)
        twoOfTwoCat["test"].assertNotNullApply {
            assertSize(2)
        }
        twoOfTwoCat["asd"].assertNotNullApply {
            assertSize(2)
        }
    }

    @Test
    fun reversedIf() {
        val empty = listOf<String>()
        empty.reversedIf(false).assertEmpty()
        empty.reversedIf(true).assertEmpty()

        val single = listOf("single")
        single.reversedIf(true).apply { assertSize(1); first().assert("single") }
        single.reversedIf(false).apply { assertSize(1); first().assert("single") }

        val two = listOf("a", "b")
        two.reversedIf(false).first().assert("a")
        two.reversedIf(false).last().assert("b")

        two.reversedIf(true).first().assert("b")
        two.reversedIf(true).last().assert("a")
    }

    @Test
    fun isNotNullOrEmpty() {
        val nullCol: Collection<String>? = null
        nullCol.isNotNullOrEmpty().assert(false)
        val emptyCol: Collection<String> = listOf()
        emptyCol.isNotNullOrEmpty().assert(false)
        val singleCol: Collection<String> = listOf("omg")
        singleCol.isNotNullOrEmpty().assert(true)
    }

    @Test
    fun isNullOrEmpty() {
        val nullCol: Collection<String>? = null
        nullCol.isNullOrEmpty().assert(true)
        val emptyCol: Collection<String> = listOf()
        emptyCol.isNullOrEmpty().assert(true)
        val singleCol: Collection<String> = listOf("omg")
        singleCol.isNullOrEmpty().assert(false)
    }

    class IsAllTrue {
        @Test
        fun empty() {
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


    @Test
    fun collectionTSecondLastOrNull() {
        val empty: Collection<String> = listOf()
        empty.secondLastOrNull().assertNull()
        val single: Collection<String> = listOf("a")
        single.secondLastOrNull().assertNull()
        val two: Collection<String> = listOf("a", "b")
        two.secondLastOrNull().assertNotNullAndEquals("a")
        val aLot = listOf("a", "1", "2", "b")
        aLot.secondLastOrNull().assertNotNullAndEquals("2")
    }


    class CollectionTCategorizeByString {

        @Test
        fun empty() {
            listOf<String>().categorizeByString { it }.assertSize(0)
            listOf<String>().categorizeByString { "same" }.assertSize(0)

        }

        @Test
        fun single(){

        }

        @Test
        fun multiple(){

        }
    }
    class CollectionTIndexOfOrNull {
        @Test
        fun empty() {
            val lst = listOf<String>()
            lst.indexOfOrNull("a").assertNull()
            lst.indexOfOrNull("").assertNull()
            lst.indexOfOrNull("b").assertNull()
        }
        
        @Test
        fun single() {
            val lst = listOf("a")
            lst.indexOfOrNull("a").assertNotNullAndEquals(0)
            lst.indexOfOrNull("").assertNull()
            lst.indexOfOrNull("b").assertNull()
        }
        
        @Test
        fun multiple() {
            val lst = listOf("d", "c")
            lst.indexOfOrNull("a").assertNull()
            lst.indexOfOrNull("").assertNull()
            lst.indexOfOrNull("b").assertNull()
            lst.indexOfOrNull("c").assertNotNullAndEquals(1)
            lst.indexOfOrNull("d").assertNotNullAndEquals(0)
            lst.indexOfOrNull("dc").assertNull()
        }
        
        @Test
        fun multipleOccurrences() {
            val lst = listOf("d", "c", "d", "c")
            lst.indexOfOrNull("a").assertNull()
            lst.indexOfOrNull("").assertNull()
            lst.indexOfOrNull("c").assertNotNullAndEquals(1)
            lst.indexOfOrNull("d").assertNotNullAndEquals(0)
        }
    }
}