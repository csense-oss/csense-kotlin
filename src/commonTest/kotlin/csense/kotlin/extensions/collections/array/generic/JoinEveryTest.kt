@file:Suppress("unused")

package csense.kotlin.extensions.collections.array.generic

import csense.kotlin.tests.assertions.*
import kotlin.test.*

class JoinEveryTest {
    class ArrayTJoinEveryAction {

        @Test
        fun empty() {
            val lst: Array<String> = arrayOf<String>().joinEveryAction(itemsBetweenJoins = 1) {
                "1"
            }
            lst.assertEmpty()
        }


        @Test
        fun single() {
            val res: Array<String> = arrayOf("1").joinEveryAction(itemsBetweenJoins = 1) {
                "test"
            }
            res.assertSize(1)
            res.first().assert("1", message = "cannot join when only one element")
        }

        @Test
        fun multiple() {
            val res: Array<String> = arrayOf("1", "3").joinEveryAction(itemsBetweenJoins = 1) {
                "2"
            }
            res.assertSize(3)
            res[0].assert("1")
            res[1].assert("2")
            res[2].assert("3")
        }
    }


    class ArrayTJoinEvery {
        @Test
        fun empty() {
            val lst: Array<String> = arrayOf<String>().joinEvery(itemsBetweenJoins = 1, toJoin = "")
            lst.assertEmpty()
        }

        @Test
        fun single() {
            val res: Array<String> = arrayOf("1").joinEvery(itemsBetweenJoins = 1, toJoin = "test")
            res.assertSize(1)
            res.first().assert("1", message = "cannot join when only one element")
        }

        @Test
        fun multiple() {
            val res: Array<String> = arrayOf("1", "3").joinEvery(itemsBetweenJoins = 1, toJoin = "2")
            res.assertSize(3)
            res[0].assert("1")
            res[1].assert("2")
            res[2].assert("3")
        }
    }



    class ArrayTJoinEveryAtSteps {
        @Test
        fun empty() {
            arrayOf<String>().joinEvery(itemsBetweenJoins = -1, toJoin = "").assertSize(0)
            arrayOf<String>().joinEvery(itemsBetweenJoins = 0, toJoin = "").assertSize(0)
            arrayOf<String>().joinEvery(itemsBetweenJoins = 1, toJoin = "").assertSize(0)
        }

        @Test
        fun single() {
            arrayOf("a").joinEvery(itemsBetweenJoins = -1, toJoin = "b").apply {
                assertSize(1)
                first().assert("a")
            }
            arrayOf("a").joinEvery(itemsBetweenJoins = 0, toJoin = "b").apply {
                assertSize(1)
                first().assert("a")
            }
            arrayOf("a").joinEvery(itemsBetweenJoins = 1, toJoin = "b").apply {
                assertSize(1)
                first().assert("a")
            }
        }

        @Test
        fun multiple() {
            arrayOf("a", "1").joinEvery(itemsBetweenJoins = -1, toJoin = "b").apply {
                assertSize(2)
                first().assert("a")
                last().assert("1")
            }
            arrayOf("a", "1").joinEvery(itemsBetweenJoins = 0, toJoin = "b").apply {
                assertSize(2)
                first().assert("a")
                last().assert("1")
            }
            arrayOf("a", "1").joinEvery(itemsBetweenJoins = 1, toJoin = "b").apply {
                assertSize(3)
                this[0].assert("a")
                this[1].assert("b")
                this[2].assert("1")
            }

            arrayOf("a", "b", "c").joinEvery(itemsBetweenJoins = 1, toJoin = "1").apply {
                assertSize(5)
                this[0].assert("a")
                this[1].assert("1")
                this[2].assert("b")
                this[3].assert("1")
                this[4].assert("c")
            }
            arrayOf("a", "b", "c").joinEvery(itemsBetweenJoins = 2, toJoin = "1").apply {
                assertSize(4)
                this[0].assert("a")
                this[1].assert("b")
                this[2].assert("1")
                this[3].assert("c")
            }
            arrayOf("a", "b", "c").joinEvery(itemsBetweenJoins = 3, toJoin = "1").apply {
                assertSize(3)
                this[0].assert("a")
                this[1].assert("b")
                this[2].assert("c")
            }

            arrayOf("a", "b", "c").joinEveryAction(itemsBetweenJoins = 3) {
                ""
            }.apply {
                assertSize(3)
                this[0].assert("a")
                this[1].assert("b")
                this[2].assert("c")
            }


        }
    }

}