package csense.kotlin.extensions.collections.array.generic

import csense.kotlin.tests.assertions.*
import kotlin.test.*

class GetTest {

    @Test
    fun empty() {
        val array: Array<String> = arrayOf()
        array.getOr(index = 0, defaultValue = "default").assert("default")
        array.getOr(index = -1, defaultValue = "default").assert("default")
        array.getOr(index = 1, defaultValue = "default").assert("default")
    }


    @Test
    fun single() {
        val array: Array<String> = arrayOf("a")
        array.getOr(index = 0, defaultValue = "default").assert("a")
        array.getOr(index = -1, defaultValue = "default").assert("default")
        array.getOr(index = 1, defaultValue = "default").assert("default")
    }


    @Test
    fun multiple() {
        val array: Array<String> = arrayOf("a", "1")
        array.getOr(index = 0, defaultValue = "default").assert("a")
        array.getOr(index = -1, defaultValue = "default").assert("default")
        array.getOr(index = -2, defaultValue = "default").assert("default")
        array.getOr(index = 1, defaultValue = "default").assert("1")
        array.getOr(index = 2, defaultValue = "default").assert("default")
    }
}