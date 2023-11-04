package csense.kotlin.extensions.collections.array.generic

import csense.kotlin.tests.assertions.*
import kotlin.test.*

class GetTest {

    class GetOr {
        @Test
        fun empty() {
            val empty: Array<String> = arrayOf()
            empty.getOr(index = 0, defaultValue = "defaultValue").assert("defaultValue")
            empty.getOr(index = 1, defaultValue = "defaultValue").assert("defaultValue")
        }

        @Test
        fun single() {
            val single: Array<String> = arrayOf("value")
            single.getOr(index = 0, defaultValue = "defaultValue").assert("value")
            single.getOr(index = 1, defaultValue = "defaultValue").assert("defaultValue")
        }

        @Test
        fun multiple() {
            val multi: Array<String> = arrayOf("value", "1234")
            multi.getOr(index = 0, defaultValue = "defaultValue").assert("value")
            multi.getOr(index = 1, defaultValue = "defaultValue").assert("1234")
            multi.getOr(index = 2, defaultValue = "defaultValue").assert("defaultValue")
        }

    }
}