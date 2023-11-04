package csense.kotlin.specificExtensions.collections.collection.categorization

import csense.kotlin.extensions.primitives.char.*
import csense.kotlin.tests.assertions.*
import kotlin.test.*

class IntoTest {
    @Test
    fun categorizeIntoMultiple() {
        val someData: List<String> = listOf("a", "1", "qwerty", "cow", "blue", "red", "cat", "3", "b")
        //try filter only single characters and single numbers
        val (singleChars: List<String>, singleDigits: List<String>) = someData.categorization.categorizeIntoMultiple({ it: String ->
            it.length == 1 && it.first().asDigit() == null
        }, { it: String ->
            it.length == 1 && it.first().asDigit() != null
        })
        singleChars.assertSize(2)
        singleDigits.assertSize(2)

        val (all: List<String>, colors: List<String>) = someData.categorization.categorizeIntoMultiple({ _: String ->
            true
        }, { it: String ->
            setOf("blue", "red").contains(it)
        })

        all.assertSize(9)
        colors.assertSize(2)
        val (all1: List<String>, all2: List<String>, all3: List<String>) = someData.categorization.categorizeIntoMultiple(
            { true },
            { true },
            { true }
        )

        all1.assertSize(9)
        all2.assertSize(9)
        all3.assertSize(9)
        val (falseAll1: List<String>, falseAll2: List<String>) = someData.categorization.categorizeIntoMultiple(
            { false },
            { false }
        )
        falseAll1.assertSize(0)
        falseAll2.assertSize(0)
    }

    @Test
    fun categorizeIntoSingle() {
        val someData: List<String> = listOf("a", "1", "qwerty", "cow", "blue", "red", "cat", "3", "b")
        //try filter only single characters and single numbers
        val (singleChars: List<String>, singleDigits: List<String>) = someData.categorization.categorizeIntoSingle({ it: String ->
            it.length == 1 && it.first().asDigit() == null
        }, { it: String ->
            it.length == 1 && it.first().asDigit() != null
        })
        singleChars.assertSize(2)
        singleDigits.assertSize(2)

        val (all: List<String>, colors: List<String>) = someData.categorization.categorizeIntoSingle({ _: String ->
            true
        }, { _: String ->
            failTest("should never get called, since the other filter will eat this")
        })

        all.assertSize(9)
        colors.assertSize(0, "since all eats the item")
        val (all1: List<String>, all2: List<String>, all3: List<String>) = someData.categorization.categorizeIntoSingle(
            { true },
            { failTest() },
            { failTest() }
        )

        all1.assertSize(9)
        all2.assertSize(0)
        all3.assertSize(0)
        val (falseAll1: List<String>, falseAll2: List<String>) = someData.categorization.categorizeIntoSingle(
            { false },
            { false }
        )
        falseAll1.assertSize(0)
        falseAll2.assertSize(0)

        val (falseAll11: List<String>, falseAll21: List<String>, trueAll21: List<String>) = someData.categorization.categorizeIntoSingle(
            { false },
            { false },
            { true }
        )
        falseAll11.assertSize(0)
        falseAll21.assertSize(0)
        trueAll21.assertSize(9)

    }


    class CollectionItemCategorizeIntoFilters {
        @Test
        fun emptyInputEmptyFilters() {
            listOf<String>().categorization.categorizeInto().assertEmpty()
        }

        @Test
        fun emptyInputWithFilters() {
            listOf<String>().categorization.categorizeInto({ true }).assertSingle { it: List<String> ->
                it.isEmpty()
            }
            listOf<String>().categorization.categorizeInto({ true }, { true }, allowItemInMultipleBuckets = false)
                .apply {
                    assertSize(2)
                    first().assertEmpty()
                    last().assertEmpty()
                }
            listOf<String>().categorization.categorizeInto({ true }, { true }, allowItemInMultipleBuckets = true)
                .apply {
                    assertSize(2)
                    first().assertEmpty()
                    last().assertEmpty()
                }
        }

        @Test
        fun singleInputNoFilters() {
            listOf("test").categorization.categorizeInto().assertEmpty()
        }

        @Test
        fun singleInputWithFilters() {
            listOf("test").categorization.categorizeInto({ false }).assertSingle { it: List<String> ->
                it.isEmpty()
            }
            listOf("test").categorization.categorizeInto({ true }).assertSingle {
                it.assertSingle("test")
            }
            listOf("test").categorization.categorizeInto({ false }, { false }, allowItemInMultipleBuckets = false)
                .apply {
                    assertSize(2)
                    first().assertEmpty()
                    last().assertEmpty()
                }
            listOf("test").categorization.categorizeInto({ true }, { true }, allowItemInMultipleBuckets = false).apply {
                assertSize(2)
                first().assertSingle("test")
                last().assertEmpty("since \"allowItemInMultipleBuckets\" is false")
            }
            listOf("test").categorization.categorizeInto({ true }, { true }, allowItemInMultipleBuckets = true).apply {
                assertSize(2)
                first().assertSingle("test")
                last().assertSingle("test")
            }
        }

        @Test
        fun mixed() {
            listOf("test", "abc").categorization.categorizeInto(
                { true },
                { false },
                { true },
                allowItemInMultipleBuckets = false
            )
                .apply {
                    assertSize(3)
                    this[0].apply {
                        assertSize(2)
                        assertContainsInOrder("test", "abc")
                    }
                    this[1].assertEmpty("")
                    this[2].assertEmpty("\"allowItemInMultipleBuckets\" = false")
                }
            listOf("test", "123").categorization.categorizeInto(
                { true },
                { false },
                { true },
                allowItemInMultipleBuckets = true
            )
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
}