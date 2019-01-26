package csense.kotlin.algorithms

import csense.kotlin.test.assertions.*
import kotlin.test.*

class ComparisonKtTest {

    @Test
    fun testToComparing() {
        0.toComparing().assert(Comparing.Equal)
        // -1 means that x > y thus the comparison is that x is larger than y (y is less than x)
        (-1).toComparing().assert(Comparing.LessThan)
        // 1 means that x < y thus the comparison is that y is larger than x.( y is larger than x)
        1.toComparing().assert(Comparing.LargerThan)
        Int.MAX_VALUE.toComparing().assert(Comparing.LargerThan)
        Int.MIN_VALUE.toComparing().assert(Comparing.LessThan)
    }
}
