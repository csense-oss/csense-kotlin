package csense.kotlin.extensions.collections.iterator

import csense.kotlin.tests.assertions.*
import kotlin.test.*

class FindFirstTest {
    @Test
    fun findFirst() {
        val empty: List<String> = listOf()
        empty.iterator()
            .findFirst { it == "test" }
            .assertNull()

        val single: List<String> = listOf("test2")

        single.iterator()
            .findFirst { it == "test" }
            .assertNull("nothing is test in the single collection")

        single.iterator()
            .findFirst { it == "test2" }
            .assert("test2", "should find value searching for when there")


        val multiple: List<String> = listOf("test1", "test2")

        multiple.iterator()
            .findFirst { it == "test3" }
            .assertNull()

        multiple.iterator()
            .findFirst { it == "test2" }
            .assert("test2")

        multiple.iterator()
            .findFirst { it == "test1" }
            .assert("test1")


        multiple.iterator()
            .findFirst { true }
            .assert("test1")

        var counterA = 0

        multiple.iterator()
            .findFirst {
                counterA += 1
                counterA == 2
            }
            .assert("test2")


        var counterB = 0
        multiple.iterator()
            .findFirst {
                counterB += 1
                counterB == 3
            }
            .assertNull()


    }
}