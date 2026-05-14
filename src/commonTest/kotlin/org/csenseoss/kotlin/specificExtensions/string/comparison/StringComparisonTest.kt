package org.csenseoss.kotlin.specificExtensions.string.comparison

import org.csenseoss.kotlin.tests.assertions.primitives.boolean.assertFalse
import org.csenseoss.kotlin.tests.assertions.primitives.boolean.assertTrue
import kotlin.test.Test

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

    class ContainsStringEndingAt {
        @Test
        fun other() {
            "".comparison.containsStringEndingAt(endIndex = 0, other = "", ignoreCase = false)
                .assertFalse("everything is out of bounds")
            "a".comparison.containsStringEndingAt(endIndex = 0, other = "a", ignoreCase = false).assertTrue()

            "b".comparison.containsStringEndingAt(endIndex = 0, other = "a", ignoreCase = false).assertFalse()
            "a".comparison.containsStringEndingAt(endIndex = 0, other = "b", ignoreCase = false).assertFalse()

            "abc".comparison.containsStringEndingAt(endIndex = 0, "a", ignoreCase = false).assertTrue()
            "abc".comparison.containsStringEndingAt(endIndex = 1, "b", ignoreCase = false).assertTrue()
            "abc".comparison.containsStringEndingAt(endIndex = 2, "c", ignoreCase = false).assertTrue()

            "abc".comparison.containsStringEndingAt(endIndex = 0, "A", ignoreCase = false).assertFalse()
            "abc".comparison.containsStringEndingAt(endIndex = 1, "B", ignoreCase = false).assertFalse()
            "abc".comparison.containsStringEndingAt(endIndex = 2, "C", ignoreCase = false).assertFalse()

            "abc".comparison.containsStringEndingAt(endIndex = 0, "A", ignoreCase = true).assertTrue()
            "abc".comparison.containsStringEndingAt(endIndex = 1, "B", ignoreCase = true).assertTrue()
            "abc".comparison.containsStringEndingAt(endIndex = 2, "C", ignoreCase = true).assertTrue()


            "abc".comparison.containsStringEndingAt(endIndex = 2, "BC", ignoreCase = true).assertTrue()
            "abc".comparison.containsStringEndingAt(endIndex = 1, "BC", ignoreCase = true).assertFalse()

            "abc bc".comparison.containsStringEndingAt(endIndex = 2, "BC", ignoreCase = true).assertTrue()
            "abc bc".comparison.containsStringEndingAt(endIndex = 5, "BC", ignoreCase = true).assertTrue()


        }
    }

    class IsEqual {
        @Test
        fun empty() {

            "".comparison.isEqual(
                startingIndexInThisString = 0,
                other = "",
                startIndexInOtherString = 0,
                length = 0,
                ignoreCase = false
            ).assertTrue()
            "".comparison.isEqual(
                startingIndexInThisString = 0,
                other = "",
                startIndexInOtherString = 0,
                length = 0,
                ignoreCase = true
            ).assertTrue()
        }

        @Test
        fun equalLengthIndexes() {

            "test".comparison.isEqual(
                startingIndexInThisString = 0,
                other = "test",
                startIndexInOtherString = 0,
                length = 0,
                ignoreCase = false
            ).assertTrue()

            "test".comparison.isEqual(
                startingIndexInThisString = 0,
                other = "1234",
                startIndexInOtherString = 0,
                length = 4,
                ignoreCase = false
            ).assertFalse()

            "test".comparison.isEqual(
                startingIndexInThisString = 0,
                other = "test",
                startIndexInOtherString = 0,
                length = 3,
                ignoreCase = false
            ).assertTrue()

            "test".comparison.isEqual(
                startingIndexInThisString = 0,
                other = "tesX",
                startIndexInOtherString = 0,
                length = 3,
                ignoreCase = false
            ).assertTrue()

            "tesY".comparison.isEqual(
                startingIndexInThisString = 0,
                other = "tesX",
                startIndexInOtherString = 0,
                length = 3,
                ignoreCase = false
            ).assertTrue()

            "asd".comparison.isEqual(
                startingIndexInThisString = 0,
                other = "000",
                startIndexInOtherString = 0,
                length = 0,
                ignoreCase = false
            ).assertTrue()


        }

        @Test
        fun differentIndexAndLengths(){
            "1234".comparison.isEqual(
                startingIndexInThisString = 0,
                other = "1",
                startIndexInOtherString = 0,
                length = 1,
                ignoreCase = false
            ).assertTrue()

            " asd".comparison.isEqual(
                startingIndexInThisString = 1,
                other = "asd",
                startIndexInOtherString = 0,
                length = 3,
                ignoreCase = false
            ).assertTrue()

            "asd".comparison.isEqual(
                startingIndexInThisString = 0,
                other = "123-asd",
                startIndexInOtherString = 4,
                length = 3,
                ignoreCase = false
            ).assertTrue()
        }
    }

}