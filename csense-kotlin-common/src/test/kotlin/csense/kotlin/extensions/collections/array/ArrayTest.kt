package csense.kotlin.extensions.collections.array

import csense.kotlin.test.assertions.*
import kotlin.collections.isNullOrEmpty
import kotlin.test.*

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

    object arrayTForEachBackwards {
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

    object arrayTForEach2 {
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

    object arrayTForEach2Indexed {
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

    object arrayTForEachDiscard {
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