package csense.kotlin.algorithms

import csense.kotlin.test.assertions.*
import kotlin.test.*

class ComparisonKtTest {

    @Test
    fun testToComparing() {
        0.toComparing().assert(ItemComparison.Equal)
        // -1 means that x > y thus the comparison is that x is larger than y (y is less than x)
        (-1).toComparing().assert(ItemComparison.LessThan)
        // 1 means that x < y thus the comparison is that y is larger than x.( y is larger than x)
        1.toComparing().assert(ItemComparison.LargerThan)
        Int.MAX_VALUE.toComparing().assert(ItemComparison.LargerThan)
        Int.MIN_VALUE.toComparing().assert(ItemComparison.LessThan)
    }

    @Test
    fun compareToRangeFromInclusive() {
        0.compareToRange(0, 1).assert(ItemComparison.Equal)
        1.compareToRange(0, 1).assert(ItemComparison.LargerThan)
        (-1).compareToRange(0, 1).assert(ItemComparison.LessThan)
        (2).compareToRange(0, 1).assert(ItemComparison.LargerThan)
    }

    @Test
    fun compareToRangeIntRange() {
        (-1).compareToRange(0 until 5).assert(ItemComparison.LessThan)
        0.compareToRange(0 until 5).assert(ItemComparison.Equal)
        1.compareToRange(0 until 5).assert(ItemComparison.Equal)
        2.compareToRange(0 until 5).assert(ItemComparison.Equal)
        3.compareToRange(0 until 5).assert(ItemComparison.Equal)
        4.compareToRange(0 until 5).assert(ItemComparison.Equal)
        5.compareToRange(0 until 5).assert(ItemComparison.LargerThan)
    }
}
