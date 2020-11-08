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
        //TODO make me.

    }
}