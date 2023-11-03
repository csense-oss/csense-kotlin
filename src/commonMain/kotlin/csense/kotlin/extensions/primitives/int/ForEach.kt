@file:Suppress("unused", "NOTHING_TO_INLINE")

package csense.kotlin.extensions.primitives.int

import csense.kotlin.*
import csense.kotlin.annotations.numbers.*


/**
 * Does the given action this values times
 * if this value is less than or equal to 0, then the action is not ran
 * @receiver [Int]
 * @param action [FunctionUnit]<[Int]> the action to perform each this values (positive) times
 */
@IntLimit(from = 0)
public inline fun Int.forEach(action: FunctionUnit<Int>) {
    if (this.isNegativeOrZero) {
        return
    }
    for (i: Int in 0 until this) {
        action(i)
    }
}
