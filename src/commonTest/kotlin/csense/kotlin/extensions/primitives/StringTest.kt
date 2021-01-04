@file:Suppress("unused")

package csense.kotlin.extensions.primitives

import csense.kotlin.tests.assertions.*
import kotlin.test.*


class StringTest {

    class ForEachMatching {

        @Test
        fun bad() {
            "".mapEachMatching("", searchByWord = false, ignoreCase = false) { it }
                .assertEmpty("nothing in nothing should be nothing")
            "".mapEachMatching("0", searchByWord = false, ignoreCase = false) { it }
                .assertEmpty("finding something in nothing happens never")

            "0".mapEachMatching("", searchByWord = false, ignoreCase = false) { it }
                .assertEmpty("finding nothing in something happens never")

            "0".mapEachMatching("", searchByWord = true, ignoreCase = true) { it }
                .assertEmpty("no parameter changes that behavior")
            "".mapEachMatching("0", searchByWord = true, ignoreCase = true) { it }
                .assertEmpty("no parameter changes that behavior")
        }

        @Test
        fun good() {

            val textString = "-abc-aa-bb-cc-"
            val indexes = textString.mapEachMatching(
                "a",
                searchByWord = false,
                ignoreCase = false
            ) { it }
            indexes.assertSize(3, "there are 3 a's in the text")

            indexes[0].assert(1, "first is at second index of string")

            indexes[1].assert(5, "second is past 5 chars")

            indexes[2].assert(6, "third is past 6 chars")

            textString.mapEachMatching("A", searchByWord = false, ignoreCase = false) { it }
                .assertEmpty("should search case sensitive when asked")

            textString.mapEachMatching("A", searchByWord = false, ignoreCase = true) { it }
                .assertSize(3, "should find all case insensitive")


            val funnyString = "ababab"
            funnyString.mapEachMatching(
                "abab",
                searchByWord = false,
                ignoreCase = false
            ) { it }
                .assertSize(
                    2, "since searching by chars, we will encounter an overlap , which then " +
                            "will give us 2 results since we are only advancing by 1 chars"
                )


            funnyString.mapEachMatching(
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
    fun findAllOf() {
        "".findAllOf("", searchByWord = false, ignoreCase = false)
            .assertEmpty("nothing in nothing is nothing")
        val textString = "\"a very funny qoute\""
        textString.findAllOf("\"", searchByWord = false, ignoreCase = false).apply {
            assertSize(2, "since there are 2 \" in the text")
            first().assert(0, "since the first \" is at the first location")
            last().assert(textString.length - 1, "since the last \" is at the end")
        }

        textString.findAllOf("abc", searchByWord = false, ignoreCase = false).assertEmpty()
    }

    @Test
    fun ifNotEmpty() {
        "".ifNotEmpty { "asd" }.assert("")
        " ".ifNotEmpty { "qwe" }.assert("qwe")
        "abc".ifNotEmpty { "123" }.assert("123")
    }


    @Test
    fun ifNotBlank() {
        "".ifNotBlank { "asd" }.assert("")
        " ".ifNotBlank { "qwe" }.assert(" ")
        "   ".ifNotBlank { "qwe" }.assert("   ")
        "abc".ifNotBlank { "123" }.assert("123")
    }


    @Test
    fun replaceIf() {
        "abc".replaceIf(false, "abc", "1234", false).assert("abc", "should not replace")
        "abc".replaceIf(false, "abc", "1234", true).assert("abc", "should not replace")
        "abc".replaceIf(true, "abc", "1234", false).assert("1234")
        "abc".replaceIf(true, "ABC", "1234", false).assert("abc", "case does not match")
        "abc".replaceIf(true, "ABC", "1234", true).assert("1234")
    }

    class ContainsAnyCollection {

        @Test
        fun empty() {
            "".containsAny(listOf()).assertFalse("nothing containing nothing is nonsense")
            "".containsAny(listOf("a")).assertFalse("a is not in empty")
            "".containsAny(listOf("a", " ")).assertFalse()

            "abc".containsAny(listOf("")).assertTrue("as contains..")
            "abc".containsAny(listOf(" ")).assertFalse("space is not in abc")

        }

        @Test
        fun single() {
            "a".containsAny(listOf("b")).assertFalse()
            "a".containsAny(listOf("a")).assertTrue()
            "a".containsAny(listOf("b", "a")).assertTrue()
            "abc".containsAny(listOf("d")).assertFalse()
        }

        @Test
        fun multiple() {
            "abc".containsAny(listOf("a")).assertTrue()
            "abc".containsAny(listOf("b")).assertTrue()
            "abc".containsAny(listOf("c")).assertTrue()
            "abc".containsAny(listOf("ab")).assertTrue()
            "abc".containsAny(listOf("bc")).assertTrue()

            "abc".containsAny(listOf("dd", "bb", "B"), ignoreCase = false).assertFalse()

            "abc".containsAny(listOf("dd", "bb", "B"), ignoreCase = true).assertTrue()

            "abc".containsAny(listOf("ab", "bc")).assertTrue()
        }


    }


    class ContainsAnyStrings {

        @Test
        fun empty() {
            "".containsAny().assertFalse("nothing containing nothing is nonsense")
            "".containsAny("a").assertFalse("a is not in empty")
            "".containsAny("a", " ").assertFalse()

            "abc".containsAny("").assertTrue("as contains..")
            "abc".containsAny(" ").assertFalse("space is not in abc")

        }

        @Test
        fun single() {
            "a".containsAny("b").assertFalse()
            "a".containsAny("a").assertTrue()
            "a".containsAny("b", "a").assertTrue()
            "abc".containsAny("d").assertFalse()
        }

        @Test
        fun multiple() {
            "abc".containsAny("a").assertTrue()
            "abc".containsAny("b").assertTrue()
            "abc".containsAny("c").assertTrue()
            "abc".containsAny("ab").assertTrue()
            "abc".containsAny("bc").assertTrue()

            "abc".containsAny("dd", "bb", "B", ignoreCase = false).assertFalse()

            "abc".containsAny("dd", "bb", "B", ignoreCase = true).assertTrue()

            "abc".containsAny("ab", "bc").assertTrue()
        }

    }


    @Test
    fun stringStartsWithAnyStrings() {
        "a".startsWithAny("a", ignoreCase = true).assertTrue()
        "a".startsWithAny("a", ignoreCase = false).assertTrue()

        "a".startsWithAny("A", ignoreCase = true).assertTrue()
        "a".startsWithAny("A", ignoreCase = false).assertFalse()

        "A".startsWithAny("a", ignoreCase = true).assertTrue()
        "A".startsWithAny("a", ignoreCase = false).assertFalse()

        "A".startsWithAny("A", ignoreCase = true).assertTrue()
        "A".startsWithAny("A", ignoreCase = false).assertTrue()


        "a".startsWithAny("a", "b", ignoreCase = true).assertTrue()
        "b-".startsWithAny("a", "b", ignoreCase = true).assertTrue()
        "b-".startsWithAny("a", "b", ignoreCase = false).assertTrue()
        "ab".startsWithAny("a", "b", ignoreCase = true).assertTrue()
        "a-b-c".startsWithAny("a", "b", ignoreCase = true).assertTrue()

        "ABC".startsWithAny("c", ignoreCase = true).assertFalse()

    }

    @Test
    fun stringStartsWithAnyCollection() {

        "".startsWithAny(listOf(), false).assertFalse()
        "".startsWithAny(listOf(), true).assertFalse()

        "a".startsWithAny(listOf(), false).assertFalse()
        "a".startsWithAny(listOf(), true).assertFalse()

        "a".startsWithAny(listOf("a"), true).assertTrue()
        "a".startsWithAny(listOf("a"), false).assertTrue()

        "a".startsWithAny(listOf("A"), true).assertTrue()
        "a".startsWithAny(listOf("A"), false).assertFalse()

        "A".startsWithAny(listOf("a"), true).assertTrue()
        "A".startsWithAny(listOf("a"), false).assertFalse()

        "A".startsWithAny(listOf("A"), true).assertTrue()
        "A".startsWithAny(listOf("A"), false).assertTrue()


        "a".startsWithAny(listOf("a", "b"), true).assertTrue()
        "b-".startsWithAny(listOf("a", "b"), true).assertTrue()
        "b-".startsWithAny(listOf("a", "b"), false).assertTrue()
        "ab".startsWithAny(listOf("a", "b"), true).assertTrue()
        "a-b-c".startsWithAny(listOf("a", "b"), true).assertTrue()

        "ABC".startsWithAny(listOf("c"), true).assertFalse()

    }

    @Test
    fun stringEndsWithAnyCollection() {

        "".endsWithAny(listOf(), false).assertFalse()
        "".endsWithAny(listOf(), true).assertFalse()

        "a".endsWithAny(listOf(), false).assertFalse()
        "a".endsWithAny(listOf(), true).assertFalse()

        "a".endsWithAny(listOf("a"), true).assertTrue()
        "a".endsWithAny(listOf("a"), false).assertTrue()

        "a".endsWithAny(listOf("A"), true).assertTrue()
        "a".endsWithAny(listOf("A"), false).assertFalse()

        "A".endsWithAny(listOf("a"), true).assertTrue()
        "A".endsWithAny(listOf("a"), false).assertFalse()

        "A".endsWithAny(listOf("A"), true).assertTrue()
        "A".endsWithAny(listOf("A"), false).assertTrue()


        "a".endsWithAny(listOf("a", "b"), true).assertTrue()
        "b-".endsWithAny(listOf("a", "b"), ignoreCase = true).assertFalse()
        "b-".endsWithAny(listOf("a", "b"), ignoreCase = false).assertFalse()
        "-b".endsWithAny(listOf("a", "b"), ignoreCase = true).assertTrue()
        "-b".endsWithAny(listOf("a", "b"), ignoreCase = false).assertTrue()
        "ab".endsWithAny(listOf("a", "b"), true).assertTrue()
        "a-b-c".endsWithAny(listOf("a", "b"), true).assertFalse()

        "a-b-c".endsWithAny(listOf("a", "b"), ignoreCase = true).assertFalse()
        "a-b-c".endsWithAny(listOf("d", "c"), true).assertTrue()

        "ABC".endsWithAny(listOf("c"), ignoreCase = true).assertTrue()
        "ABC".endsWithAny(listOf("c"), ignoreCase = false).assertFalse()

    }

    @Test
    fun stringEndsWithAnyStrings() {
        "a".endsWithAny("a", ignoreCase = true).assertTrue()
        "a".endsWithAny("a", ignoreCase = false).assertTrue()

        "a".endsWithAny("A", ignoreCase = true).assertTrue()
        "a".endsWithAny("A", ignoreCase = false).assertFalse()

        "A".endsWithAny("a", ignoreCase = true).assertTrue()
        "A".endsWithAny("a", ignoreCase = false).assertFalse()

        "A".endsWithAny("A", ignoreCase = true).assertTrue()
        "A".endsWithAny("A", ignoreCase = false).assertTrue()


        "a".endsWithAny("a", "b", ignoreCase = true).assertTrue()
        "b-".endsWithAny("a", "b", ignoreCase = true).assertFalse()
        "b-".endsWithAny("a", "b", ignoreCase = false).assertFalse()
        "-b".endsWithAny("a", "b", ignoreCase = true).assertTrue()
        "-b".endsWithAny("a", "b", ignoreCase = false).assertTrue()
        "ab".endsWithAny("a", "b", ignoreCase = true).assertTrue()
        "a-b-c".endsWithAny("a", "b", ignoreCase = true).assertFalse()

        "ABC".endsWithAny("c", ignoreCase = true).assertTrue()
        "ABC".endsWithAny("c", ignoreCase = false).assertFalse()

    }


    @Test
    fun stringDoesNotStartsWithPrefix() {
        "".doesNotStartsWith("").assertFalse()
        "a".doesNotStartsWith("").assertFalse()
        "".doesNotStartsWith("a").assertTrue()
        "a".doesNotStartsWith("a").assertFalse()
        "abc".doesNotStartsWith("a").assertFalse()
        "abc".doesNotStartsWith("abcd").assertTrue()
    }

    @Test
    fun stringDoesNotStartsWithPrefixChar() {
        "".doesNotStartsWith('a').assertTrue()
        "a".doesNotStartsWith('a').assertFalse()
        "abc".doesNotStartsWith('a').assertFalse()
    }

    class StringIsOnlyUpperCaseLetters {
        @Test
        fun noIgnoreNoneLetters() {
            "".isOnlyUpperCaseLetters().assertFalse() //nothing is always false.
            " ".isOnlyUpperCaseLetters().assertFalse()
            "a".isOnlyUpperCaseLetters().assertFalse()
            "A".isOnlyUpperCaseLetters().assertTrue()
            "abc".isOnlyUpperCaseLetters().assertFalse()
            "aBc".isOnlyUpperCaseLetters().assertFalse()
            "ABC".isOnlyUpperCaseLetters().assertTrue()
            "1234".isOnlyUpperCaseLetters().assertFalse()
            "\n".isOnlyUpperCaseLetters().assertFalse()
            "...()[]".isOnlyUpperCaseLetters().assertFalse()
        }

        @Test
        fun ignoreNoneLetters() {
            "".isOnlyUpperCaseLetters(true).assertFalse() //nothing is always false.
            " ".isOnlyUpperCaseLetters(true).assertTrue() //there are no "lowercase" letters
            "a".isOnlyUpperCaseLetters(true).assertFalse()
            "A".isOnlyUpperCaseLetters(true).assertTrue()
            "abc".isOnlyUpperCaseLetters(true).assertFalse()
            "aBc".isOnlyUpperCaseLetters(true).assertFalse()
            "ABC".isOnlyUpperCaseLetters(true).assertTrue()
            "1234".isOnlyUpperCaseLetters(true).assertTrue()
            "\n".isOnlyUpperCaseLetters(true).assertTrue()
            "...()[]".isOnlyUpperCaseLetters(true).assertTrue()
        }
    }

    class StringIsOnlyLowerCaseLetters {
        @Test
        fun noIgnoreNoneLetters() {
            "".isOnlyLowerCaseLetters().assertFalse()//nothing is always false.
            " ".isOnlyLowerCaseLetters().assertFalse()
            "a".isOnlyLowerCaseLetters().assertTrue()
            "A".isOnlyLowerCaseLetters().assertFalse()
            "abc".isOnlyLowerCaseLetters().assertTrue()
            "aBc".isOnlyLowerCaseLetters().assertFalse()
            "ABC".isOnlyLowerCaseLetters().assertFalse()
            "1234".isOnlyLowerCaseLetters().assertFalse()
            "\n".isOnlyLowerCaseLetters().assertFalse()
            "...()[]".isOnlyLowerCaseLetters().assertFalse()
        }

        @Test
        fun ignoreNoneLetters() {
            "".isOnlyLowerCaseLetters(true).assertFalse() //nothing is always false.
            " ".isOnlyLowerCaseLetters(true).assertTrue() //there are no "lowercase" letters
            "a".isOnlyLowerCaseLetters(true).assertTrue()
            "A".isOnlyLowerCaseLetters(true).assertFalse()
            "abc".isOnlyLowerCaseLetters(true).assertTrue()
            "aBc".isOnlyLowerCaseLetters(true).assertFalse()
            "ABC".isOnlyLowerCaseLetters(true).assertFalse()
            "1234".isOnlyLowerCaseLetters(true).assertTrue()
            "\n".isOnlyLowerCaseLetters(true).assertTrue()
            "...()[]".isOnlyLowerCaseLetters(true).assertTrue()
        }

    }

    @Test
    fun stringSkipStartsWith() {
        "".skipStartsWith("").assert("")
        "abc".skipStartsWith("a").assert("bc")
        "abc".skipStartsWith("12").assert("abc")
        "abc".skipStartsWith("bc").assert("abc")
        "a10bc".skipStartsWith("a10").assert("bc")
    }

    @Test
    fun stringForeach2() {
        "".foreach2 { first, second -> shouldNotBeCalled() }
        "a".foreach2 { first, second -> shouldNotBeCalled() }
        assertCalled(times = 1) {
            "ab".foreach2 { first, second ->
                first.assert('a')
                second.assert('b')
                it()
            }
        }
        assertCalled(times = 3) {
            "ababab".foreach2 { first, second ->
                first.assert('a')
                second.assert('b')
                it()
            }
        }
        //odd length
        "abababc".foreach2 { first, second -> shouldNotBeCalled() }
    }

    @Test
    fun stringDoesNotEndsWithSequence() {
        "".doesNotEndsWith("").assertFalse("anything does end with nothing")
        "abc".doesNotEndsWith("").assertFalse("anything ends with nothing")
        "abc".doesNotEndsWith("1234").assertTrue()
        "abc".doesNotEndsWith("a").assertTrue()
        "abc".doesNotEndsWith("a", ignoreCase = true).assertTrue()
        "abc".doesNotEndsWith("b").assertTrue()
        "abc".doesNotEndsWith("c").assertFalse()
        "abc".doesNotEndsWith("c", ignoreCase = true).assertFalse()
        "abc".doesNotEndsWith("C", ignoreCase = true).assertFalse()
        "abc".doesNotEndsWith("C", ignoreCase = false).assertTrue()
        "test 1234".doesNotEndsWith("1234").assertFalse()
        "test 1234".doesNotEndsWith("test").assertTrue()
    }

    @Test
    fun stringDoesNotEndsWithChar() {
        "".doesNotEndsWith('a').assertTrue()
        "a".doesNotEndsWith('a').assertFalse()
        "a".doesNotEndsWith('A').assertTrue()
        "a".doesNotEndsWith('A', ignoreCase = true).assertFalse()

    }


    @Test
    fun stringDoesNotEndsWithAnySubStrings() {
        //TODO make me.
        "".doesNotEndsWithAny("").assertFalse("ends with nothing")
        "".doesNotEndsWithAny("", "").assertFalse("ends with nothing, no matter how many times you ask")

        "abc".doesNotEndsWithAny("c").assertFalse()
        "abc".doesNotEndsWithAny("c", "c").assertFalse()

        "abc".doesNotEndsWithAny("a").assertTrue()
        "abc".doesNotEndsWithAny("a", "a").assertTrue()
        "abc".doesNotEndsWithAny("a", "b").assertTrue()

    }

    @Test
    fun stringDoesNotEndsWithAnyItems() {
        "".doesNotEndsWithAny(listOf("")).assertFalse("ends with nothing")
        "".doesNotEndsWithAny(listOf("", "")).assertFalse("ends with nothing, no matter how many times you ask")

        "abc".doesNotEndsWithAny(listOf("c")).assertFalse()
        "abc".doesNotEndsWithAny(listOf("c", "c")).assertFalse()

        "abc".doesNotEndsWithAny(listOf("a")).assertTrue()
        "abc".doesNotEndsWithAny(listOf("a", "a")).assertTrue()
        "abc".doesNotEndsWithAny(listOf("a", "b")).assertTrue()

    }


    class StringSplitWhen {
        @Test
        fun alwaysSplitEmpty() {
            "".splitWhen { true }.assertEmpty("nothing cannot be split")
        }

        @Test
        fun alwaysSplitSingle() {
            "a".splitWhen { true }.apply {
                assertSize(0, "since we do not include the split")
            }
        }

        @Test
        fun alwaysSplitMultiple() {
            "ab".splitWhen { true }.apply {
                assertSize(0, "since we do not include the split")
            }
        }

        @Test
        fun splitMultipleDigits() {
            "20/80 8000".splitWhen { it.isNotDigit() }.apply {
                assertSize(3)
                this[0].assert("20")
                this[1].assert("80")
                this[2].assert("8000")
            }
        }

        @Test
        fun splitMultipleDigitsWeird() {
            "20/80 abc 8000".splitWhen { it.isNotDigit() }.apply {
                assertSize(3)
                this[0].assert("20")
                this[1].assert("80")
                this[2].assert("8000")
            }
        }

    }

    @Test
    fun stringIsNewLine() {
        "".isNewLine().assertFalse()
        " ".isNewLine().assertFalse()
        "a".isNewLine().assertFalse()
        "abc".isNewLine().assertFalse()
        "1234".isNewLine().assertFalse()
        "Other region 한".isNewLine().assertFalse()
        "Hi ☺".isNewLine().assertFalse()
        "�".isNewLine().assertFalse()
        "\n".isNewLine().assertTrue()
        "...()[]".isNewLine().assertFalse()
    }
}