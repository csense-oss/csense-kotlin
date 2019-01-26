package csense.kotlin.extensions.primitives

import csense.kotlin.test.assertions.*
import kotlin.test.*

class StringTest {
    @Test
    fun testFileExtension() {
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
    fun testForEachMatchingBad() {
        "".forEachMatching("", false, false) { it }
                .assertEmpty("nothing in nothing should be nothing")
        "".forEachMatching("0", false, false) { it }
                .assertEmpty("finding something in nothing happens never")

        "0".forEachMatching("", false, false) { it }
                .assertEmpty("finding nothing in something happens never")

        "0".forEachMatching("", true, true) { it }
                .assertEmpty("no parameter changes that behavior")
        "".forEachMatching("0", true, true) { it }
                .assertEmpty("no parameter changes that behavior")

    }

    @Test
    fun testForEachMatchingGood() {


        val textString = "-abc-aa-bb-cc-"
        val indexes = textString.forEachMatching(
                "a",
                false,
                false) { it }
        indexes.assertSize(3, "there are 3 a's in the text")

        indexes[0].assert(1, "first is at second index of string")

        indexes[1].assert(5, "second is past 5 chars")

        indexes[2].assert(6, "third is past 6 chars")

        textString.forEachMatching("A", false, false) { it }
                .assertEmpty("should search case sensitive when asked")

        textString.forEachMatching("A", false, true) { it }
                .assertSize(3, "should find all case insensitive")


        val funnyString = "ababab"
        funnyString.forEachMatching(
                "abab",
                false,
                false) { it }
                .assertSize(2, "since searching by chars, we will encounter an overlap , which then " +
                        "will give us 2 results since we are only advancing by 1 chars")


        funnyString.forEachMatching(
                "abab",
                true,
                false) { it }
                .assertSize(1, "since searching by word, we will NOT encounter an overlap , so " +
                        "we will only see [abab] followed by the last part (ab), so not 2 matches")


    }

    @Test
    fun testFindAllOf() {
        "".findAllOf("", false, false)
                .assertEmpty("nothing in nothing is nothing")
        val textString = "\"a very funny qoute\""
        textString.findAllOf("\"", false, false).apply {
            assertSize(2, "since there are 2 \" in the text")
            first().assert(0, "since the first \" is at the first location")
            last().assert(textString.length - 1, "since the last \" is at the end")
        }

        textString.findAllOf("abc", false, false).assertEmpty()
    }

    @Test
    fun testIndexOfSafe() {
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
    fun testLimitTo(){
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
}