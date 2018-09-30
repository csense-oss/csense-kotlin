@file:Suppress("unused", "NOTHING_TO_INLINE")
package csense.kotlin.test.assertions

import kotlin.test.*


fun String.assertContains(value: String,
                          ignoreCase: Boolean = false,
                          message: String = "Could not find \"$value\", in  \r\n\"$this\"") {
    assertTrue(this.contains(value, ignoreCase = ignoreCase), message)
}

fun String.assertContainsNot(value: String,
                             ignoreCase: Boolean = false,
                             message: String = "") {
    assertFalse(this.contains(value, ignoreCase = ignoreCase), "$message \n Reason: Could find \"$value\", in  \r\n\"$this\"")
}

fun String.assert(value: String, message: String = "") {
    assertEquals(value, this, message)
}


fun String.assertNot(value: String, message: String = "") {
    assertNotEquals(value, this, message)
}


fun String.assertContainsInOrder(values: List<String>,
                                 ignoreCase: Boolean,
                                 message: String = "") {
    var currentIndex = 0
    values.forEach {
        val next = indexOf(it, currentIndex, ignoreCase)
        if (next < 0) {
            failTest("$message\n\nCould not find \n\t\"$it\" after index $currentIndex in string \n" +
                    "\"$this\"\n" +
                    "\tafter index is :\"${this.substring(currentIndex)}\"")
            return
        }
        currentIndex = next + it.length
    }
}

fun String.assertStartsWith(prefix: String,
                            ignoreCase: Boolean = false,
                            message: String = "") {
    val textOutput = "$message \n Could not find \"$prefix\", in  \n" +
            "\"$this\""
    assertTrue(this.startsWith(prefix, ignoreCase = ignoreCase), textOutput)
}

