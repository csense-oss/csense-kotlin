@file:Suppress("unused")

package csense.kotlin.extensions.primitives

import csense.kotlin.tests.assertions.*
import kotlin.test.*


class StringTest {

    class StringMapEachMatching {

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
        "".foreach2 { _, _ -> shouldNotBeCalled() }
        "a".foreach2 { _, _ -> shouldNotBeCalled() }
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
        "abababc".foreach2 { _, _ -> shouldNotBeCalled() }
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


    @Test
    fun stringIsNewLine() {
        "".isNewLine().assertFalse()
        " ".isNewLine().assertFalse()
        "a".isNewLine().assertFalse()
        "abc".isNewLine().assertFalse()
        "1234".isNewLine().assertFalse()
        "Other region 한".isNewLine().assertFalse()
        "Hi ☺".isNewLine().assertFalse()
        "\n".isNewLine().assertTrue()
        "...()[]".isNewLine().assertFalse()
    }


    class StringForEachBackwardsIndexed {
        @Test
        fun empty() {
            "".forEachBackwardsIndexed { _, _ ->
                shouldNotBeCalled()
            }
        }

        @Test
        fun single() = assertCalled { shouldBeCalled ->
            "a".forEachBackwardsIndexed { index, char ->
                index.assert(0)
                char.assert('a')
                shouldBeCalled()
            }
        }


        @Test
        fun callsNumberOfCharsTimes() = assertCalled(times = 3) { shouldBeCalled ->
            "abc".forEachBackwardsIndexed { _, _ ->
                shouldBeCalled()
            }
        }

        @Test
        fun startsFromTheBack() {
            "abc".forEachBackwardsIndexed { index, char ->
                index.assert(2)
                char.assert('c')
                return@startsFromTheBack
            }
        }

    }

    class StringForEachBackwards {
        @Test
        fun empty() {
            "".forEachBackwards {
                shouldNotBeCalled()
            }
        }

        @Test
        fun single() = assertCalled { shouldBeCalled ->
            "a".forEachBackwards { char ->
                char.assert('a')
                shouldBeCalled()
            }
        }


        @Test
        fun callsNumberOfCharsTimes() = assertCalled(times = 3) { shouldBeCalled ->
            "abc".forEachBackwards {
                shouldBeCalled()
            }
        }

        @Test
        fun startsFromTheBack() {
            "abc".forEachBackwards { char ->
                char.assert('c')
                return@startsFromTheBack
            }
        }

    }


    class StringDoesNotStartsWithAnyItems {
        @Test
        fun emptyString() {
            "".doesNotStartsWithAny(listOf("test")).assertTrue("empty does not start with something")
            "".doesNotStartsWithAny(listOf("test", "")).assertFalse("empty starts with empty")
        }

        @Test
        fun emptyList() {
            "".doesNotStartsWithAny(listOf()).assertTrue()
            "test".doesNotStartsWithAny(listOf()).assertTrue()
        }

    }

    class StringDoesNotStartsWithAnySubStrings {
        @Test
        fun emptyString() {
            "".doesNotStartsWithAny(subStrings = arrayOf("test")).assertTrue("empty does not start with something")
            "".doesNotStartsWithAny(subStrings = arrayOf("test", "")).assertFalse("empty starts with empty")
        }

        @Test
        fun emptyList() {
            "".doesNotStartsWithAny(subStrings = arrayOf()).assertTrue()
            "test".doesNotStartsWithAny(subStrings = arrayOf()).assertTrue()
        }
    }


    @Test
    fun stringFromHexStringToByteArray() {
        "".fromHexStringToByteArray().assertNull("not hex")
        " ".fromHexStringToByteArray().assertNull("not hex")
        "0xFF".fromHexStringToByteArray().assertNotNullApply {
            assertSize(1)
            first().assert(0xFF)
        }
    }

    @Test
    fun stringForeach2Indexed() {
        "".foreach2Indexed { _, _, _ ->
            shouldNotBeCalled()
        }
        "a".foreach2Indexed { _, _, _ ->
            shouldNotBeCalled()
        }
        assertCalled { shouldBeCalled ->
            "ab".foreach2Indexed { indexOfFirst, first, second ->
                shouldBeCalled()
                indexOfFirst.assert(0)
                first.assert('a')
                second.assert('b')
            }
        }

        assertCalled(times = 2) { shouldBeCalled ->
            var indexCounter = 0
            "ab12".foreach2Indexed { indexOfFirst, first, second ->
                indexOfFirst.assert(indexCounter)
                if (indexCounter == 0) {
                    first.assert('a')
                    second.assert('b')
                } else {
                    first.assert('1')
                    second.assert('2')
                }
                shouldBeCalled()
                indexCounter += 2
            }
        }
    }


    class StringReplaceIfOrStrings {
        @Test
        fun empty() {
            "".replaceIfOr(condition = false, toReplace = "test", ifTrueValue = "true", ifFalseValue = "false")
                .assert("")
            "".replaceIfOr(condition = true, toReplace = "test", ifTrueValue = "true", ifFalseValue = "false")
                .assert("")
        }

        @Test
        fun notFound() {
            "abc".replaceIfOr(condition = false, toReplace = "test", ifTrueValue = "true", ifFalseValue = "false")
                .assert("abc")
            "abc".replaceIfOr(condition = true, toReplace = "test", ifTrueValue = "true", ifFalseValue = "false")
                .assert("abc")
            "TEST".replaceIfOr(condition = true, toReplace = "test", ifTrueValue = "true", ifFalseValue = "false")
                .assert("TEST")
        }

        @Test
        fun found() {
            "abc".replaceIfOr(condition = false, toReplace = "abc", ifTrueValue = "true", ifFalseValue = "false")
                .assert("false")
            "abc".replaceIfOr(condition = true, toReplace = "abc", ifTrueValue = "true", ifFalseValue = "false")
                .assert("true")
            "TEST".replaceIfOr(
                condition = false,
                toReplace = "test",
                ifTrueValue = "true",
                ifFalseValue = "false",
                ignoreCase = true
            )
                .assert("false")
            "TEST".replaceIfOr(
                condition = true,
                toReplace = "test",
                ifTrueValue = "true",
                ifFalseValue = "false",
                ignoreCase = true
            )
                .assert("true")
        }

    }

    class StringReplaceIfOrCondition {
        @Test
        fun empty() {
            "".replaceIfOr(condition = false, toReplace = "test", ifTrueValue = { "true" }, ifFalseValue = { "false" })
                .assert("")
            "".replaceIfOr(condition = true, toReplace = "test", ifTrueValue = { "true" }, ifFalseValue = { "false" })
                .assert("")
        }

        @Test
        fun notFound() {
            "abc".replaceIfOr(
                condition = false,
                toReplace = "test",
                ifTrueValue = { "true" },
                ifFalseValue = { "false" }).assert("abc")
            "abc".replaceIfOr(
                condition = true,
                toReplace = "test",
                ifTrueValue = { "true" },
                ifFalseValue = { "false" }).assert("abc")
            "TEST".replaceIfOr(
                condition = true,
                toReplace = "test",
                ifTrueValue = { "true" },
                ifFalseValue = { "false" }).assert("TEST")
        }

        @Test
        fun found() {
            "abc".replaceIfOr(
                condition = false,
                toReplace = "abc",
                ifTrueValue = { "true" },
                ifFalseValue = { "false" }).assert("false")
            "abc".replaceIfOr(condition = true, toReplace = "abc", ifTrueValue = { "true" }, ifFalseValue = { "false" })
                .assert("true")
            "TEST".replaceIfOr(
                condition = false,
                toReplace = "test",
                ifTrueValue = { "true" },
                ifFalseValue = { "false" },
                ignoreCase = true
            )
                .assert("false")
            "TEST".replaceIfOr(
                condition = true,
                toReplace = "test",
                ifTrueValue = { "true" },
                ifFalseValue = { "false" },
                ignoreCase = true
            )
                .assert("true")
        }
    }

    @Test
    fun nullOnEmpty() {
        "".nullOnEmpty().assertNull()
        " ".nullOnEmpty().assertNotNullAndEquals(" ")
        "a".nullOnEmpty().assertNotNullAndEquals("a")
        "abc".nullOnEmpty().assertNotNullAndEquals("abc")
    }

    @Test
    fun titleCaseFirstWord() {
        "".titleCaseFirstWord().assert("")
        " ".titleCaseFirstWord().assert(" ")
        "a".titleCaseFirstWord().assert("A")
        "abc".titleCaseFirstWord().assert("Abc")
        "1234".titleCaseFirstWord().assert("1234")
        "Hi ☺".titleCaseFirstWord().assert("Hi ☺")
        "Ǆ".titleCaseFirstWord().assert("ǅ")
        "ǆ".titleCaseFirstWord().assert("ǅ")
        "hej med".titleCaseFirstWord().assert("Hej med")
    }


    class StringReplaceCharAtOrNull {

        @Test
        fun empty() {
            "".replaceCharAtOrNull(index = 0, withChar = 'a').assertNull()
            "".replaceCharAtOrNull(index = -1, withChar = 'a').assertNull()
            "".replaceCharAtOrNull(index = -1, withChar = 'a').assertNull()
        }

        @Test
        fun single() {
            " ".replaceCharAtOrNull(index = 0, withChar = 'a').assertNotNullAndEquals("a")
            "a".replaceCharAtOrNull(index = 0, withChar = '1').assertNotNullAndEquals("1")
            "b".replaceCharAtOrNull(index = 1, withChar = 'a').assertNull()
        }

        @Test
        fun multiple() {
            "abc".replaceCharAtOrNull(index = -1, withChar = 'q').assertNull()
            "abc".replaceCharAtOrNull(index = 0, withChar = 'q').assertNotNullAndEquals("qbc")
            "abc".replaceCharAtOrNull(index = 1, withChar = 'q').assertNotNullAndEquals("aqc")
            "abc".replaceCharAtOrNull(index = 2, withChar = 'q').assertNotNullAndEquals("abq")
            "abc".replaceCharAtOrNull(index = 3, withChar = 'q').assertNull()

        }

    }

    class StringSplitAtOrNull {
        @Test
        fun empty() {
            "".splitAtOrNull(-1).assertNull()
            "".splitAtOrNull(0).assertNull()
            "".splitAtOrNull(1).assertNull()
        }

        @Test
        fun single() {
            "a".splitAtOrNull(-1).assertNull()
            "a".splitAtOrNull(0).assertNotNullApply {
                beforeIndex.assertEmpty()
                afterIndex.assertEmpty()
            }
            "a".splitAtOrNull(1).assertNull()
        }

        @Test
        fun multiple() {
            "abc".splitAtOrNull(-1).assertNull()
            "abc".splitAtOrNull(0).assertNotNullApply {
                beforeIndex.assertEmpty()
                afterIndex.assert("bc")
            }
            "abc".splitAtOrNull(1).assertNotNullApply {
                beforeIndex.assert("a")
                afterIndex.assert("c")
            }
            "abc".splitAtOrNull(2).assertNotNullApply {
                beforeIndex.assert("ab")
                afterIndex.assertEmpty()
            }
            "abc".splitAtOrNull(3).assertNull()

        }
    }

    class StringReplaceCharAt {

        @Test
        fun empty() = assertThrows<IndexOutOfBoundsException> {
            "".replaceCharAt(0, 'a')
        }


        @Test
        fun singleInvalidIndexNegative() = assertThrows<IndexOutOfBoundsException> {
            "a".replaceCharAt(index = -1, withChar = 'a')
        }

        @Test
        fun singleInvalidIndexPositive() = assertThrows<IndexOutOfBoundsException> {
            "a".replaceCharAt(index = 1, withChar = 'a')
        }

        @Test
        fun singleValidIndex() {
            "a".replaceCharAt(index = 0, withChar = 'Q').assert("Q")
        }

        @Test
        fun multipleInvalidIndexNegative() = assertThrows<IndexOutOfBoundsException> {
            "abc".replaceCharAt(index = -1, withChar = 'a')
        }

        @Test
        fun multipleInvalidIndexPositive() = assertThrows<IndexOutOfBoundsException> {
            "abc".replaceCharAt(index = 3, withChar = 'a')
        }

        @Test
        fun multipleReplaceRespectsInBounds() {
            "abc".replaceCharAt(index = 0, withChar = 'q').assertNotNullAndEquals("qbc")
            "abc".replaceCharAt(index = 2, withChar = 'q').assertNotNullAndEquals("abq")
        }


    }
}

