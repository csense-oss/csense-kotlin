@file:Suppress("unused")

package org.csenseoss.kotlin.extensions.collections.iterable

import org.csenseoss.kotlin.tests.assertions.collections.iterable.*
import org.csenseoss.kotlin.tests.assertions.comparable.*
import org.csenseoss.kotlin.tests.assertions.general.*
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
                falseForPredicate.assert("test","1234","a")
            }

            listOf("test", "1234", "a").partitionSafe { true }.apply {
                trueForPredicate.assert("test","1234","a")
                falseForPredicate.assertEmpty()
            }

            listOf("test", "1234", "a").partitionSafe { it == "a" }.apply {
                falseForPredicate.assert("test", "1234")
                trueForPredicate.assert("a")
            }

            listOf("test", "1234", "a").partitionSafe { it.length == 4 }.apply {
                trueForPredicate.assert("test", "1234")
                falseForPredicate.assert("a")
            }
        }
    }


}