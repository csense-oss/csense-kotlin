package csense.kotlin.extensions.progressions

import csense.kotlin.tests.assertions.*
import kotlin.test.*

class IntProgressionTest {
    
    @Test
    fun intProgressionToIntArray() {
        IntProgression.fromClosedRange(0, 9, 1) //[0, .. 9]
                .toIntArray().apply {
                    assertSize(10)
                    first().assert(0)
                    last().assert(9)
                }
        
        IntProgression.fromClosedRange(0, 9, 5)
                .toIntArray().apply {
                    assertSize(2)
                    first().assert(0)
                    last().assert(5)
                }
    }
    
    @Test
    fun intProgressionLength() {
        IntProgression.fromClosedRange(0, -50, 1).length.assert(-49)
        IntProgression.fromClosedRange(1, 0, 1).length.assert(0)
        IntProgression.fromClosedRange(0, 0, 1).length.assert(1)
        IntProgression.fromClosedRange(0, 1, 1).length.assert(2)
        IntProgression.fromClosedRange(0, 9, 5).length.assert(2)
        IntProgression.fromClosedRange(0, 9, 1).length.assert(10)
    }
    
    class IntProgressionSkip{
        @Test
        fun empty() {
            IntProgression.fromClosedRange(0, 0, 1).skip(0).apply {
                first.assert(0)
                step.assert(1)
                last.assert(0)
            }
            IntProgression.fromClosedRange(5, 10, 1).skip(0).apply {
                first.assert(5)
                step.assert(1)
                last.assert(10)
            }
        }
        
        
        @Test
        fun singlePositive() {
            IntProgression.fromClosedRange(0, 10, 1).skip(2).apply {
                first.assert(2)
                step.assert(1)
                last.assert(10)
            }
        }
        
        @Test
        fun singleNegative() {
            //should return the original as we are a "negative" length range.
            IntProgression.fromClosedRange(0, -10, 1).skip(2).apply {
                first.assert(0)
                step.assert(1)
                last.assert(-10)
            }
        }
        
        @Test
        fun respectsSteps() {
            IntProgression.fromClosedRange(0, 50, 5).skip(5).apply {
                first.assert(25)
                step.assert(5)
                last.assert(50)
            }
        
        }
    }
}