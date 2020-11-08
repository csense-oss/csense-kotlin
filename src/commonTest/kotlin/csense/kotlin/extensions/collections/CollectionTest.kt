@file:Suppress("unused")

package csense.kotlin.extensions.collections

import csense.kotlin.extensions.*
import csense.kotlin.extensions.collections.array.*
import csense.kotlin.extensions.primitives.*
import csense.kotlin.tests.assertions.*
import kotlin.test.*

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


    class CollectionTGetOrNull {
        @Test
        fun getOrNullEmpty() {
            val collection: MutableCollection<String> = mutableListOf()
            collection.getOrNull(-1).assertNull()
            collection.getOrNull(0).assertNull("collection is empty.")
            collection.getOrNull(1).assertNull()
        }

        @Test
        fun getOrNullSingle() {
            val collection: MutableCollection<String> = mutableListOf()
            collection.add("test")
            collection.getOrNull(-1).assertNull()
            collection.getOrNull(0).assertNotNullAndEquals("test")
            collection.getOrNull(1).assertNull()
        }

        @Test
        fun getOrNullMultiple() {
            val collection: MutableCollection<String> = mutableListOf()
            collection.add("test")
            collection.add("1")
            collection.add("2")
            collection.add("3")
            collection.getOrNull(3).assertNotNullAndEquals("3")
            collection.getOrNull(4).assertNull()
            collection.getOrNull(2).assertNotNullAndEquals("2")
            collection.getOrNull(1).assertNotNullAndEquals("1")
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
        collection.isRangeValid(4 until 1)
            .assertFalse("even if the range is inverse, it should still be within bounds.")

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


    @Test
    fun collectionTSecondLastOrNull() {
        val emptyData: Collection<String> = listOf()
        emptyData.secondLastOrNull().assertNull()
        val singleData: Collection<String> = listOf("a")
        singleData.secondLastOrNull().assertNull()
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
        fun single() {
            listOf("test").categorizeByString { it }.apply {
                assertSize(1)
                keys.first().assert("test")
                values.first().assertSize(1)
                values.first().first().assert("test")
            }

            listOf("test").categorizeByString { "same" }.apply {
                assertSize(1)
                keys.first().assert("same")
                values.first().assertSize(1)
                values.first().first().assert("test")
            }
        }

        @Test
        fun multiple() {
            listOf("test", "1234").categorizeByString { "constantKey" }.apply {
                assertSize(1)
                keys.first().assert("constantKey")
                values.first().assertSize(2)
                values.assertContainsAll(listOf("test", "1234"))
            }
            listOf("test", "1234").categorizeByString { it }.apply {
                assertSize(2)
                keys.assertContainsAll("1234", "test")
                values.assertContainsAll(listOf("1234"), listOf("test"))
            }
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

    class CollectionTLastIndexOfOrNull {
        @Test
        fun empty() {
            val lst = listOf<String>()
            lst.lastIndexOfOrNull("a").assertNull()
            lst.lastIndexOfOrNull("").assertNull()
            lst.lastIndexOfOrNull("b").assertNull()
        }

        @Test
        fun single() {
            val lst = listOf("a")
            lst.lastIndexOfOrNull("a").assertNotNullAndEquals(0)
            lst.lastIndexOfOrNull("").assertNull()
            lst.lastIndexOfOrNull("b").assertNull()
        }

        @Test
        fun multiple() {
            val lst = listOf("d", "c")
            lst.lastIndexOfOrNull("a").assertNull()
            lst.lastIndexOfOrNull("").assertNull()
            lst.lastIndexOfOrNull("b").assertNull()
            lst.lastIndexOfOrNull("c").assertNotNullAndEquals(1)
            lst.lastIndexOfOrNull("d").assertNotNullAndEquals(0)
            lst.lastIndexOfOrNull("dc").assertNull()
        }

        @Test
        fun multipleOccurrences() {
            val lst = listOf("d", "c", "d", "c")
            lst.lastIndexOfOrNull("a").assertNull()
            lst.lastIndexOfOrNull("").assertNull()
            lst.lastIndexOfOrNull("c").assertNotNullAndEquals(3)
            lst.lastIndexOfOrNull("d").assertNotNullAndEquals(2)
        }
    }


    class CollectionTNullOnEmpty {
        @Test
        fun empty() {
            listOf<String>()
                .nullOnEmpty()
                .assertNull("as the name suggest, should be null on empty")
        }

        @Test
        fun single() {
            listOf("a")
                .nullOnEmpty()
                .assertNotNullApply("should not be null and have a single element that is 'a'") {
                    assertSize(1)
                    first().assert("a")
                }
        }

        @Test
        fun multiple() {
            listOf("1", "b", "3")
                .nullOnEmpty()
                .assertNotNullApply("should not be null and have a 3 elements") {
                    assertSize(3)
                    this.elementAt(0).assert("1")
                    this.elementAt(1).assert("b")
                    this.elementAt(2).assert("3")
                }
        }
    }

    class CollectionTSelectFirstOrNull {
        @Test
        fun emptySelect() {
            listOf<String>().selectFirstOrNull {
                it.length
            }.assertNull("cannot find anything in an empty list")
        }

        @Test
        fun emptySelectNotFound() {
            listOf<String>().selectFirstOrNull {
                null
            }.assertNull("cannot find anything in an empty list")
        }

        @Test
        fun singleSelect() {
            listOf("test").selectFirstOrNull {
                it.length
            }.assertNotNullAndEquals(
                4,
                "length of test is 4 , and there is a single element in the collection"
            )
        }

        @Test
        fun singleSelectNotFound() {
            listOf("test").selectFirstOrNull {
                null
            }.assertNull("Non is ever selected, so should be null")
        }

        @Test
        fun multipleFound() {
            listOf("test", "asdf").selectFirstOrNull {
                it.firstOrNull()
            }.assertNotNullAndEquals('t', "test is first")

            listOf("test", "2").selectFirstOrNull {
                if (it.length == 1) {
                    it.firstOrNull()
                } else {
                    null
                }
            }.assertNotNullAndEquals('2', "since we only return the char when the string has a length of 1")
        }

        @Test
        fun multipleNotFound() {
            listOf("test", "asdf", "1234").selectFirstOrNull {
                (it.length > 10).map(this, null)
            }.assertNull("no string is larger than 10 chars.")
        }
    }

    //TODO when test plugin understands it,use "toJoin" as the end name
    class CollectionTJoinEveryItemsBetweenJoin {
        @Test
        fun empty() {
            listOf<String>().joinEvery(-1, "").assertSize(0)
            listOf<String>().joinEvery(0, "").assertSize(0)
            listOf<String>().joinEvery(1, "").assertSize(0)
        }

        @Test
        fun single() {
            listOf("a").joinEvery(-1, "b").apply {
                assertSize(1)
                first().assert("a")
            }
            listOf("a").joinEvery(0, "b").apply {
                assertSize(1)
                first().assert("a")
            }
            listOf("a").joinEvery(1, "b").apply {
                assertSize(1)
                first().assert("a")
            }
        }

        @Test
        fun multiple() {
            listOf("a", "b").joinEvery(1, "0").apply {
                assertSize(3)
                this[0].assert("a")
                this[1].assert("0")
                this[2].assert("b")
            }

            listOf("a", "b", "c").joinEvery(1, "0").apply {
                assertSize(5)
                this[0].assert("a")
                this[1].assert("0")
                this[2].assert("b")
                this[3].assert("0")
                this[4].assert("c")
            }
            listOf("a", "b", "c").joinEvery(2, ",").apply {
                assertSize(4)
                this[0].assert("a")
                this[1].assert("b")
                this[2].assert(",")
                this[3].assert("c")
            }
        }
    }

    class CollectionTJoinEveryAction {
        @Test
        fun empty() {
            listOf<String>().joinEveryAction(-1) { failTest() }.assertSize(0)
            listOf<String>().joinEveryAction(0) { failTest() }.assertSize(0)
            listOf<String>().joinEveryAction(1) { failTest() }.assertSize(0)
        }

        @Test
        fun single() {
            listOf("a").joinEvery(-1, "b").apply {
                assertSize(1)
                first().assert("a")
            }
            listOf("a").joinEvery(0, "b").apply {
                assertSize(1)
                first().assert("a")
            }
            listOf("a").joinEvery(1, "b").apply {
                assertSize(1)
                first().assert("a")
            }
        }

        @Test
        fun multiple() {
            listOf("a", "b").joinEveryAction(1) { "0" }.apply {
                assertSize(3)
                this[0].assert("a")
                this[1].assert("0")
                this[2].assert("b")
            }

            listOf("a", "b", "c").joinEveryAction(1) { "0" }.apply {
                assertSize(5)
                this[0].assert("a")
                this[1].assert("0")
                this[2].assert("b")
                this[3].assert("0")
                this[4].assert("c")
            }
            listOf("a", "b", "c").joinEveryAction(2) { "," }.apply {
                assertSize(4)
                this[0].assert("a")
                this[1].assert("b")
                this[2].assert(",")
                this[3].assert("c")
            }
        }
    }
}
