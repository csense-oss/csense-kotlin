package csense.kotlin.extensions.primitives

import csense.kotlin.extensions.primitives.char.*
import csense.kotlin.extensions.primitives.charSequence.*
import csense.kotlin.tests.assertions.*
import kotlin.test.*
import kotlin.text.split

class CharSequenceTest {

    @Test
    fun isNotNullOrBlank() {
        val nullStr: String? = null
        nullStr.isNotNullOrBlank().assert(false)

        @Suppress("RedundantNullableReturnType")
        val empty: String? = ""
        empty.isNotNullOrBlank().assert(false)

        @Suppress("RedundantNullableReturnType")
        val singleSpace: String? = " "
        singleSpace.isNotNullOrBlank().assert(false)

        @Suppress("RedundantNullableReturnType")
        val testString: String? = "test"
        testString.isNotNullOrBlank().assert(true)
    }

    @Test
    fun isNotNullOrEmpty() {
        val nullStr: String? = null
        nullStr.isNotNullOrEmpty().assertFalse()

        @Suppress("RedundantNullableReturnType")
        val empty: String? = ""
        empty.isNotNullOrEmpty().assertFalse()

        @Suppress("RedundantNullableReturnType")
        val singleSpace: String? = " "
        singleSpace.isNotNullOrEmpty().assertTrue()

        @Suppress("RedundantNullableReturnType")
        val testString: String? = "test"
        testString.isNotNullOrEmpty().assertTrue()
    }

    @Test
    fun charSequenceIndexOfOrNullChar() {
        "".indexOfOrNull('0').assertNull()
        "a".indexOfOrNull('0').assertNull()
        "0".indexOfOrNull('0').assert(0)
        "A".indexOfOrNull('a').assertNull()
        "A".indexOfOrNull('a', ignoreCase = false).assertNull()
        "A".indexOfOrNull('a', ignoreCase = true).assert(0)
        "0A".indexOfOrNull('a', ignoreCase = true).assert(1)
        "0A0A".indexOfOrNull('a', ignoreCase = true).assert(1)
        "0A0A".indexOfOrNull('a', ignoreCase = true, startIndex = 2).assert(3)
        "0a0A".indexOfOrNull('a', ignoreCase = false, startIndex = 2).assertNull()
        "0a0A".indexOfOrNull('A', ignoreCase = false, startIndex = 2).assert(3)
    }

    @Test
    fun charSequenceIndexOfOrNullString() {
        "".indexOfOrNull("").assert(0)
        "".indexOfOrNull("a").assertNull()
        "0".indexOfOrNull("a").assertNull()
        "0".indexOfOrNull("0").assert(0)
        "a".indexOfOrNull("a").assert(0)
        "a".indexOfOrNull("A").assertNull()
        "a".indexOfOrNull("A", ignoreCase = true).assert(0)
        "abC".indexOfOrNull("Abc", ignoreCase = true).assert(0)
        "abc".indexOfOrNull("ABC", ignoreCase = false).assertNull()
        "0abC".indexOfOrNull("Abc", ignoreCase = true).assert(1)
        "aABC".indexOfOrNull("ABC", ignoreCase = false).assert(1)
        "abCABC".indexOfOrNull("ABC", ignoreCase = false).assert(3)

    }

    @Test
    fun charSequenceLastIndexOfOrNullChar() {
        "".lastIndexOfOrNull('0').assertNull()
        "a".lastIndexOfOrNull('0').assertNull()
        "0".lastIndexOfOrNull('0').assert(0)
        "A".lastIndexOfOrNull('a').assertNull()
        "A".lastIndexOfOrNull('a', ignoreCase = false).assertNull()
        "A".lastIndexOfOrNull('a', ignoreCase = true).assert(0)
        "0A".lastIndexOfOrNull('a', ignoreCase = true).assert(1)
        "0A0A".lastIndexOfOrNull('a', ignoreCase = true).assert(3)
        "0A0A".lastIndexOfOrNull('a', ignoreCase = true, startIndex = 1).assert(1)
        "0A0a".lastIndexOfOrNull('a', ignoreCase = false, startIndex = 1).assertNull()
        "0A0a".lastIndexOfOrNull('A', ignoreCase = false, startIndex = 1).assert(1)
    }

    @Test
    fun charSequenceLastIndexOfOrNullString() {
        "".lastIndexOfOrNull("0").assertNull()
        "a".lastIndexOfOrNull("0").assertNull()
        "0".lastIndexOfOrNull("0").assert(0)
        "A".lastIndexOfOrNull("a").assertNull()
        "A".lastIndexOfOrNull("a", ignoreCase = false).assertNull()
        "A".lastIndexOfOrNull("a", ignoreCase = true).assert(0)
        "0A".lastIndexOfOrNull("a", ignoreCase = true).assert(1)
        "0A0A".lastIndexOfOrNull("a", ignoreCase = true).assert(3)
        "0A0A".lastIndexOfOrNull("a", ignoreCase = true, startIndex = 1).assert(1)
        "0A0a".lastIndexOfOrNull("a", ignoreCase = false, startIndex = 1).assertNull()
        "0A0a".lastIndexOfOrNull("A", ignoreCase = false, startIndex = 1).assert(1)

    }


    class CharSequenceEquals {
        @Test
        fun empty() {
            val first: CharSequence = ""
            val second: CharSequence = ""
            first.equals(second, false).assertTrue()
            first.equals(second, true).assertTrue()
        }

        @Test
        fun singleSameCase() {
            val first: CharSequence = "a"
            val second: CharSequence = "a"
            first.equals(second, false).assertTrue()
            first.equals(second, true).assertTrue()
        }

        @Test
        fun singleDifferentCase() {
            val first: CharSequence = "a"
            val second: CharSequence = "A"
            first.equals(second, false).assertFalse()
            first.equals(second, true).assertTrue()
        }

        @Test
        fun singleNumber() {
            val first: CharSequence = "5"
            val second: CharSequence = "5"
            first.equals(second, false).assertTrue()
            first.equals(second, true).assertTrue()
        }

        @Test
        fun differentLength() {
            val first: CharSequence = "50"
            val second: CharSequence = "5"
            first.equals(second, false).assertFalse()
            first.equals(second, true).assertFalse()
        }


    }


    class CharSequenceNotEquals {
        @Test
        fun empty() {
            val first: CharSequence = ""
            val second: CharSequence = ""
            first.notEquals(second, false).assertFalse()
            first.notEquals(second, true).assertFalse()
        }

        @Test
        fun singleSameCase() {
            val first: CharSequence = "a"
            val second: CharSequence = "a"
            first.notEquals(second, false).assertFalse()
            first.notEquals(second, true).assertFalse()
        }

        @Test
        fun singleDifferentCase() {
            val first: CharSequence = "a"
            val second: CharSequence = "A"
            first.notEquals(second, false).assertTrue()
            first.notEquals(second, true).assertFalse()
        }

        @Test
        fun singleNumber() {
            val first: CharSequence = "5"
            val second: CharSequence = "5"
            first.notEquals(second, false).assertFalse()
            first.notEquals(second, true).assertFalse()
        }

        @Test
        fun differentLength() {
            val first: CharSequence = "50"
            val second: CharSequence = "5"
            first.notEquals(second, false).assertTrue()
            first.notEquals(second, true).assertTrue()
        }

    }

    class CharSequenceSplitBy {
        @Test
        fun alwaysSplitEmpty() {
            "".splitBy { true }.assertEmpty("nothing cannot be split")
        }

        @Test
        fun alwaysSplitSingle() {
            "a".splitBy { true }.apply {
                assertEmpty("since we do not include the split")
            }
        }

        @Test
        fun alwaysSplitMultiple() {
            "ab".splitBy { true }.apply {
                assertEmpty("since we do not include the split")
            }
        }

        @Test
        fun splitMultipleDigits() {
            "20/80 8000".splitBy { it.isNotDigit() }.apply {
                assertSize(3)
                this[0].assert("20")
                this[1].assert("80")
                this[2].assert("8000")
            }
        }

        @Test
        fun splitMultipleDigitsWeird() {
            "20/80 abc 8000".splitBy { it.isNotDigit() }.apply {
                assertSize(3)
                this[0].assert("20")
                this[1].assert("80")
                this[2].assert("8000")
            }
        }

    }


    class CharSequenceSplitDelimiters {
        @Test
        fun empty() {
            val result = "abc".split(setOf())
            result.assertSingle("abc")
        }

        @Test
        fun singleTakesSplit() {
            val result = "abc".split(setOf('a'))
            result.first().assert("bc")
            result.assertSize(1)
        }

        @Test
        fun singleInMiddle() {
            val result = "abc".split(setOf('b'))
            result.assertSize(2)
            result.first().assert("a")
            result.last().assert("c")
        }

        @Test
        fun multipleAtEnds() {
            val result = "abc1234".split(setOf('a', '4'))
            result.assertSize(1)
            result[0].assert("bc123")
        }

        @Test
        fun multipleAtMiddle() {
            val result = "abc1234".split(setOf('b', '3'))
            result.assertSize(3)
            result[0].assert("a")
            result[1].assert("c12")
            result[2].assert("4")
        }

        @Test
        fun shouldBehaveAsRegularSplit() {
            val regular = "123a123".split('a')
            val bySet = "123a123".split(setOf('a'))
            regular.assertSize(bySet.size)
            regular.assertContainsInOrder(regular)
        }
    }


    class SubstringOrNullStartIndex {
        @Test
        fun empty() {
            "".substringOrNull(-1).assertNull()
            "".substringOrNull(0).assertNull()
            "".substringOrNull(1).assertNull()
        }

        @Test
        fun single() {
            "a".substringOrNull(-1).assertNull()
            "a".substringOrNull(0).assert("a")
            "a".substringOrNull(1).assertNull()
        }

        @Test
        fun multiple() {
            "abc".substringOrNull(-1).assertNull()
            "abc".substringOrNull(0).assert("abc")
            "abc".substringOrNull(1).assert("bc")
            "abc".substringOrNull(2).assert("c")
            "abc".substringOrNull(3).assertNull()
        }
    }

    class SubstringOrNullStartIndexEndIndex {
        @Test
        fun empty() {
            "".substringOrNull(startIndex = -1, endIndex = 0).assertNull()
            "".substringOrNull(startIndex = 0, endIndex = 0).assertNull()
            "".substringOrNull(startIndex = 1, endIndex = 0).assertNull()

            "".substringOrNull(startIndex = 0, endIndex = -1).assertNull()
            "".substringOrNull(startIndex = 0, endIndex = 0).assertNull()
            "".substringOrNull(startIndex = 0, endIndex = 1).assertNull()
        }

        @Test
        fun single() {
            "a".substringOrNull(startIndex = -1, endIndex = 0).assertNull()
            "a".substringOrNull(startIndex = 0, endIndex = 0).assertNull()
            "a".substringOrNull(startIndex = 1, endIndex = 0).assertNull()

            "a".substringOrNull(startIndex = 0, endIndex = -1).assertNull()
            "a".substringOrNull(startIndex = 0, endIndex = 0).assertNull()
            "a".substringOrNull(startIndex = 0, endIndex = 1).assert("a")
            "a".substringOrNull(startIndex = 0, endIndex = 2).assertNull("is out of bounds")
        }

        @Test
        fun multiple() {
            "abc".substringOrNull(startIndex = -1, endIndex = 0).assertNull()

            "abc".substringOrNull(startIndex = 0, endIndex = -1).assertNull()
            "abc".substringOrNull(startIndex = 0, endIndex = 0).assertNull()
            "abc".substringOrNull(startIndex = 0, endIndex = 1).assert("a")
            "abc".substringOrNull(startIndex = 0, endIndex = 2).assert("ab")
            "abc".substringOrNull(startIndex = 0, endIndex = 3).assert("abc")
            "abc".substringOrNull(startIndex = 0, endIndex = 4).assertNull()

            "abc".substringOrNull(startIndex = 1, endIndex = 1).assertNull()
            "abc".substringOrNull(startIndex = 1, endIndex = 2).assert("b")
            "abc".substringOrNull(startIndex = 1, endIndex = 3).assert("bc")
            "abc".substringOrNull(startIndex = 1, endIndex = 4).assertNull()

            "abc".substringOrNull(startIndex = 2, endIndex = 1).assertNull()
            "abc".substringOrNull(startIndex = 2, endIndex = 2).assertNull()
            "abc".substringOrNull(startIndex = 2, endIndex = 3).assert("c")
            "abc".substringOrNull(startIndex = 2, endIndex = 4).assertNull()
        }
    }

    class SubstringOrNullRange {
        @Test
        fun empty() {
            "".substringOrNull(IntRange(start = -1, endInclusive = 0)).assertNull()
            "".substringOrNull(IntRange(start = 0, endInclusive = 0)).assertNull()
            "".substringOrNull(IntRange(start = 1, endInclusive = 0)).assertNull()

            "".substringOrNull(IntRange(start = 0, endInclusive = -1)).assertNull()
            "".substringOrNull(IntRange(start = 0, endInclusive = 0)).assertNull()
            "".substringOrNull(IntRange(start = 0, endInclusive = 1)).assertNull()
        }

        @Test
        fun single() {
            "a".substringOrNull(IntRange(start = -1, endInclusive = 0)).assertNull()
            "a".substringOrNull(IntRange(start = 0, endInclusive = 0)).assert("a")
            "a".substringOrNull(IntRange(start = 1, endInclusive = 0)).assertNull()

            "a".substringOrNull(IntRange(start = 0, endInclusive = -1)).assertNull()
            "a".substringOrNull(IntRange(start = 0, endInclusive = 0)).assert("a")
            "a".substringOrNull(IntRange(start = 0, endInclusive = 1)).assertNull("is out of bounds")
            "a".substringOrNull(IntRange(start = 0, endInclusive = 2)).assertNull("is out of bounds")
        }

        @Test
        fun multiple() {
            "abc".substringOrNull(IntRange(start = -1, endInclusive = 0)).assertNull()

            "abc".substringOrNull(IntRange(start = 0, endInclusive = -1)).assertNull()
            "abc".substringOrNull(IntRange(start = 0, endInclusive = 0)).assert("a")
            "abc".substringOrNull(IntRange(start = 0, endInclusive = 1)).assert("ab")
            "abc".substringOrNull(IntRange(start = 0, endInclusive = 2)).assert("abc")
            "abc".substringOrNull(IntRange(start = 0, endInclusive = 3)).assertNull()
            "abc".substringOrNull(IntRange(start = 0, endInclusive = 4)).assertNull()

            "abc".substringOrNull(IntRange(start = 1, endInclusive = 1)).assert("b")
            "abc".substringOrNull(IntRange(start = 1, endInclusive = 2)).assert("bc")
            "abc".substringOrNull(IntRange(start = 1, endInclusive = 3)).assertNull()
            "abc".substringOrNull(IntRange(start = 1, endInclusive = 4)).assertNull()

            "abc".substringOrNull(IntRange(start = 2, endInclusive = 1)).assertNull()
            "abc".substringOrNull(IntRange(start = 2, endInclusive = 2)).assert("c")
            "abc".substringOrNull(IntRange(start = 2, endInclusive = 3)).assertNull()
            "abc".substringOrNull(IntRange(start = 2, endInclusive = 4)).assertNull()
        }
    }

    class CharSequenceIndexOfFirstIndexedOrNull {
        @Test
        fun empty() {
            "".indexOfFirstIndexedOrNull { _, _ -> shouldNotBeCalled() }.assertNull()
        }

        @Test
        fun singleNotFound() = assertCalled { shouldBeCalled ->
            "a".indexOfFirstIndexedOrNull { i, c ->
                i.assert(0)
                c.assert('a')
                shouldBeCalled()
                false
            }.assertNull()
        }

        @Test
        fun singleFound() = assertCalled { shouldBeCalled ->
            "a".indexOfFirstIndexedOrNull { i, c ->
                i.assert(0)
                c.assert('a')
                shouldBeCalled()
                true
            }.assert(0)
        }

        @Test
        fun multipleNotFound() = assertCalled(times = 2) { shouldBeCalled ->
            val testData = listOf(0 to 'a', 1 to 'b')
            "ab".indexOfFirstIndexedOrNull { i, c ->
                testData[i].first.assert(i)
                testData[i].second.assert(c)
                shouldBeCalled()
                false
            }.assertNull()
        }

        @Test
        fun multipleFirstFound() = assertCalled { shouldBeCalled ->
            val testData = listOf(0 to 'a', 1 to 'b')
            "ab".indexOfFirstIndexedOrNull { i, c ->
                testData[i].first.assert(i)
                testData[i].second.assert(c)
                shouldBeCalled()
                true
            }.assert(0)
        }

        @Test
        fun multipleLastFound() = assertCalled(times = 2) { shouldBeCalled ->
            val testData = listOf(0 to 'a', 1 to 'b')
            "ab".indexOfFirstIndexedOrNull { i, c ->
                testData[i].first.assert(i)
                testData[i].second.assert(c)
                shouldBeCalled()
                i == 1
            }.assert(1)
        }

    }

    class CharSequenceIndexOfFirstOrNull {

        @Test
        fun empty() {
            "".indexOfFirstOrNull { shouldNotBeCalled() }.assertNull()
        }


        @Test
        fun singleNotFound() = assertCalled { shouldBeCalled ->
            "a".indexOfFirstOrNull {
                it.assert('a')
                shouldBeCalled()
                false
            }.assertNull()
        }

        @Test
        fun singleFound() = assertCalled { shouldBeCalled ->
            "a".indexOfFirstOrNull {
                it.assert('a')
                shouldBeCalled()
                true
            }.assert(0)
        }

        @Test
        fun multipleFirstFound() = assertCalled { shouldBeCalled ->
            "abc".indexOfFirstOrNull {
                shouldBeCalled()
                it.assert('a')
                true
            }.assert(0)
        }

        @Test
        fun multipleLastFound() = assertCalled(times = 3) { shouldBeCalled ->
            assertCallbackCalledWith(listOf('a', 'b', 'c')) { assertChar ->
                "abc".indexOfFirstOrNull {
                    assertChar(it)
                    shouldBeCalled()
                    it == 'c'
                }.assert(2)
            }
        }

    }

    class CharSequenceIndexOfLastOrNull {

        @Test
        fun nonFound() {
            "abc".indexOfLastOrNull { false }.assertNull()
            assertCalled {
                "a".indexOfLastOrNull { it.assert('a');it(); false }.assertNull()
            }
            "abc".indexOfLastOrNull { it == '1' }.assertNull()
        }

        @Test
        fun singleFoundAtStart() {
            assertCalled { shouldBeCalled ->
                "a".indexOfLastOrNull {
                    it.assert('a')
                    shouldBeCalled()
                    true
                }.assert(0)
            }
        }


        @Test
        fun singleFoundAtEnd() {
            assertCalled { shouldBeCalled ->
                "abc".indexOfLastOrNull {
                    it.assert('c')
                    shouldBeCalled()
                    true
                }.assert(2)
            }
        }


        @Test
        fun multipleShouldSelectLatest() {
            assertCalled { shouldBeCalled ->
                "cabc".indexOfLastOrNull {
                    it.assert('c')
                    shouldBeCalled()
                    true
                }.assert(3, message = "should be at end")
            }

            assertCalled(times = 2) { shouldBeCalled ->
                "bcbabc".indexOfLastOrNull {
                    shouldBeCalled()
                    it == 'b'
                }.assert(4)
            }
        }
    }

    class CharSequenceIndexOfLastIndexedOrNull {

        @Test
        fun nonFound() {
            "abc".indexOfLastIndexedOrNull { _, _ ->
                false
            }.assertNull()
            assertCalled { shouldBeCalled ->
                "a".indexOfLastIndexedOrNull { index, it ->
                    it.assert('a')
                    index.assert(0)
                    shouldBeCalled()
                    false
                }.assertNull()
            }
            assertCalled(times = 3) { shouldBeCalled ->
                "abc".indexOfLastIndexedOrNull { _, it ->
                    shouldBeCalled()
                    it == '1'
                }.assertNull()
            }
        }

        @Test
        fun singleFoundAtStart() {
            assertCalled { shouldBeCalled ->
                "a".indexOfLastIndexedOrNull { index, char ->
                    char.assert('a')
                    index.assert(0)
                    shouldBeCalled()
                    true
                }.assert(0)
            }
        }


        @Test
        fun singleFoundAtEnd() {
            assertCalled { shouldBeCalled ->
                "abc".indexOfLastIndexedOrNull { index, char ->
                    char.assert('c')
                    index.assert(2)
                    shouldBeCalled()
                    true
                }.assert(2)
            }
        }


        @Test
        fun multipleShouldSelectLatest() {
            assertCalled { shouldBeCalled ->
                "cabc".indexOfLastIndexedOrNull { index, char ->
                    char.assert('c')
                    index.assert(3)
                    shouldBeCalled()
                    true
                }.assert(3, message = "should be at end")
            }

            assertCalled(times = 2) { shouldBeCalled ->
                "bcbabc".indexOfLastIndexedOrNull { _, char ->
                    shouldBeCalled()
                    char == 'b'
                }.assert(4)
            }
        }
    }
}