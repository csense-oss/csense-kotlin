package csense.kotlin.extensions.primitives

import csense.kotlin.tests.assertions.*
import kotlin.test.Test

class CharSequenceTest {

    @Test
    fun isNotNullOrBlank() {
        val nullStr: String? = null
        nullStr.isNotNullOrBlank().assert(false)
        "".isNotNullOrBlank().assert(false)
        " ".isNotNullOrBlank().assert(false)
        "test".isNotNullOrBlank().assert(true)
    }

    @Test
    fun isNotNullOrEmpty() {
        val nullStr: String? = null
        nullStr.isNotNullOrEmpty()
        "".isNotNullOrEmpty().assert(false)
        " ".isNotNullOrEmpty().assert(true)
        "test".isNotNullOrEmpty().assert(true)
    }

    @Test
    fun charSequenceIndexOfOrNullChar() {
        "".indexOfOrNull('0').assertNull()
        "a".indexOfOrNull('0').assertNull()
        "0".indexOfOrNull('0').assertNotNullAndEquals(0)
        "A".indexOfOrNull('a').assertNull()
        "A".indexOfOrNull('a', ignoreCase = false).assertNull()
        "A".indexOfOrNull('a', ignoreCase = true).assertNotNullAndEquals(0)
        "0A".indexOfOrNull('a', ignoreCase = true).assertNotNullAndEquals(1)
        "0A0A".indexOfOrNull('a', ignoreCase = true).assertNotNullAndEquals(1)
        "0A0A".indexOfOrNull('a', ignoreCase = true, startIndex = 2).assertNotNullAndEquals(3)
        "0a0A".indexOfOrNull('a', ignoreCase = false, startIndex = 2).assertNull()
        "0a0A".indexOfOrNull('A', ignoreCase = false, startIndex = 2).assertNotNullAndEquals(3)
    }

    @Test
    fun charSequenceIndexOfOrNullString() {
        "".indexOfOrNull("").assertNotNullAndEquals(0)
        "".indexOfOrNull("a").assertNull()
        "0".indexOfOrNull("a").assertNull()
        "0".indexOfOrNull("0").assertNotNullAndEquals(0)
        "a".indexOfOrNull("a").assertNotNullAndEquals(0)
        "a".indexOfOrNull("A").assertNull()
        "a".indexOfOrNull("A", ignoreCase = true).assertNotNullAndEquals(0)
        "abC".indexOfOrNull("Abc", ignoreCase = true).assertNotNullAndEquals(0)
        "abc".indexOfOrNull("ABC", ignoreCase = false).assertNull()
        "0abC".indexOfOrNull("Abc", ignoreCase = true).assertNotNullAndEquals(1)
        "aABC".indexOfOrNull("ABC", ignoreCase = false).assertNotNullAndEquals(1)
        "abCABC".indexOfOrNull("ABC", ignoreCase = false).assertNotNullAndEquals(3)

    }

    @Test
    fun charSequenceLastIndexOfOrNullChar() {
        "".lastIndexOfOrNull('0').assertNull()
        "a".lastIndexOfOrNull('0').assertNull()
        "0".lastIndexOfOrNull('0').assertNotNullAndEquals(0)
        "A".lastIndexOfOrNull('a').assertNull()
        "A".lastIndexOfOrNull('a', ignoreCase = false).assertNull()
        "A".lastIndexOfOrNull('a', ignoreCase = true).assertNotNullAndEquals(0)
        "0A".lastIndexOfOrNull('a', ignoreCase = true).assertNotNullAndEquals(1)
        "0A0A".lastIndexOfOrNull('a', ignoreCase = true).assertNotNullAndEquals(3)
        "0A0A".lastIndexOfOrNull('a', ignoreCase = true, startIndex = 1).assertNotNullAndEquals(1)
        "0A0a".lastIndexOfOrNull('a', ignoreCase = false, startIndex = 1).assertNull()
        "0A0a".lastIndexOfOrNull('A', ignoreCase = false, startIndex = 1).assertNotNullAndEquals(1)
    }

    @Test
    fun charSequenceLastIndexOfOrNullString() {
        "".lastIndexOfOrNull("0").assertNull()
        "a".lastIndexOfOrNull("0").assertNull()
        "0".lastIndexOfOrNull("0").assertNotNullAndEquals(0)
        "A".lastIndexOfOrNull("a").assertNull()
        "A".lastIndexOfOrNull("a", ignoreCase = false).assertNull()
        "A".lastIndexOfOrNull("a", ignoreCase = true).assertNotNullAndEquals(0)
        "0A".lastIndexOfOrNull("a", ignoreCase = true).assertNotNullAndEquals(1)
        "0A0A".lastIndexOfOrNull("a", ignoreCase = true).assertNotNullAndEquals(3)
        "0A0A".lastIndexOfOrNull("a", ignoreCase = true, startIndex = 1).assertNotNullAndEquals(1)
        "0A0a".lastIndexOfOrNull("a", ignoreCase = false, startIndex = 1).assertNull()
        "0A0a".lastIndexOfOrNull("A", ignoreCase = false, startIndex = 1).assertNotNullAndEquals(1)

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
                assertSize(0, "since we do not include the split")
            }
        }

        @Test
        fun alwaysSplitMultiple() {
            "ab".splitBy { true }.apply {
                assertSize(0, "since we do not include the split")
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


    class CharSequenceSplitDelimiters() {
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
            //todo csense tests -> assertContentAndOrder

            regular.forEachIndexed { index, s ->
                bySet[index].assert(s)
            }
        }

    }
}