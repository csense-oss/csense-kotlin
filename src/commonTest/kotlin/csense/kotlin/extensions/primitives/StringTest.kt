@file:Suppress("unused")

package csense.kotlin.extensions.primitives

import csense.kotlin.tests.assertions.*
import kotlin.test.*


class StringTest {

    @Test
    fun allIndicesOf() {
        "".allIndicesOf("", searchByWord = false, ignoreCase = false)
            .assertEmpty("nothing in nothing is nothing")
        val textString = "\"a very funny qoute\""
        textString.allIndicesOf("\"", searchByWord = false, ignoreCase = false).apply {
            assertSize(2, "since there are 2 \" in the text")
            first().assert(0, "since the first \" is at the first location")
            last().assert(textString.length - 1, "since the last \" is at the end")
        }

        textString.allIndicesOf("abc", searchByWord = false, ignoreCase = false).assertEmpty()
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
    fun nullOnEmpty() {
        "".nullOnEmpty().assertNull()
        " ".nullOnEmpty().assert(" ")
        "a".nullOnEmpty().assert("a")
        "abc".nullOnEmpty().assert("abc")
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


    class StringStartsWith {
        @Test
        fun prefix() {
            "".startsWith(prefix = "", ignoreCase = false, ignoreWhitespace = false).assertTrue()
            "".startsWith(prefix = " ", ignoreCase = false, ignoreWhitespace = false).assertFalse()
            "".startsWith(prefix = "a", ignoreCase = false, ignoreWhitespace = false).assertFalse()


            "a".startsWith(prefix = "", ignoreCase = false, ignoreWhitespace = false).assertTrue("a")
            "a".startsWith(prefix = " ", ignoreCase = false, ignoreWhitespace = false).assertFalse("b")
            "a".startsWith(prefix = "a", ignoreCase = false, ignoreWhitespace = false).assertTrue("c")
            "hello".startsWith(prefix = "hel", ignoreCase = false, ignoreWhitespace = false).assertTrue()
            "hello".startsWith(prefix = "helll", ignoreCase = false, ignoreWhitespace = false).assertFalse()
        }


        @Test
        fun ignoreCase() {
            "a".startsWith(prefix = "A", ignoreCase = false, ignoreWhitespace = false).assertFalse()
            "A".startsWith(prefix = "A", ignoreCase = false, ignoreWhitespace = false).assertTrue()

            "A".startsWith(prefix = "a", ignoreCase = true, ignoreWhitespace = false).assertTrue()
            "A".startsWith(prefix = "A", ignoreCase = true, ignoreWhitespace = false).assertTrue()
        }


        @Test
        fun ignoreWhitespace() {
            " a".startsWith(prefix = "a", ignoreCase = false, ignoreWhitespace = false).assertFalse("a")

            " a".startsWith(prefix = "a", ignoreCase = false, ignoreWhitespace = true).assertTrue("b")
            "   a".startsWith(prefix = "a", ignoreCase = false, ignoreWhitespace = true).assertTrue("c")
            "\ta".startsWith(prefix = "a", ignoreCase = false, ignoreWhitespace = true).assertTrue("d")
            "\na".startsWith(prefix = "a", ignoreCase = false, ignoreWhitespace = true).assertTrue("e")
        }

    }

    class StringEndsWith {


        @Test
        fun suffix() {
            "".endsWith(suffix = "", ignoreCase = false, ignoreWhitespace = false).assertTrue()
            "".endsWith(suffix = " ", ignoreCase = false, ignoreWhitespace = false).assertFalse()
            "".endsWith(suffix = "a", ignoreCase = false, ignoreWhitespace = false).assertFalse()


            "a".endsWith(suffix = "", ignoreCase = false, ignoreWhitespace = false).assertTrue()
            "a".endsWith(suffix = " ", ignoreCase = false, ignoreWhitespace = false).assertFalse()
            "a".endsWith(suffix = "a", ignoreCase = false, ignoreWhitespace = false).assertTrue()
            "hello".endsWith(suffix = "llo", ignoreCase = false, ignoreWhitespace = false).assertTrue()
            "hello".endsWith(suffix = "lllo", ignoreCase = false, ignoreWhitespace = false).assertFalse()
        }


        @Test
        fun ignoreCase() {
            "a".endsWith(suffix = "A", ignoreCase = false, ignoreWhitespace = false).assertFalse()
            "A".endsWith(suffix = "A", ignoreCase = false, ignoreWhitespace = false).assertTrue()

            "A".endsWith(suffix = "a", ignoreCase = true, ignoreWhitespace = false).assertTrue()
            "A".endsWith(suffix = "A", ignoreCase = true, ignoreWhitespace = false).assertTrue()
        }


        @Test
        fun ignoreWhitespace() {
            "a ".endsWith(suffix = "a", ignoreCase = false, ignoreWhitespace = false).assertFalse()

            "a ".endsWith(suffix = "a", ignoreCase = false, ignoreWhitespace = true).assertTrue()
            "a   ".endsWith(suffix = "a", ignoreCase = false, ignoreWhitespace = true).assertTrue()
            "a\t".endsWith(suffix = "a", ignoreCase = false, ignoreWhitespace = true).assertTrue()
            "a\n".endsWith(suffix = "a", ignoreCase = false, ignoreWhitespace = true).assertTrue()
        }
    }

    class StringEquals {

        @Test
        fun simpleIgnoreAll() {
            "".equals("", ignoreCase = false, ignoreWhitespace = false).assertTrue()
            " ".equals("", ignoreCase = false, ignoreWhitespace = false).assertFalse()
            "".equals(" ", ignoreCase = false, ignoreWhitespace = false).assertFalse()
            "a".equals("a", ignoreCase = false, ignoreWhitespace = false).assertTrue()
            "Test".equals("Test", ignoreCase = false, ignoreWhitespace = false).assertTrue()
            "Test".equals("Test ", ignoreCase = false, ignoreWhitespace = false).assertFalse()
        }

        @Test
        fun ignoreCaseFalse() {
            "a".equals("a", ignoreCase = false, ignoreWhitespace = false).assertTrue()
            "a".equals("A", ignoreCase = false, ignoreWhitespace = false).assertFalse()
            "aBc".equals("abc", ignoreCase = false, ignoreWhitespace = false).assertFalse()
        }

        @Test
        fun ignoreCaseTrue() {
            "a".equals("a", ignoreCase = true, ignoreWhitespace = false).assertTrue()
            "a".equals("A", ignoreCase = true, ignoreWhitespace = false).assertTrue()
            "aBc".equals("abc", ignoreCase = true, ignoreWhitespace = false).assertTrue()
            "aBc1234".equals("abc123X", ignoreCase = true, ignoreWhitespace = false).assertFalse()
        }

        @Test
        fun ignoreWhitespaceFalseAllCombinations() {
            "a".equals(" a", ignoreCase = false, ignoreWhitespace = false).assertFalse()
            "a".equals("a ", ignoreCase = false, ignoreWhitespace = false).assertFalse()
            "a".equals(" a ", ignoreCase = false, ignoreWhitespace = false).assertFalse()

            " a".equals("a", ignoreCase = false, ignoreWhitespace = false).assertFalse()
            " a".equals(" a", ignoreCase = false, ignoreWhitespace = false).assertTrue()
            " a".equals("a ", ignoreCase = false, ignoreWhitespace = false).assertFalse()
            " a".equals(" a ", ignoreCase = false, ignoreWhitespace = false).assertFalse()

            "a ".equals("a", ignoreCase = false, ignoreWhitespace = false).assertFalse()
            "a ".equals(" a", ignoreCase = false, ignoreWhitespace = false).assertFalse()
            "a ".equals("a ", ignoreCase = false, ignoreWhitespace = false).assertTrue()
            "a ".equals(" a ", ignoreCase = false, ignoreWhitespace = false).assertFalse()

            " a ".equals("a", ignoreCase = false, ignoreWhitespace = false).assertFalse()
            " a ".equals(" a", ignoreCase = false, ignoreWhitespace = false).assertFalse()
            " a ".equals("a ", ignoreCase = false, ignoreWhitespace = false).assertFalse()
            " a ".equals(" a ", ignoreCase = false, ignoreWhitespace = false).assertTrue()
        }

        @Test
        fun ignoreWhitespaceTrueAllCombinations() {
            "a".equals(" a", ignoreCase = false, ignoreWhitespace = true).assertTrue()
            "a".equals("a ", ignoreCase = false, ignoreWhitespace = true).assertTrue()
            "a".equals(" a ", ignoreCase = false, ignoreWhitespace = true).assertTrue()

            " a".equals("a", ignoreCase = false, ignoreWhitespace = true).assertTrue()
            " a".equals(" a", ignoreCase = false, ignoreWhitespace = true).assertTrue()
            " a".equals("a ", ignoreCase = false, ignoreWhitespace = true).assertTrue()
            " a".equals(" a ", ignoreCase = false, ignoreWhitespace = true).assertTrue()

            "a ".equals("a", ignoreCase = false, ignoreWhitespace = true).assertTrue()
            "a ".equals(" a", ignoreCase = false, ignoreWhitespace = true).assertTrue()
            "a ".equals("a ", ignoreCase = false, ignoreWhitespace = true).assertTrue()
            "a ".equals(" a ", ignoreCase = false, ignoreWhitespace = true).assertTrue()

            " a ".equals("a", ignoreCase = false, ignoreWhitespace = true).assertTrue()
            " a ".equals(" a", ignoreCase = false, ignoreWhitespace = true).assertTrue()
            " a ".equals("a ", ignoreCase = false, ignoreWhitespace = true).assertTrue()
            " a ".equals(" a ", ignoreCase = false, ignoreWhitespace = true).assertTrue()
        }

        @Test
        fun mixedCombinations() {
            " Im a test".equals("Im a test ", ignoreCase = false, ignoreWhitespace = false).assertFalse()
            " Im a test".equals("Im a test ", ignoreCase = false, ignoreWhitespace = true).assertTrue()

            " Im a test".equals("im a test ", ignoreCase = true, ignoreWhitespace = false).assertFalse()
            " Im a test".equals("im a test ", ignoreCase = false, ignoreWhitespace = true).assertFalse()
            " Im a test".equals("im a test ", ignoreCase = true, ignoreWhitespace = true).assertTrue()
        }

    }
}
