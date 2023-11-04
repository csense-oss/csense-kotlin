@file:Suppress("unused")

package csense.kotlin.extensions.collections.collection

import csense.kotlin.tests.assertions.*
import kotlin.test.*

class JoinEveryTest {
    //TODO when test plugin understands it,use "toJoin" as the end name
    class CollectionItemJoinEveryItemsBetweenJoin {
        @Test
        fun empty() {
            listOf<String>().joinEvery(-1, "").assertSize(0)
            listOf<String>().joinEvery(0, "").assertSize(0)
            listOf<String>().joinEvery(1, "").assertSize(0)
        }

        @Test
        fun single() {
            listOf("a").joinEvery(-1, "b").apply {
                assertSize(1)
                first().assert("a")
            }
            listOf("a").joinEvery(0, "b").apply {
                assertSize(1)
                first().assert("a")
            }
            listOf("a").joinEvery(1, "b").apply {
                assertSize(1)
                first().assert("a")
            }
        }

        @Test
        fun multiple() {
            listOf("a", "b").joinEvery(1, "0").apply {
                assertSize(3)
                this[0].assert("a")
                this[1].assert("0")
                this[2].assert("b")
            }

            listOf("a", "b", "c").joinEvery(1, "0").apply {
                assertSize(5)
                this[0].assert("a")
                this[1].assert("0")
                this[2].assert("b")
                this[3].assert("0")
                this[4].assert("c")
            }
            listOf("a", "b", "c").joinEvery(2, ",").apply {
                assertSize(4)
                this[0].assert("a")
                this[1].assert("b")
                this[2].assert(",")
                this[3].assert("c")
            }
        }
    }

    class CollectionItemJoinEveryAction {
        @Test
        fun empty() {
            listOf<String>().joinEveryAction(-1) { failTest() }.assertSize(0)
            listOf<String>().joinEveryAction(0) { failTest() }.assertSize(0)
            listOf<String>().joinEveryAction(1) { failTest() }.assertSize(0)
        }

        @Test
        fun single() {
            listOf("a").joinEvery(-1, "b").apply {
                assertSize(1)
                first().assert("a")
            }
            listOf("a").joinEvery(0, "b").apply {
                assertSize(1)
                first().assert("a")
            }
            listOf("a").joinEvery(1, "b").apply {
                assertSize(1)
                first().assert("a")
            }
        }

        @Test
        fun multiple() {
            listOf("a", "b").joinEveryAction(1) { "0" }.apply {
                assertSize(3)
                this[0].assert("a")
                this[1].assert("0")
                this[2].assert("b")
            }

            listOf("a", "b", "c").joinEveryAction(1) { "0" }.apply {
                assertSize(5)
                this[0].assert("a")
                this[1].assert("0")
                this[2].assert("b")
                this[3].assert("0")
                this[4].assert("c")
            }
            listOf("a", "b", "c").joinEveryAction(2) { "," }.apply {
                assertSize(4)
                this[0].assert("a")
                this[1].assert("b")
                this[2].assert(",")
                this[3].assert("c")
            }
        }
    }
}