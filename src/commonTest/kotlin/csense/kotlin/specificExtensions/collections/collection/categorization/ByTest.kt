package csense.kotlin.specificExtensions.collections.collection.categorization

import csense.kotlin.tests.assertions.*
import kotlin.test.*

class ByTest {


    @Test
    fun categorize() {
        val empty: List<String> = listOf()
        empty.categorization.categorize { failTest("should never get called") }.assertSize(0)

        val single: List<String> = listOf("test")
        single.categorization.categorize { it }.apply {
            assertSize(1)
            this["test"].assertNotNullApply {
                assertSingle("test")
            }
        }


        val twoOfTwo: List<String> = listOf("test-1", "test-2", "asd-1", "asd-2")
        val twoOfTwoCat: Map<String, List<String>> = twoOfTwo.categorization.categorize { it: String ->
            if (it.startsWith("test")) {
                "test"
            } else {
                "asd"
            }
        }
        twoOfTwoCat.assertSize(2)
        twoOfTwoCat["test"].assertNotNullApply {
            assertSize(2)
        }
        twoOfTwoCat["asd"].assertNotNullApply {
            assertSize(2)
        }
    }

    class CollectionItemCategorizeByString {

        @Test
        fun empty() {
            listOf<String>().categorization.categorizeByString { it }.assertSize(0)
            listOf<String>().categorization.categorizeByString { "same" }.assertSize(0)

        }

        @Test
        fun single() {
            listOf("test").categorization.categorizeByString { it }.apply {
                assertSize(1)
                keys.first().assert("test")
                values.first().assertSize(1)
                values.first().first().assert("test")
            }

            listOf("test").categorization.categorizeByString { "same" }.apply {
                assertSize(1)
                keys.first().assert("same")
                values.first().assertSize(1)
                values.first().first().assert("test")
            }
        }

        @Test
        fun multiple() {
            listOf("test", "1234").categorization.categorizeByString { "constantKey" }.apply {
                assertSize(1)
                keys.first().assert("constantKey")
                values.assertSingle { it: List<String> ->
                    it.assertSize(2)
                    it.assertContainsAll(listOf("test", "1234"))
                }
            }
            listOf("test", "1234").categorization.categorizeByString { it }.apply {
                assertSize(2)
                keys.assertContainsAll("1234", "test")
                values.assertContainsAll(listOf("1234"), listOf("test"))
            }
        }
    }


}