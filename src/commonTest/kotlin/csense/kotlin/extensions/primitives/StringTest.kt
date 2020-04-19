@file:Suppress("unused")

package csense.kotlin.extensions.primitives

import csense.kotlin.tests.assertions.*
import kotlin.test.Test


class StringTest {
    @Test
    fun fileExtension() {
        val noExtensionMessage = "there are no extensions in this string"
        "".fileExtension().assertNull(noExtensionMessage)
        "test".fileExtension().assertNull(noExtensionMessage)
        "test.".fileExtension().assertNull(noExtensionMessage)
        "test..".fileExtension().assertNull(noExtensionMessage)
        
        "test.a".fileExtension().assertNotNullAndEquals("a")
        "test.a.".fileExtension().assertNull("since the text ends in . then there are no extension.")
        "test.a.b".fileExtension().assertNotNullAndEquals("b")
        "test..a".fileExtension().assertNotNullAndEquals("a")
        
        //more real life examples
        "test.xml".fileExtension().assertNotNullAndEquals("xml")
        "test.\$java".fileExtension().assertNotNullAndEquals("\$java")
        "test.\"xml".fileExtension().assertNotNullAndEquals("\"xml")
        
    }
    
    @Test
    fun forEachMatchingBad() {
        "".forEachMatching("", searchByWord = false, ignoreCase = false) { it }
                .assertEmpty("nothing in nothing should be nothing")
        "".forEachMatching("0", searchByWord = false, ignoreCase = false) { it }
                .assertEmpty("finding something in nothing happens never")
        
        "0".forEachMatching("", searchByWord = false, ignoreCase = false) { it }
                .assertEmpty("finding nothing in something happens never")
        
        "0".forEachMatching("", searchByWord = true, ignoreCase = true) { it }
                .assertEmpty("no parameter changes that behavior")
        "".forEachMatching("0", searchByWord = true, ignoreCase = true) { it }
                .assertEmpty("no parameter changes that behavior")
        
    }
    
    @Test
    fun forEachMatchingGood() {
        
        
        val textString = "-abc-aa-bb-cc-"
        val indexes = textString.forEachMatching(
                "a",
                searchByWord = false,
                ignoreCase = false) { it }
        indexes.assertSize(3, "there are 3 a's in the text")
        
        indexes[0].assert(1, "first is at second index of string")
        
        indexes[1].assert(5, "second is past 5 chars")
        
        indexes[2].assert(6, "third is past 6 chars")
        
        textString.forEachMatching("A", searchByWord = false, ignoreCase = false) { it }
                .assertEmpty("should search case sensitive when asked")
        
        textString.forEachMatching("A", searchByWord = false, ignoreCase = true) { it }
                .assertSize(3, "should find all case insensitive")
        
        
        val funnyString = "ababab"
        funnyString.forEachMatching(
                        "abab",
                        searchByWord = false,
                        ignoreCase = false) { it }
                .assertSize(2, "since searching by chars, we will encounter an overlap , which then " +
                        "will give us 2 results since we are only advancing by 1 chars")
        
        
        funnyString.forEachMatching(
                        "abab",
                        searchByWord = true,
                        ignoreCase = false) { it }
                .assertSize(1, "since searching by word, we will NOT encounter an overlap , so " +
                        "we will only see [abab] followed by the last part (ab), so not 2 matches")
        
        
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
    fun indexOfSafe() {
        "".indexOfSafe("a", 0, false).isError
                .assertTrue("indexof a in nothing i not found")
        "a".indexOfSafe("a", 0, false).value
                .assert(0, "should be first index")
        
        "a".indexOfSafe("A", 0, false).isError
                .assertTrue("case sensitivity should be respected")
        "a".indexOfSafe("A", 0, true).isError
                .assertFalse("case sensitivity should be respected")
        
        "aba".indexOfSafe("a", 1, false).value
                .assert(2, "should find second a")
        
        "abA".indexOfSafe("a", 1, false).isError
                .assertTrue("case sensitivity should be respected even with index")
        
        "abA".indexOfSafe("a", 1, true).value
                .assert(2, "should find second a")
        
    }
    
    @Test
    fun limitTo() {
        "".limitTo(0).assert("")
        "".limitTo(-1).assert("")
        "".limitTo(1).assert("")
        "abc".limitTo(0).assert("")
        "abc".limitTo(-2).assert("")
        "abc".limitTo(2).assert("ab")
        "abc".limitTo(1).assert("a")
        "abc".limitTo(3).assert("abc")
        "abc".limitTo(10).assert("abc")
    }
    
    @Test
    fun removeFileExtension() {
        "".removeFileExtension().assert("")
        "test".removeFileExtension().assert("test")
        "random string with some fun".removeFileExtension().assert("random string with some fun")
        "test.asd".removeFileExtension().assert("test")
        "qwerty.xml".removeFileExtension().assert("qwerty")
        "qwerty.xml.js".removeFileExtension().assert("qwerty.xml")
    }
    
    @Test
    fun wrapIn() {
        "".wrapIn("", "").assert("")
        "".wrapIn("aa", "").assert("aa")
        "".wrapIn("", "bb").assert("bb")
        "".wrapIn("a", "b").assert("ab")
        "1234".wrapIn("<", ">").assert("<1234>")
        "1234".wrapIn("<", "").assert("<1234")
        "1234".wrapIn("", ">").assert("1234>")
        "1234".wrapIn("", "").assert("1234")
        
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
    
    
    @Test
    fun wrapInQuotes() {
        "".wrapInQuotes().assert("\"\"")
        "a".wrapInQuotes().assert("\"a\"")
        "\"".wrapInQuotes().assert("\"\"\"")
        "yzz x".wrapInQuotes().assert("\"yzz x\"")
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
}