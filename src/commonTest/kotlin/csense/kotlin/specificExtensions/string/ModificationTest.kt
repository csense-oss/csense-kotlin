package csense.kotlin.specificExtensions.string

import csense.kotlin.tests.assertions.*
import kotlin.test.*

class ModificationTest {
    @Test
    fun stringModificationLimitTo() {
        "".modifications.limitTo(0).assert("")
        "".modifications.limitTo(-1).assert("")
        "".modifications.limitTo(1).assert("")
        "abc".modifications.limitTo(0).assert("")
        "abc".modifications.limitTo(-2).assert("")
        "abc".modifications.limitTo(2).assert("ab")
        "abc".modifications.limitTo(1).assert("a")
        "abc".modifications.limitTo(3).assert("abc")
        "abc".modifications.limitTo(10).assert("abc")
    }

    @Test
    fun stringModificationWrapIn() {
        "".modifications.wrapIn("", "").assert("")
        "".modifications.wrapIn("aa", "").assert("aa")
        "".modifications.wrapIn("", "bb").assert("bb")
        "".modifications.wrapIn("a", "b").assert("ab")
        "1234".modifications.wrapIn("<", ">").assert("<1234>")
        "1234".modifications.wrapIn("<", "").assert("<1234")
        "1234".modifications.wrapIn("", ">").assert("1234>")
        "1234".modifications.wrapIn("", "").assert("1234")

    }

    @Test
    fun stringModificationWrapInQuotes() {
        "".modifications.wrapInQuotes().assert("\"\"")
        "a".modifications.wrapInQuotes().assert("\"a\"")
        "\"".modifications.wrapInQuotes().assert("\"\"\"")
        "yzz x".modifications.wrapInQuotes().assert("\"yzz x\"")
    }

    @Test
    fun stringModifications() {
        val empty = "".modifications
        val single = "a".modifications
        empty.string.assert("")
        single.string.assert("a")
        assertNotEquals(empty, single)

    }


    class StringModificationReplaceCharAtOrNull {

        @Test
        fun empty() {
            "".modifications.replaceCharAtOrNull(index = 0, withChar = 'a').assertNull()
            "".modifications.replaceCharAtOrNull(index = -1, withChar = 'a').assertNull()
            "".modifications.replaceCharAtOrNull(index = -1, withChar = 'a').assertNull()
        }

        @Test
        fun single() {
            " ".modifications.replaceCharAtOrNull(index = 0, withChar = 'a').assert("a")
            "a".modifications.replaceCharAtOrNull(index = 0, withChar = '1').assert("1")
            "b".modifications.replaceCharAtOrNull(index = 1, withChar = 'a').assertNull()
        }

        @Test
        fun multiple() {
            "abc".modifications.replaceCharAtOrNull(index = -1, withChar = 'q').assertNull()
            "abc".modifications.replaceCharAtOrNull(index = 0, withChar = 'q').assert("qbc")
            "abc".modifications.replaceCharAtOrNull(index = 1, withChar = 'q').assert("aqc")
            "abc".modifications.replaceCharAtOrNull(index = 2, withChar = 'q').assert("abq")
            "abc".modifications.replaceCharAtOrNull(index = 3, withChar = 'q').assertNull()

        }

    }

    class StringModificationSplitAtOrNull {
        @Test
        fun empty() {
            "".modifications.splitAtOrNull(-1).assertNull()
            "".modifications.splitAtOrNull(0).assertNull()
            "".modifications.splitAtOrNull(1).assertNull()
        }

        @Test
        fun single() {
            "a".modifications.splitAtOrNull(-1).assertNull()
            "a".modifications.splitAtOrNull(0).assertNotNullApply {
                beforeIndex.assertEmpty()
                afterIndex.assertEmpty()
            }
            "a".modifications.splitAtOrNull(1).assertNull()
        }

        @Test
        fun multiple() {
            "abc".modifications.splitAtOrNull(-1).assertNull()
            "abc".modifications.splitAtOrNull(0).assertNotNullApply {
                beforeIndex.assertEmpty()
                afterIndex.assert("bc")
            }
            "abc".modifications.splitAtOrNull(1).assertNotNullApply {
                beforeIndex.assert("a")
                afterIndex.assert("c")
            }
            "abc".modifications.splitAtOrNull(2).assertNotNullApply {
                beforeIndex.assert("ab")
                afterIndex.assertEmpty()
            }
            "abc".modifications.splitAtOrNull(3).assertNull()

        }
    }

    class StringModificationReplaceCharAt {

        @Test
        fun empty() = assertThrows<IndexOutOfBoundsException> {
            "".modifications.replaceCharAt(0, 'a')
        }


        @Test
        fun singleInvalidIndexNegative() = assertThrows<IndexOutOfBoundsException> {
            "a".modifications.replaceCharAt(index = -1, withChar = 'a')
        }

        @Test
        fun singleInvalidIndexPositive() = assertThrows<IndexOutOfBoundsException> {
            "a".modifications.replaceCharAt(index = 1, withChar = 'a')
        }

        @Test
        fun singleValidIndex() {
            "a".modifications.replaceCharAt(index = 0, withChar = 'Q').assert("Q")
        }

        @Test
        fun multipleInvalidIndexNegative() = assertThrows<IndexOutOfBoundsException> {
            "abc".modifications.replaceCharAt(index = -1, withChar = 'a')
        }

        @Test
        fun multipleInvalidIndexPositive() = assertThrows<IndexOutOfBoundsException> {
            "abc".modifications.replaceCharAt(index = 3, withChar = 'a')
        }

        @Test
        fun multipleReplaceRespectsInBounds() {
            "abc".modifications.replaceCharAt(index = 0, withChar = 'q').assert("qbc")
            "abc".modifications.replaceCharAt(index = 2, withChar = 'q').assert("abq")
        }


    }

}