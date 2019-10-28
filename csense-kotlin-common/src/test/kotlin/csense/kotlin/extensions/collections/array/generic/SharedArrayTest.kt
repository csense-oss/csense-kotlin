@file:Suppress("unused")

package csense.kotlin.extensions.collections.array.generic

import csense.kotlin.test.assertions.assert
import kotlin.test.Test

class SharedArrayTest {

    object GenericArrayForeachDiscardResult {
        @Test
        fun empty() {
            var counter = 0
            var innerCounter = 0
            GenericArray.foreachDiscardResult(
                    0,
                    { counter += 1; "$it" },
                    { t -> innerCounter += 1;t })
            counter.assert(0, "should not call when empty")
            innerCounter.assert(0, "should not call receiver when empty")
        }

        @Test
        fun single() {
            var counter = 0
            var innerCounter = 0
            GenericArray.foreachDiscardResult(
                    1,
                    { counter += 1; "$it" },
                    { t -> innerCounter += 1;t })
            counter.assert(1, "should called size times")
            innerCounter.assert(1, "should call receiver")
        }

        @Test
        fun multiple() {
            var counter = 0
            var innerCounter = 0
            GenericArray.foreachDiscardResult(
                    50,
                    { counter += 1; "$it" },
                    { t -> innerCounter += 1;t })
            counter.assert(50, "should called size times")
            innerCounter.assert(50, "should call receiver size times")
        }
    }

}