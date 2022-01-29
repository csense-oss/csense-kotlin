@file:Suppress("unused")

package csense.kotlin.extensions.collections

import csense.kotlin.extensions.*
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
                values.assertSingle {
                    it.assertSize(2)
                    it.assertContainsAll(listOf("test", "1234"))
                }
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
            listOf("test", "asd").selectFirstOrNull {
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
            listOf("test", "asd", "1234").selectFirstOrNull {
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

    class CollectionTIndexOfFirstOrNull {
        @Test
        fun empty() {
            listOf<String>().indexOfFirstOrNull { shouldNotBeCalled() }.assertNull()
        }

        @Test
        fun single() {
            listOf("test").indexOfFirstOrNull { false }.assertNull()
            listOf("test").indexOfFirstOrNull { true }.assertNotNullAndEquals(0)
            listOf("test").indexOfFirstOrNull { it == "test" }.assertNotNullAndEquals(0)
        }

        @Test
        fun multiple() {
            listOf("1", "2", "3", "1").indexOfFirstOrNull { it == "1" }
                .assertNotNullAndEquals(0, "should search from the start")
            listOf("1", "2", "3", "1").indexOfFirstOrNull { it == "3" }.assertNotNullAndEquals(2)
            listOf("1", "2", "3", "1").indexOfFirstOrNull { it == "4" }.assertNull()
        }
    }

    class CollectionTIndexOfLastOrNull {
        @Test
        fun empty() {
            listOf<String>().indexOfLastOrNull { shouldNotBeCalled() }.assertNull()
        }

        @Test
        fun single() {
            listOf("test").indexOfLastOrNull { false }.assertNull()
            listOf("test").indexOfLastOrNull { true }.assertNotNullAndEquals(0)
            listOf("test").indexOfLastOrNull { it == "test" }.assertNotNullAndEquals(0)
        }

        @Test
        fun multiple() {
            listOf("1", "2", "3", "1").indexOfLastOrNull { it == "1" }
                .assertNotNullAndEquals(3, "should search from the end towards the start")
            listOf("1", "2", "3", "1").indexOfLastOrNull { it == "3" }.assertNotNullAndEquals(2)
            listOf("1", "2", "3", "1").indexOfLastOrNull { it == "4" }.assertNull()
        }
    }

    class CollectionElementCategorizeIntoFilters {
        @Test
        fun emptyInputEmptyFilters() {
            listOf<String>().categorizeInto().assertEmpty()
        }

        @Test
        fun emptyInputWithFilters() {
            listOf<String>().categorizeInto({ true }).assertSingle {
                it.isEmpty()
            }
            listOf<String>().categorizeInto({ true }, { true }, allowItemInMultipleBuckets = false).apply {
                assertSize(2)
                first().assertEmpty()
                last().assertEmpty()
            }
            listOf<String>().categorizeInto({ true }, { true }, allowItemInMultipleBuckets = true).apply {
                assertSize(2)
                first().assertEmpty()
                last().assertEmpty()
            }
        }

        @Test
        fun singleInputNoFilters() {
            listOf("test").categorizeInto().assertEmpty()
        }

        @Test
        fun singleInputWithFilters() {
            listOf("test").categorizeInto({ false }).assertSingle {
                it.isEmpty()
            }
            listOf("test").categorizeInto({ true }).assertSingle {
                it.assertSingle("test")
            }
            listOf("test").categorizeInto({ false }, { false }, allowItemInMultipleBuckets = false).apply {
                assertSize(2)
                first().assertEmpty()
                last().assertEmpty()
            }
            listOf("test").categorizeInto({ true }, { true }, allowItemInMultipleBuckets = false).apply {
                assertSize(2)
                first().assertSingle("test")
                last().assertEmpty("since \"allowItemInMultipleBuckets\" is false")
            }
            listOf("test").categorizeInto({ true }, { true }, allowItemInMultipleBuckets = true).apply {
                assertSize(2)
                first().assertSingle("test")
                last().assertSingle("test")
            }
        }

        @Test
        fun mixed() {
            listOf("test", "abc").categorizeInto({ true }, { false }, { true }, allowItemInMultipleBuckets = false)
                .apply {
                    assertSize(3)
                    this[0].apply {
                        assertSize(2)
                        assertContainsInOrder("test", "abc")
                    }
                    this[1].assertEmpty("")
                    this[2].assertEmpty("\"allowItemInMultipleBuckets\" = false")
                }
            listOf("test", "123").categorizeInto({ true }, { false }, { true }, allowItemInMultipleBuckets = true)
                .apply {
                    assertSize(3)
                    this[0].apply {
                        assertSize(2)
                        assertContainsInOrder("test", "123")
                    }
                    this[1].assertEmpty()
                    this[2].apply {
                        assertSize(2)
                        assertContainsInOrder("test", "123")
                    }
                }

        }
    }

    class CollectionAnyForEachWithType {
        @Test
        fun empty() {
            val collection: Collection<String> = listOf()
            collection.forEachWithType<Int> { shouldNotBeCalled() }
            collection.forEachWithType<String> { shouldNotBeCalled() }
        }

        @Test
        fun single() {
            val collection: Collection<String> = listOf("1")
            collection.forEachWithType<Int> { shouldNotBeCalled() }
            assertCalled { shouldBeCalled ->
                collection.forEachWithType<String> {
                    it.assert("1")
                    shouldBeCalled()
                }
            }
            assertCalled { shouldBeCalled ->
                collection.forEachWithType<CharSequence> {
                    it.assert("1")
                    shouldBeCalled()
                }
            }
            assertCalled { shouldBeCalled ->
                collection.forEachWithType<Comparable<String>> {
                    it.compareTo("1").assert(0, "expect it to be the same string as \"1\"")
                    shouldBeCalled()
                }
            }
        }

        @Test
        fun multiple() {
            val collection: Collection<Any> = listOf("a", "b", 42)
            assertCalled { shouldBeCalled ->
                collection.forEachWithType<Int> {
                    it.assert(42)
                    shouldBeCalled()
                }
            }
            var isFirstCall = true
            assertCalled(times = 2) { shouldBeCalled ->
                collection.forEachWithType<String> {
                    if (isFirstCall) {
                        it.assert("a")
                    } else {
                        it.assert("b")
                    }
                    isFirstCall = false
                    shouldBeCalled()
                }
            }
            collection.forEachWithType<Char> { shouldNotBeCalled() }
        }
    }

    class CollectionAnyFindWithType {
        @Test
        fun empty() {
            val collection: Collection<String> = listOf()
            collection.findWithType<Int> { shouldNotBeCalled() }
            collection.findWithType<String> { shouldNotBeCalled() }
        }

        @Test
        fun single() {
            val collection: Collection<Any?> = listOf("test")
            collection.findWithType<Int> { shouldNotBeCalled() }
            collection.findWithType<String> {
                it.assert("test")
                false
            }.assertNull()

            collection.findWithType<String> {
                it.assert("test")
                true
            }.assertNotNullAndEquals("test")
        }

        @Test
        fun multiple() {
            val collection: Collection<Any?> = listOf("test", "1234", 1234)
            collection.findWithType<Char> { shouldNotBeCalled() }
            collection.findWithType<Char> { shouldNotBeCalled() }
            collection.findWithType<Number> {
                true
            }.assertNotNullAndEquals(1234)

            assertCallbackCalledWith(listOf("test", "1234")) { assertCallback ->
                collection.findWithType<String> {
                    assertCallback(it)
                    false
                }.assertNull()
            }

            collection.findWithType<String> {
                it.assert("test")
                true
            }.assertNotNullAndEquals("test")

            collection.findWithType<String> {
                it == "1234"
            }.assertNotNullAndEquals("1234")
        }

    }

    //region toMapFlat
    class CollectionTToMapFlatKeyMapper {
        @Test
        fun empty() {
            listOf<String>().toMapFlat { shouldNotBeCalled() }
        }

        @Test
        fun single() {
            listOf("1234").toMapFlat {
                it.assert("1234")
                it.toInt()
            }.assertSingle {
                it.key.assert(1234)
                it.value.assert("1234")
            }
        }

        @Test
        fun multipleDifferentKey() {
            val data = listOf("123", "abc")
            assertCallbackCalledWith(data) { assertIsExpected ->
                data.toMapFlat {
                    assertIsExpected(it)
                    "$it-"
                }.apply {
                    assertSize(2)
                    assertContains(
                        Pair("123-", "123")
                    )
                    assertContains(
                        Pair("abc-", "abc")
                    )
                }
            }
        }

        @Test
        fun multipleSameKey() {
            val data = listOf("123", "abc")
            assertCallbackCalledWith(data) { assertIsExpected ->
                data.toMapFlat {
                    assertIsExpected(it)
                    "test"
                }.assertSingle {
                    it.key.assert("test")
                    it.value.assert("abc", message = "123 gets overwritten by abc")
                }
            }
        }
    }

    class CollectionTToMutableMapFlatKeyMapper {
        @Test
        fun empty() {
            listOf<String>().toMutableMapFlat { shouldNotBeCalled() }
        }

        @Test
        fun single() {
            listOf("1234").toMutableMapFlat {
                it.assert("1234")
                it.toInt()
            }.assertSingle {
                it.key.assert(1234)
                it.value.assert("1234")
            }
        }

        @Test
        fun multipleSameKey() {
            val data = listOf("1234", "abc")
            assertCallbackCalledWith(data) { expectedValue ->
                data.toMutableMapFlat {
                    expectedValue(it)
                    "test"
                }.assertSingle {
                    it.value.assert("abc", message = "abc overwrites 1234")
                }
            }
        }

        @Test
        fun multipleDifferentKey() {
            val data = listOf("1234", "abc")
            assertCallbackCalledWith(data) { expectedValue ->
                val result = data.toMutableMapFlat {
                    expectedValue(it)
                    it
                }
                result.assertSize(2)
                result.assertContains(
                    Pair(
                        "1234", "1234"
                    )
                )
                result.assertContains(
                    Pair(
                        "abc", "abc"
                    )
                )
            }
        }
    }

    class CollectionTToMapKeyMapperFlatValueMapper {
        @Test
        fun empty() {
            listOf<String>().toMapFlat(
                keyMapper = { shouldNotBeCalled() },
                valueMapper = { shouldNotBeCalled() }
            )
        }

        @Test
        fun single() {
            listOf("555").toMapFlat(
                keyMapper = {
                    it.assert("555")
                    "key"
                },
                valueMapper = {
                    it.assert("555")
                    "value"
                }
            ).assertSingle {
                it.key.assert("key")
                it.value.assert("value")
            }
        }

        @Test
        fun multipleDifferentKey() {
            val data = listOf("1234", "abc")
            assertCallbackCalledWith(data) { expectedValue ->
                val result = data.toMapFlat(keyMapper = {
                    expectedValue(it)
                    it
                }, valueMapper = {
                    it.length
                })
                result.assertSize(2)
                result.assertContains(
                    Pair(
                        "1234", 4
                    )
                )
                result.assertContains(
                    Pair(
                        "abc", 3
                    )
                )
            }
        }

        @Test
        fun multipleSameKey() {
            val data = listOf("1234", "abc")
            assertCallbackCalledWith(data) { expectedValue ->
                val result = data.toMapFlat(keyMapper = {
                    expectedValue(it)
                    "qwerty"
                }, valueMapper = {
                    it.length
                })
                result.assertSingle {
                    it.key.assert("qwerty")
                    it.value.assert(3, message = "length of abc")
                }
            }
        }
    }

    class CollectionTToMutableMapFlatKeyMapperValueMapper {
        @Test
        fun empty() {
            listOf<String>().toMutableMapFlat(
                keyMapper = { shouldNotBeCalled() },
                valueMapper = { shouldNotBeCalled() }
            )
        }

        @Test
        fun single() {
            listOf("555").toMutableMapFlat(
                keyMapper = {
                    it.assert("555")
                    "key"
                },
                valueMapper = {
                    it.assert("555")
                    "value"
                }
            ).assertSingle {
                it.key.assert("key")
                it.value.assert("value")
            }
        }

        @Test
        fun multipleSameKey() {
            val data = listOf("123", "abc")
            assertCallbackCalledWith(data) { expectedValue ->
                val result = data.toMutableMapFlat(
                    keyMapper = {
                        expectedValue(it)
                        "key"
                    },
                    valueMapper = {
                        it.length
                    }
                )
                result.assertSingle {
                    it.key.assert("key")
                    it.value.assert(3, "length of abc")
                }
            }
        }

        @Test
        fun multipleDifferentKey() {
            val data = listOf("123", "abc")
            assertCallbackCalledWith(data) { expectedValue ->
                val result = data.toMutableMapFlat(
                    keyMapper = {
                        expectedValue(it)
                        it
                    },
                    valueMapper = {
                        it
                    }
                )
                result.assertSize(2)
                result.assertContains(
                    Pair(
                        "123", "123"
                    )
                )
                result.assertContains(
                    Pair(
                        "abc", "abc"
                    )
                )
            }

        }
    }
    //endregion

    //region toMap
    class CollectionTToMapKeyMapper {
        @Test
        fun empty() {
            listOf<String>().toMap {
                shouldNotBeCalled()
            }.assertEmpty()
        }

        @Test
        fun single() {
            listOf("abc").toMap {
                "key"
            }.assertSingle {
                it.key.assert("key")
                it.value.assertSingle("abc")
            }
        }

        @Test
        fun multipleDifferentKeys() {
            val data = listOf("1234", "456")
            assertCallbackCalledWith(data) { expectedValue ->
                val size = data.toMap {
                    expectedValue(it)
                    it
                }
                size.assertSize(2)
                size.assertContains(
                    Pair(
                        "1234",
                        listOf("1234")
                    )
                )
                size.assertContains(
                    Pair(
                        "456",
                        listOf("456")
                    )
                )
            }
        }

        @Test
        fun multipleSameKey() {
            val data = listOf("1234", "456")
            assertCallbackCalledWith(data) { expectedValue ->
                val size = data.toMap {
                    expectedValue(it)
                    "key"
                }
                size.assertSingle {
                    it.key.assert("key")
                    it.value.assertSize(2)
                    it.value.assertContainsInOrder("1234", "456")
                }
            }
        }
    }

    class CollectionTToMapKeyMapperValueMapper {
        @Test
        fun empty() {
            listOf<String>().toMap(
                keyMapper = {

                }, valueMapper = {

                }
            ).assertEmpty()
        }

        @Test
        fun single() {
            listOf("1234").toMap(
                keyMapper = {
                    it.assert("1234")
                    "key"
                }, valueMapper = {
                    it.assert("1234")
                    "value"
                }
            ).assertSingle {
                it.key.assert("key")
                it.value.assertSingle("value")
            }
        }

        @Test
        fun multipleDifferentKeys() {
            val data = listOf("1234", "456")
            assertCallbackCalledWith(data) { expectedKey ->
                assertCallbackCalledWith(data) { expectedValue ->
                    val size = data.toMap(
                        keyMapper = {
                            expectedKey(it)
                            it
                        }, valueMapper = {
                            expectedValue(it)
                            it
                        }
                    )
                    size.assertSize(2)
                    size.assertContains(
                        Pair(
                            "1234",
                            listOf("1234")
                        )
                    )
                    size.assertContains(
                        Pair(
                            "456",
                            listOf("456")
                        )
                    )
                }
            }
        }

        @Test
        fun multipleSameKey() {
            val data = listOf("1234", "456")
            assertCallbackCalledWith(data) { expectedKey ->
                assertCallbackCalledWith(data) { expectedValue ->
                    val size = data.toMap(
                        keyMapper = {
                            expectedKey(it)
                            "key"
                        }, valueMapper = {
                            expectedValue(it)
                            it
                        }
                    )
                    size.assertSingle {
                        it.key.assert("key")
                        it.value.assertSize(2)
                        it.value.assertContainsInOrder("1234", "456")
                    }
                }
            }
        }
    }

    class CollectionTToMutableMapKeyMapper {

        @Test
        fun empty() {
            listOf<String>().toMutableMap {
                shouldNotBeCalled()
            }.assertEmpty()
        }


        @Test
        fun single() {
            listOf("1234").toMutableMap {
                it.assert("1234")
                it
            }.assertSingle {
                it.key.assert("1234")
                it.value.assertSingle("1234")
            }
        }


        @Test
        fun multipleSameKey() {
            val data = listOf("1234", "abc")
            assertCallbackCalledWith(data) { expectedValue ->
                data.toMutableMap {
                    expectedValue(it)
                    "key"
                }.assertSingle {
                    it.key.assert("key")
                    it.value.assertSize(2)
                    it.value.assertContainsInOrder("1234", "abc")
                }
            }
        }

        @Test
        fun multipleDifferentKey() {
            val data = listOf("1234", "abc")
            assertCallbackCalledWith(data) { expectedValue ->
                val result = data.toMutableMap {
                    expectedValue(it)
                    it
                }
                result.assertSize(2)
                result.assertContains(
                    Pair(
                        "1234", listOf("1234")
                    )
                )
                result.assertContains(
                    Pair(
                        "abc", listOf("abc")
                    )
                )

            }
        }

    }

    class CollectionTToMutableMapKeyMapperValueMapper {

        @Test
        fun empty() {
            listOf<String>().toMutableMap(
                keyMapper = {
                    shouldNotBeCalled()
                },
                valueMapper = {
                    shouldNotBeCalled()
                }
            ).assertEmpty()
        }


        @Test
        fun single() {
            listOf("1234").toMutableMap(keyMapper = {
                it.assert("1234")
                "key"
            }, valueMapper = {
                it.assert("1234")
                "value"
            }).assertSingle {
                it.key.assert("key")
                it.value.assertSingle("value")
            }
        }


        @Test
        fun multipleSameKey() {
            val data = listOf("1234", "abc")
            assertCallbackCalledWith(data) { expectedValue ->
                data.toMutableMap(keyMapper = {
                    expectedValue(it)
                    "key"
                }, valueMapper = {
                    "$it+"
                }).assertSingle {
                    it.key.assert("key")
                    it.value.assertSize(2)
                    it.value.assertContainsInOrder("1234+", "abc+")
                }
            }
        }

        @Test
        fun multipleDifferentKey() {
            val data = listOf("1234", "abc")
            assertCallbackCalledWith(data) { expectedValue ->
                val result = data.toMutableMap(keyMapper = {
                    expectedValue(it)
                    it
                }, valueMapper = {
                    "$it+"
                })
                result.assertSize(2)
                result.assertContains(
                    Pair(
                        "1234", listOf("1234+")
                    )
                )
                result.assertContains(
                    Pair(
                        "abc", listOf("abc+")
                    )
                )

            }
        }

    }


    //endregion

    class CollectionTToUniqueMutableMap {

        @Test
        fun empty() {
            listOf<String>().toUniqueMutableMap(
                keyMapper = { shouldNotBeCalled() },
                valueMapper = { shouldNotBeCalled() },
                onKeyCollision = { _, _ -> shouldNotBeCalled() }
            )
        }


        @Test
        fun single() {
            listOf("0").toUniqueMutableMap(
                keyMapper = { "key" },
                valueMapper = { "-$it-" },
                onKeyCollision = { _, _ -> shouldNotBeCalled() }
            ).assertSingle {
                it.key.assert("key")
                it.value.assert("-0-")
            }
        }


        @Test
        fun multipleCollision() {
            listOf("0", "0").toUniqueMutableMap(
                keyMapper = { "key" },
                valueMapper = { "-$it-" },
                onKeyCollision = { first, second ->
                    first.assert("-0-")
                    second.assert("-0-")
                    "test"
                }
            ).assertSingle {
                it.key.assert("key")
                it.value.assert("test")
            }
        }
    }

    class CollectionTToUniqueMap {
        @Test
        fun empty() {
            val map = listOf<String>().toUniqueMap(
                { shouldNotBeCalled() },
                { shouldNotBeCalled() },
                { _, _ -> shouldNotBeCalled() }
            )
            map.assertEmpty()
        }


        @Test
        fun single() {
            val map = listOf(Pair("a", "b")).toUniqueMap(
                { it.first },
                { it.second },
                { _, _ -> shouldNotBeCalled() }
            )
            map.assertSingle("a" to "b")
        }


        @Test
        fun multipleNonColliding() {
            val map = listOf(Pair("a", "b")).toUniqueMap(
                { it.first },
                { it.second },
                { _, _ -> shouldNotBeCalled() }
            )
            map.assertSingle("a" to "b")
        }

        @Test
        fun multipleWithColliding() {
            val map = listOf(Pair("a", "b")).toUniqueMap(
                { it.first },
                { it.second },
                { _, _ -> shouldNotBeCalled() }
            )
            map.assertSingle("a" to "b")
        }

    }
}
