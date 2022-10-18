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

    class StringModificationMapEachMatching {

        @Test
        fun bad() {
            "".modifications.mapEachMatching("", searchByWord = false, ignoreCase = false) { it }
                .assertEmpty("nothing in nothing should be nothing")
            "".modifications.mapEachMatching("0", searchByWord = false, ignoreCase = false) { it }
                .assertEmpty("finding something in nothing happens never")

            "0".modifications.mapEachMatching("", searchByWord = false, ignoreCase = false) { it }
                .assertEmpty("finding nothing in something happens never")

            "0".modifications.mapEachMatching("", searchByWord = true, ignoreCase = true) { it }
                .assertEmpty("no parameter changes that behavior")
            "".modifications.mapEachMatching("0", searchByWord = true, ignoreCase = true) { it }
                .assertEmpty("no parameter changes that behavior")
        }

        @Test
        fun good() {

            val textString = "-abc-aa-bb-cc-"
            val indexes = textString.modifications.mapEachMatching(
                "a",
                searchByWord = false,
                ignoreCase = false
            ) { it }
            indexes.assertSize(3, "there are 3 a's in the text")

            indexes[0].assert(1, "first is at second index of string")

            indexes[1].assert(5, "second is past 5 chars")

            indexes[2].assert(6, "third is past 6 chars")

            textString.modifications.mapEachMatching("A", searchByWord = false, ignoreCase = false) { it }
                .assertEmpty("should search case sensitive when asked")

            textString.modifications.mapEachMatching("A", searchByWord = false, ignoreCase = true) { it }
                .assertSize(3, "should find all case insensitive")


            val funnyString = "ababab"
            funnyString.modifications.mapEachMatching(
                "abab",
                searchByWord = false,
                ignoreCase = false
            ) { it }
                .assertSize(
                    2, "since searching by chars, we will encounter an overlap , which then " +
                            "will give us 2 results since we are only advancing by 1 chars"
                )


            funnyString.modifications.mapEachMatching(
                "abab",
                searchByWord = true,
                ignoreCase = false
            ) { it }
                .assertSize(
                    1, "since searching by word, we will NOT encounter an overlap , so " +
                            "we will only see [abab] followed by the last part (ab), so not 2 matches"
                )

        }
    }


    @Test
    fun replaceIf() {
        "abc".modifications.replaceIf(false, "abc", "1234", false).assert("abc", "should not replace")
        "abc".modifications.replaceIf(false, "abc", "1234", true).assert("abc", "should not replace")
        "abc".modifications.replaceIf(true, "abc", "1234", false).assert("1234")
        "abc".modifications.replaceIf(true, "ABC", "1234", false).assert("abc", "case does not match")
        "abc".modifications.replaceIf(true, "ABC", "1234", true).assert("1234")
    }


    class StringModificationReplaceIfOrStrings {
        @Test
        fun empty() {
            "".modifications.replaceIfOr(
                condition = false,
                toReplace = "test",
                ifTrueValue = "true",
                ifFalseValue = "false"
            )
                .assert("")
            "".modifications.replaceIfOr(
                condition = true,
                toReplace = "test",
                ifTrueValue = "true",
                ifFalseValue = "false"
            )
                .assert("")
        }

        @Test
        fun notFound() {
            "abc".modifications.replaceIfOr(
                condition = false,
                toReplace = "test",
                ifTrueValue = "true",
                ifFalseValue = "false"
            )
                .assert("abc")
            "abc".modifications.replaceIfOr(
                condition = true,
                toReplace = "test",
                ifTrueValue = "true",
                ifFalseValue = "false"
            )
                .assert("abc")
            "TEST".modifications.replaceIfOr(
                condition = true,
                toReplace = "test",
                ifTrueValue = "true",
                ifFalseValue = "false"
            )
                .assert("TEST")
        }

        @Test
        fun found() {
            "abc".modifications.replaceIfOr(
                condition = false,
                toReplace = "abc",
                ifTrueValue = "true",
                ifFalseValue = "false"
            )
                .assert("false")
            "abc".modifications.replaceIfOr(
                condition = true,
                toReplace = "abc",
                ifTrueValue = "true",
                ifFalseValue = "false"
            )
                .assert("true")
            "TEST".modifications.replaceIfOr(
                condition = false,
                toReplace = "test",
                ifTrueValue = "true",
                ifFalseValue = "false",
                ignoreCase = true
            )
                .assert("false")
            "TEST".modifications.replaceIfOr(
                condition = true,
                toReplace = "test",
                ifTrueValue = "true",
                ifFalseValue = "false",
                ignoreCase = true
            )
                .assert("true")
        }

    }

    class StringModificationReplaceIfOrCondition {
        @Test
        fun empty() {
            "".modifications.replaceIfOr(
                condition = false,
                toReplace = "test",
                ifTrueValue = { "true" },
                ifFalseValue = { "false" })
                .assert("")
            "".modifications.replaceIfOr(
                condition = true,
                toReplace = "test",
                ifTrueValue = { "true" },
                ifFalseValue = { "false" })
                .assert("")
        }

        @Test
        fun notFound() {
            "abc".modifications.replaceIfOr(
                condition = false,
                toReplace = "test",
                ifTrueValue = { "true" },
                ifFalseValue = { "false" }).assert("abc")
            "abc".modifications.replaceIfOr(
                condition = true,
                toReplace = "test",
                ifTrueValue = { "true" },
                ifFalseValue = { "false" }).assert("abc")
            "TEST".modifications.replaceIfOr(
                condition = true,
                toReplace = "test",
                ifTrueValue = { "true" },
                ifFalseValue = { "false" }).assert("TEST")
        }

        @Test
        fun found() {
            "abc".modifications.replaceIfOr(
                condition = false,
                toReplace = "abc",
                ifTrueValue = { "true" },
                ifFalseValue = { "false" }).assert("false")
            "abc".modifications.replaceIfOr(
                condition = true,
                toReplace = "abc",
                ifTrueValue = { "true" },
                ifFalseValue = { "false" })
                .assert("true")
            "TEST".modifications.replaceIfOr(
                condition = false,
                toReplace = "test",
                ifTrueValue = { "true" },
                ifFalseValue = { "false" },
                ignoreCase = true
            )
                .assert("false")
            "TEST".modifications.replaceIfOr(
                condition = true,
                toReplace = "test",
                ifTrueValue = { "true" },
                ifFalseValue = { "false" },
                ignoreCase = true
            )
                .assert("true")
        }
    }

    class StringModificationReplaceLazy {

        @Test
        fun empty() {
            "".modifications.replaceLazy("", { shouldNotBeCalled() }).assert("")
        }

        @Test
        fun singleNotFound() {
            "a".modifications.replaceLazy("", { shouldNotBeCalled() }).assert("a")
            "A".modifications.replaceLazy("", { shouldNotBeCalled() }).assert("A")

            "a".modifications.replaceLazy(" ", { shouldNotBeCalled() }).assert("a")
        }

        @Test
        fun singleFound() {
            assertCalled { shouldBeCalled ->
                "a".modifications.replaceLazy("a", { shouldBeCalled(); "-" }).assert("-")
            }
        }

        @Test
        fun singleIgnoreCaseFalse() {
            assertCalled { shouldBeCalled ->
                "a".modifications.replaceLazy("a", { shouldBeCalled(); "-" }, ignoreCase = false).assert("-")
            }

            "A".modifications.replaceLazy("a", { shouldNotBeCalled() }, ignoreCase = false).assert("A")
            "a".modifications.replaceLazy("A", { shouldNotBeCalled() }, ignoreCase = false).assert("a")

            assertCalled { shouldBeCalled ->
                "A".modifications.replaceLazy("A", { shouldBeCalled(); "-" }, ignoreCase = false).assert("-")
            }
        }

        @Test
        fun singleIgnoreCaseTrue() {
            assertCalled { shouldBeCalled ->
                "a".modifications.replaceLazy("a", { shouldBeCalled(); "-" }, ignoreCase = true).assert("-")
            }
            assertCalled { shouldBeCalled ->
                "A".modifications.replaceLazy("a", { shouldBeCalled(); "-" }, ignoreCase = true).assert("-")
            }
            assertCalled { shouldBeCalled ->
                "a".modifications.replaceLazy("A", { shouldBeCalled(); "-" }, ignoreCase = true).assert("-")
            }
            assertCalled { shouldBeCalled ->
                "A".modifications.replaceLazy("A", { shouldBeCalled(); "-" }, ignoreCase = true).assert("-")
            }
        }

        @Test
        fun multipleNoMatches() {
            "test 1234 test".modifications.replaceLazy("qwerty", ::shouldNotBeCalled).assert("test 1234 test")
        }

        @Test
        fun multipleMatchesString() = assertCalled { shouldBeCalled ->
            "test 1234 test".modifications.replaceLazy("test", { shouldBeCalled(); "-" }).assert("- 1234 -")
        }


        @Test
        fun multipleMatchesSingleLetter() = assertCalled { shouldBeCalled ->
            "test 1234 test".modifications.replaceLazy("t", { shouldBeCalled(); "-*-" })
                .assert("-*-es-*- 1234 -*-es-*-")
        }

    }

    class StringModificationReplaceEachOccurrence {

        @Test
        fun stringModificationReplaceEachOccurrence() {
            StringModification("some message {}").replaceEachOccurrence("{}", false){
                "myMessage"
            }.assert("some message myMessage")
        }
    }
}