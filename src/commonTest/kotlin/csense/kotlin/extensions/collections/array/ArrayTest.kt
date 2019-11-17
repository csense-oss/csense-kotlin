package csense.kotlin.extensions.collections.array

import csense.kotlin.tests.assertions.assert
import kotlin.collections.isNullOrEmpty
import kotlin.test.Test

class ArrayTest {

    @Test
    fun isNotNullOrEmpty() {
        val nullCol: Array<String>? = null
        nullCol.isNotNullOrEmpty().assert(false)
        val emptyCol: Array<String> = arrayOf()
        emptyCol.isNotNullOrEmpty().assert(false)
        val singleCol: Array<String> = arrayOf("omg")
        singleCol.isNotNullOrEmpty().assert(true)
    }

    @Test
    fun isNullOrEmpty() {
        val nullCol: Array<String>? = null
        nullCol.isNullOrEmpty().assert(true)
        val emptyCol: Array<String> = arrayOf()
        emptyCol.isNullOrEmpty().assert(true)
        val singleCol: Array<String> = arrayOf("omg")
        singleCol.isNullOrEmpty().assert(false)
    }

    class ArrayTForEachBackwards {
        @Test
        fun empty() {
            //TODO test empty condition here.
        }

        @Test
        fun single() {
            //TODO test single element condition here.
        }

        @Test
        fun multiple() {
            //TODO test multiple element condition here.
        }
    }

    class ArrayTForEach2 {
        @Test
        fun empty() {
            //TODO test empty condition here.
        }

        @Test
        fun single() {
            //TODO test single element condition here.
        }

        @Test
        fun multiple() {
            //TODO test multiple element condition here.
        }
    }

    class ArrayTForEach2Indexed {
        @Test
        fun empty() {
            //TODO test empty condition here.
        }

        @Test
        fun single() {
            //TODO test single element condition here.
        }

        @Test
        fun multiple() {
            //TODO test multiple element condition here.
        }
    }

    class ArrayTForEachDiscard {

        @Test
        fun empty() {
            //TODO test empty condition here.
        }

        @Test
        fun single() {
            //TODO test single element condition here.
        }

        @Test
        fun multiple() {
            //TODO test multiple element condition here.
        }
    }
}