package csense.kotlin.specificExtensions.string

import csense.kotlin.tests.assertions.*
import kotlin.test.*

class StringComparisonTest {
    class StringComparisonContainsStringAt {

        @Test
        fun shouldBeFalseWhenOutOfBounds() {
            //sanity test
            "a".comparison.containsStringAt(startIndex = 0, other = "a", ignoreCase = false).assertTrue()

            "abc".comparison.containsStringAt(startIndex = (-1), other = "a", ignoreCase = false)
                .assertFalse("out of bounds")
            "a".comparison.containsStringAt(startIndex = 1, other = "a", ignoreCase = false)
                .assertFalse("out of bounds")
            "".comparison.containsStringAt(startIndex = 42, other = "", ignoreCase = false)
                .assertFalse("out of bounds should be before other is empty check")
        }


        @Test
        fun other() {
            "".comparison.containsStringAt(startIndex = 0, other = "", ignoreCase = false)
                .assertFalse("everything is out of bounds")
            "a".comparison.containsStringAt(startIndex = 0, other = "a", ignoreCase = false).assertTrue()

            "b".comparison.containsStringAt(startIndex = 0, other = "a", ignoreCase = false).assertFalse()
            "a".comparison.containsStringAt(startIndex = 0, other = "b", ignoreCase = false).assertFalse()

            "abc".comparison.containsStringAt(startIndex = 0, "a", ignoreCase = false).assertTrue()
            "abc".comparison.containsStringAt(startIndex = 1, "b", ignoreCase = false).assertTrue()
            "abc".comparison.containsStringAt(startIndex = 2, "c", ignoreCase = false).assertTrue()

            "abc".comparison.containsStringAt(startIndex = 0, "A", ignoreCase = false).assertFalse()
            "abc".comparison.containsStringAt(startIndex = 1, "B", ignoreCase = false).assertFalse()
            "abc".comparison.containsStringAt(startIndex = 2, "C", ignoreCase = false).assertFalse()

            "abc".comparison.containsStringAt(startIndex = 0, "A", ignoreCase = true).assertTrue()
            "abc".comparison.containsStringAt(startIndex = 1, "B", ignoreCase = true).assertTrue()
            "abc".comparison.containsStringAt(startIndex = 2, "C", ignoreCase = true).assertTrue()

        }
    }

    class ContainsStringEndingAt{
        @Test
        fun hmm(){
            TODO()
        }
    }

    class CompareTo{
        @Test
        fun hmm(){
            TODO()
        }
    }

}