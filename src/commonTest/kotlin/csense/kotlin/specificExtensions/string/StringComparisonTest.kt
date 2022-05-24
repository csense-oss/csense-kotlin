package csense.kotlin.specificExtensions.string

import csense.kotlin.tests.assertions.*
import kotlin.test.*

class StringComparisonTest {
    class StringComparisonContainsStringAt {

        @Test
        fun shouldBeFalseWhenOutOfBounds() {
            //sanity test
            "a".comparison.containsStringAt(0, "a", false).assertTrue()

            "abc".comparison.containsStringAt((-1), "a", false).assertFalse("out of bounds")
            "a".comparison.containsStringAt(1, "a", false).assertFalse("out of bounds")
            "".comparison.containsStringAt(42, "", false)
                .assertFalse("out of bounds should be before other is empty check")
        }


        @Test
        fun other() {
            "".comparison.containsStringAt(0, "", false).assertFalse("everything is out of bounds")
            "a".comparison.containsStringAt(0, "a", false).assertTrue()

            "b".comparison.containsStringAt(0, "a", false).assertFalse()
            "a".comparison.containsStringAt(0, "b", false).assertFalse()

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

    class StringComparisonCompareTo {

        @Test
        fun stringComparisonCompareTo() {
            StringComparison("").compareTo(0, "", 0, 0, false)
        }


        @Test
        fun startingIndexInThisString() {
            StringComparison("").compareTo((-1), "", 0, 0, false)
            StringComparison("").compareTo(0, "", 0, 0, false)
            StringComparison("").compareTo(1, "", 0, 0, false)
            StringComparison("").compareTo((-50), "", 0, 0, false)
            StringComparison("").compareTo(42, "", 0, 0, false)
        }


        @Test
        fun other() {
            StringComparison("").compareTo(0, "", 0, 0, false)
            StringComparison("").compareTo(0, " ", 0, 0, false)
            StringComparison("").compareTo(0, "a", 0, 0, false)
        }


        @Test
        fun startIndexInOtherString() {
            StringComparison("").compareTo(0, "", (-1), 0, false)
            StringComparison("").compareTo(0, "", 0, 0, false)
            StringComparison("").compareTo(0, "", 1, 0, false)
            StringComparison("").compareTo(0, "", (-50), 0, false)
            StringComparison("").compareTo(0, "", 42, 0, false)
        }


        @Test
        fun length() {
            StringComparison("").compareTo(0, "", 0, (-1), false)
            StringComparison("").compareTo(0, "", 0, 0, false)
            StringComparison("").compareTo(0, "", 0, 1, false)
            StringComparison("").compareTo(0, "", 0, (-50), false)
            StringComparison("").compareTo(0, "", 0, 42, false)
        }


        @Test
        fun ignoreCase() {
            StringComparison("").compareTo(0, "", 0, 0, false)
            StringComparison("").compareTo(0, "", 0, 0, true)
        }

    }

    class StringComparisonContainsStringEndingAt {

        @Test
        fun stringComparisonContainsStringEndingAt() {
            StringComparison("").containsStringEndingAt(0, "", false)
        }


        @Test
        fun endIndex() {
            StringComparison("").containsStringEndingAt((-1), "", false)
            StringComparison("").containsStringEndingAt(0, "", false)
            StringComparison("").containsStringEndingAt(1, "", false)
            StringComparison("").containsStringEndingAt((-50), "", false)
            StringComparison("").containsStringEndingAt(42, "", false)
        }


        @Test
        fun other() {
            StringComparison("").containsStringEndingAt(0, "", false)
            StringComparison("").containsStringEndingAt(0, " ", false)
            StringComparison("").containsStringEndingAt(0, "a", false)
        }


        @Test
        fun ignoreCase() {
            StringComparison("").containsStringEndingAt(0, "", false)
            StringComparison("").containsStringEndingAt(0, "", true)
        }

    }
}