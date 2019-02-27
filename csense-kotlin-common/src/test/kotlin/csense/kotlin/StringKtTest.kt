package csense.kotlin

import csense.kotlin.extensions.primitives.*
import csense.kotlin.test.assertions.*
import kotlin.test.*

@Suppress("UNUSED_ANONYMOUS_PARAMETER")
class StringKtTest {

    @Test
    fun fileExtension() {
    }

    @Test
    fun removeFileExtension() {
    }

    @Test
    fun indexOfSafe() {
    }

    @Test
    fun createFromChars() {
    }

    @Test
    fun insertInto() {
    }

    @Test
    fun findAllOf() {
    }

    @Test
    fun forEachMatching() {
    }

    @Test
    fun replaceIf() {
    }

    @Test
    fun replaceIfOr() {
    }

    @Test
    fun replaceIfOr1() {
    }

    @Test
    fun endsWithAny() {
    }

    @Test
    fun endsWithAny1() {
    }

    @Test
    fun startsWithAny() {
    }

    @Test
    fun startsWithAny1() {
    }

    @Test
    fun limitTo() {
    }

    @Test
    fun wrapIn() {
    }

    @Test
    fun wrapInQuotes() {
    }

    @Test
    fun ifNotEmpty() {
    }

    @Test
    fun ifNotBlank() {
    }

    @Test
    fun skipStartsWith() {
    }

    @Test
    fun foreach2() {
        "".foreach2 { first, second ->
            failTest("empty cannot be called with first / second char")
        }

        "a".foreach2 { first, second ->
            failTest("single cannot be called with first / second char.")
        }


        "ab".foreach2 { first, second ->
            first.assert('a')
            second.assert('b')
        }

        "abc".foreach2 { first, second ->
            failTest("cannot be called on odd length'ed collections")
        }

        var counterAbAb = 0
        "abab".foreach2 { first, second ->
            first.assert('a')
            second.assert('b')
            counterAbAb += 2
        }
        counterAbAb.assert(4)


    }

    @Test
    fun foreach2Indexed() {
        "".foreach2Indexed { index, first, second ->
            failTest("empty cannot be called with first / second char")
        }

        "a".foreach2Indexed { index, first, second ->
            failTest("single cannot be called with first / second char.")
        }


        "ab".foreach2Indexed { index, first, second ->
            first.assert('a')
            second.assert('b')
        }

        "abc".foreach2Indexed { index, first, second ->
            failTest("cannot be called on odd length'ed collections")
        }

        var counterAbAb = 0
        "abab".foreach2Indexed { index, first, second ->
            first.assert('a')
            second.assert('b')
            counterAbAb += 2
        }
        counterAbAb.assert(4)

    }


    @Test
    fun fromHexStringToByteArray() {
        "aa".fromHexStringToByteArray().assertNotNullApply {
            size.assert(1)
            first().assert(0xaa)
        }
        "0x30".fromHexStringToByteArray().assertNotNullApply {
            count().assert(1)
            first().assert(0x30)
        }
        "30".fromHexStringToByteArray().assertNotNullApply {
            count().assert(1)
            first().assert(0x30)
        }

        "30203020".fromHexStringToByteArray().assertNotNullApply {
            count().assert(4)
            first().assert(0x30)
            get(1).assert(0x20)
            get(2).assert(0x30)
            last().assert(0x20)
        }
        " ".fromHexStringToByteArray().assertNull()
        "20x".fromHexStringToByteArray().assertNull()
        "qq".fromHexStringToByteArray().assertNull()
        "aa".fromHexStringToByteArray().assertNotNullApply {
            size.assert(1)
            first().assert(0xaa)
        }
        "FF".fromHexStringToByteArray().assertNotNullApply {
            size.assert(1)
            first().assert(0xFF)
        }
        "ff".fromHexStringToByteArray().assertNotNullApply {
            size.assert(1)
            first().assert(0xFF)
        }
    }
}