package org.csenseoss.kotlin.specificExtensions.collections.collection.categorization

import org.csenseoss.kotlin.tests.assertions.collections.iterable.*
import org.csenseoss.kotlin.tests.assertions.collections.iterable.iterable.*
import org.csenseoss.kotlin.tests.assertions.collections.map.*
import org.csenseoss.kotlin.tests.assertions.general.*
import org.csenseoss.kotlin.tests.assertions.primitives.charSequence.*
import kotlin.test.*

class ByTest {


    @Test
    fun categorize() {
        val empty: List<String> = listOf()
        empty.categorization.categorize { failTest("should never get called") }.assertEmpty()

        val single: List<String> = listOf("test")
        single.categorization.categorize { it }.apply {
            assertSize(1)
            this["test"].assert("test")
        }


        val twoOfTwo: List<String> = listOf("test-1", "test-2", "asd-1", "asd-2")
        val twoOfTwoCat: Map<String, List<String>> = twoOfTwo.categorization.categorize { it: String ->
            when {
                it.startsWith("test") -> "test"
                else -> "asd"
            }
        }
        twoOfTwoCat.assertSize(2)
        twoOfTwoCat["test"].assertSize(2)
        twoOfTwoCat["asd"].assertSize(2)
    }

    class CollectionItemCategorizeByString {

        @Test
        fun empty() {
            listOf<String>().categorization.categorizeByString { it }.assertEmpty()
            listOf<String>().categorization.categorizeByString { "same" }.assertEmpty()

        }

        @Test
        fun single() {
            listOf("test").categorization.categorizeByString { it }.apply {
                assertSize(1)
                keys.first().assert("test")
                values.assert(listOf("test"))
            }

            listOf("test").categorization.categorizeByString { "same" }.apply {
                assertSize(1)
                keys.first().assert("same")
                values.assert(listOf("test"))
            }
        }

        @Test
        fun multiple() {
            listOf("test", "1234").categorization.categorizeByString { "constantKey" }.apply {
                assertSize(1)
                keys.first().assert("constantKey")
                values.assert(listOf("test","1234"))
            }
            listOf("test", "1234").categorization.categorizeByString { it }.apply {
                assertSize(2)
                keys.assert("test", "1234")
                values.assert(listOf("test"), listOf("1234"))
            }
        }
    }


}