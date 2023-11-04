@file:Suppress("unused")

package csense.kotlin.extensions.collections.array.generic

import csense.kotlin.tests.assertions.*
import kotlin.test.*

class IndexOfTest {

    class ArrayoutTIndexOfFirstOrNull {
        @Test
        fun empty() {
            arrayOf<String>().indexOfFirstOrNull { shouldNotBeCalled() }.assertNull()
        }

        @Test
        fun singleFound() {
            arrayOf("test").indexOfFirstOrNull { it == "test" }.assert(0)
        }

        @Test
        fun singleNotFound() {
            arrayOf("test").indexOfFirstOrNull { false }.assertNull()
        }

        @Test
        fun multipleFound() {
            arrayOf("test", "1234", "test").indexOfFirstOrNull { it == "test" }
                .assert(0, message = "should find the first matching item")
        }

        @Test
        fun multipleNotFound() {
            arrayOf("test", "1234").indexOfFirstOrNull { false }.assertNull()
        }
    }

    class ArrayTIndexOfLastOrNull {
        @Test
        fun empty() {
            arrayOf<String>().indexOfLastOrNull { shouldNotBeCalled() }.assertNull()
        }

        @Test
        fun singleFound() {
            arrayOf("test").indexOfLastOrNull { it == "test" }.assert(0)
        }

        @Test
        fun singleNotFound() {
            arrayOf("test").indexOfLastOrNull { false }.assertNull()
        }

        @Test
        fun multipleFound() {
            arrayOf("test", "1234", "test").indexOfLastOrNull { it == "test" }
                .assert(2, message = "should find the last matching item")
        }

        @Test
        fun multipleNotFound() {
            arrayOf("test", "1234").indexOfLastOrNull { false }.assertNull()
        }
    }

    class IndexOfFirstOrNullStartIndex {
        @Test
        fun empty() {
            arrayOf<String>().indexOfFirstOrNull(startIndex = 0) { shouldNotBeCalled() }.assertNull()
            arrayOf<String>().indexOfFirstOrNull(startIndex = 1) { shouldNotBeCalled() }.assertNull()
            arrayOf<String>().indexOfFirstOrNull(startIndex = -1) { shouldNotBeCalled() }.assertNull()
        }

        @Test
        fun singleFound() {
            arrayOf("test").indexOfFirstOrNull(startIndex = 0) { it == "test" }.assert(0)
            arrayOf("test").indexOfFirstOrNull(startIndex = 1) { it == "test" }.assertNull()
            arrayOf("test").indexOfFirstOrNull(startIndex = -1) { it == "test" }.assert(0)
        }

        @Test
        fun singleNotFound() {
            arrayOf("test").indexOfFirstOrNull(startIndex = 0) { false }.assertNull()
            arrayOf("test").indexOfFirstOrNull(startIndex = 1) { false }.assertNull()

        }

        @Test
        fun multipleFound() {
            arrayOf("test", "1234", "test").indexOfFirstOrNull(startIndex = 0) { it == "test" }
                .assert(0, message = "should find the first matching item")

            arrayOf("test", "1234", "test").indexOfFirstOrNull(startIndex = 1) { it == "test" }
                .assert(2, message = "should find the first matching item")

            arrayOf("test", "1234", "test").indexOfFirstOrNull(startIndex = 2) { it == "test" }
                .assert(2, message = "should find the first matching item")

            arrayOf("test", "1234", "test").indexOfFirstOrNull(startIndex = 3) { it == "test" }
                .assertNull()
        }

        @Test
        fun multipleNotFound() {
            arrayOf("test", "1234", "test").indexOfFirstOrNull(startIndex = 0) { false }
                .assertNull()

            arrayOf("test", "1234", "test").indexOfFirstOrNull(startIndex = 1) { false }
                .assertNull()

            arrayOf("test", "1234", "test").indexOfFirstOrNull(startIndex = 2) { false }
                .assertNull()
        }
    }


}