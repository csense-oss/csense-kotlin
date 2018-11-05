package csense.kotlin.extensions.iterator

import csense.kotlin.test.assertions.*
import org.junit.Test

import org.junit.Assert.*

class IteratorKtTest {

    @Test
    fun findFirst() {
        val empty = listOf<String>()
        empty.iterator()
                .findFirst { it == "test" }
                .assertNull()

        val single = listOf("test2")

        single.iterator()
                .findFirst { it == "test" }
                .assertNull("nothing is test in the single collection")

        single.iterator()
                .findFirst { it == "test2" }
                .assertNotNullAndEquals("test2", "should find value searching for when there")


        val multiple = listOf("test1", "test2")

        multiple.iterator()
                .findFirst { it == "test3" }
                .assertNull()

        multiple.iterator()
                .findFirst { it == "test2" }
                .assertNotNullAndEquals("test2")

        multiple.iterator()
                .findFirst { it == "test1" }
                .assertNotNullAndEquals("test1")


        multiple.iterator()
                .findFirst { true }
                .assertNotNullAndEquals("test1")

        var counterA = 0

        multiple.iterator()
                .findFirst {
                    counterA += 1
                    counterA == 2
                }
                .assertNotNullAndEquals("test2")


        var counterB = 0
        multiple.iterator()
                .findFirst {
                    counterB += 1
                    counterB == 3
                }
                .assertNull()


    }
}