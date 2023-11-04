@file:Suppress("unused")

package csense.kotlin.extensions.collections.iterable

import csense.kotlin.tests.assertions.*
import kotlin.test.*

class PartitionSafeTest {

    class IterableEPartitionSafe {
        @Test
        fun empty() {
            listOf<String>().partitionSafe { shouldNotBeCalled() }
        }

        @Test
        fun single() {
            listOf(1).partitionSafe { true }.apply {
                trueForPredicate.assertSize(1)
                falseForPredicate.assertEmpty()
                trueForPredicate.first().assert(1)
            }
            listOf(2).partitionSafe { false }.apply {
                trueForPredicate.assertEmpty()
                falseForPredicate.assertSize(1)
                falseForPredicate.first().assert(2)
            }
            listOf(3).partitionSafe { it == 3 }.apply {
                trueForPredicate.assertSize(1)
                falseForPredicate.assertEmpty()
            }
        }

        @Test
        fun multiple() {
            listOf("test", "1234", "a").partitionSafe { false }.apply {
                trueForPredicate.assertEmpty()
                falseForPredicate.assertSize(3)
            }
            listOf("test", "1234", "a").partitionSafe { true }.apply {
                trueForPredicate.assertSize(3)
                falseForPredicate.assertEmpty()
            }
            listOf("test", "1234", "a").partitionSafe { it == "a" }.apply {
                trueForPredicate.assertSize(1)
                falseForPredicate.assertSize(2)
                trueForPredicate.first().assert("a")
                falseForPredicate.assertContainsAll("test", "1234")
            }
            listOf("test", "1234", "a").partitionSafe { it.length == 4 }.apply {
                trueForPredicate.assertSize(2)
                falseForPredicate.assertSize(1)
                trueForPredicate.assertContainsAll("1234", "test")
                falseForPredicate.first().assert("a")
            }
        }
    }


}