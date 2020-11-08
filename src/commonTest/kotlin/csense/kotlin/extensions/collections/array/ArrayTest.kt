package csense.kotlin.extensions.collections.array

import csense.kotlin.tests.assertions.*
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


    class ArrayTJoinEveryAtSteps {
        @Test
        fun empty() {
            arrayOf<String>().joinEvery(-1, "").assertSize(0)
            arrayOf<String>().joinEvery(0, "").assertSize(0)
            arrayOf<String>().joinEvery(1, "").assertSize(0)
        }

        @Test
        fun single() {
            arrayOf("a").joinEvery(-1, "b").apply {
                assertSize(1)
                first().assert("a")
            }
            arrayOf("a").joinEvery(0, "b").apply {
                assertSize(1)
                first().assert("a")
            }
            arrayOf("a").joinEvery(1, "b").apply {
                assertSize(1)
                first().assert("a")
            }
        }

        @Test
        fun multiple() {
            arrayOf("a", "1").joinEvery(-1, "b").apply {
                assertSize(2)
                first().assert("a")
                last().assert("1")
            }
            arrayOf("a", "1").joinEvery(0, "b").apply {
                assertSize(2)
                first().assert("a")
                last().assert("1")
            }
            arrayOf("a", "1").joinEvery(1, "b").apply {
                assertSize(3)
                this[0].assert("a")
                this[1].assert("b")
                this[2].assert("1")
            }

            arrayOf("a", "b", "c").joinEvery(1, "1").apply {
                assertSize(5)
                this[0].assert("a")
                this[1].assert("1")
                this[2].assert("b")
                this[3].assert("1")
                this[4].assert("c")
            }
            arrayOf("a", "b", "c").joinEvery(2, "1").apply {
                assertSize(4)
                this[0].assert("a")
                this[1].assert("b")
                this[2].assert("1")
                this[3].assert("c")
            }
            arrayOf("a", "b", "c").joinEvery(3, "1").apply {
                assertSize(3)
                this[0].assert("a")
                this[1].assert("b")
                this[2].assert("c")
            }

            arrayOf("a", "b", "c").joinEveryAction(3) {
                ""
            }.apply {
                assertSize(3)
                this[0].assert("a")
                this[1].assert("b")
                this[2].assert("c")
            }


        }
    }

    class ArrayTJoinEveryAtStepsToJoinAction {
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