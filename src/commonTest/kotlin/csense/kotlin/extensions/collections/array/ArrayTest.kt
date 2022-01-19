package csense.kotlin.extensions.collections.array

import csense.kotlin.tests.assertions.*
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

    class ArrayTForEachBackwards {
        @Test
        fun empty() {
            arrayOf<String>().forEachBackwards { shouldNotBeCalled() }
        }

        @Test
        fun single() {
            assertCalled { shouldBeCalled ->
                arrayOf("test").forEachBackwards { it.assert("test"); shouldBeCalled() }
            }
        }

        @Test
        fun multiple() {
            assertCalled(times = 2) { shouldBeCalled ->
                arrayOf("first", "last").forEachBackwards { shouldBeCalled() }
            }
            var haveSeenLast = false
            arrayOf("first", "last").forEachBackwards {
                if (haveSeenLast) {
                    it.assert("first")
                } else {
                    it.assert("last")
                }
                haveSeenLast = true
            }
        }
    }

    class ArrayTForEachDiscard {

        @Test
        fun empty() {
            arrayOf<String>().forEachDiscard { shouldNotBeCalled() }
        }

        @Test
        fun single() {
            assertCalled { shouldBeCalled ->
                arrayOf("test").forEachDiscard {
                    it.assert("test")
                    shouldBeCalled()
                }
            }
        }

        @Test
        fun multiple() {
            assertCalled(times = 2) { shouldBeCalled ->
                arrayOf("test", "test2").forEachDiscard {
                    shouldBeCalled()
                }
            }
            var wasFirst = false
            arrayOf("first", "second").forEachDiscard {
                if (!wasFirst) {
                    it.assert("first")
                } else {
                    it.assert("second")
                }
                wasFirst = true
            }
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

    class ArrayTJoinEvery {
        @Test
        fun empty() {
            val lst = arrayOf<String>().joinEvery(1, "")
            lst.assertEmpty()
        }

        @Test
        fun single() {
            val res = arrayOf("1").joinEvery(1, "test")
            res.assertSize(1)
            res.first().assert("1", message = "cannot join when only one element")
        }

        @Test
        fun multiple() {
            val res = arrayOf("1", "3").joinEvery(1, "2")
            res.assertSize(3)
            res[0].assert("1")
            res[1].assert("2")
            res[2].assert("3")
        }
    }

    class ArrayTJoinEveryAction {

        @Test
        fun empty() {
            val lst = arrayOf<String>().joinEveryAction(1) {
                "1"
            }
            lst.assertEmpty()
        }


        @Test
        fun single() {
            val res = arrayOf("1").joinEveryAction(1) {
                "test"
            }
            res.assertSize(1)
            res.first().assert("1", message = "cannot join when only one element")
        }

        @Test
        fun multiple() {
            val res = arrayOf("1", "3").joinEveryAction(1) {
                "2"
            }
            res.assertSize(3)
            res[0].assert("1")
            res[1].assert("2")
            res[2].assert("3")
        }
    }

    class ArrayoutTIndexOfFirstOrNull {
        @Test
        fun empty() {
            arrayOf<String>().indexOfFirstOrNull { shouldNotBeCalled() }.assertNull()
        }

        @Test
        fun singleFound() {
            arrayOf("test").indexOfFirstOrNull { it == "test" }.assertNotNullAndEquals(0)
        }

        @Test
        fun singleNotFound() {
            arrayOf("test").indexOfFirstOrNull { false }.assertNull()
        }

        @Test
        fun multipleFound() {
            arrayOf("test", "1234", "test").indexOfFirstOrNull { it == "test" }
                .assertNotNullAndEquals(0, message = "should find the first matching item")
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
            arrayOf("test").indexOfLastOrNull { it == "test" }.assertNotNullAndEquals(0)
        }

        @Test
        fun singleNotFound() {
            arrayOf("test").indexOfLastOrNull { false }.assertNull()
        }

        @Test
        fun multipleFound() {
            arrayOf("test", "1234", "test").indexOfLastOrNull { it == "test" }
                .assertNotNullAndEquals(2, message = "should find the last matching item")
        }

        @Test
        fun multipleNotFound() {
            arrayOf("test", "1234").indexOfLastOrNull { false }.assertNull()
        }
    }
}