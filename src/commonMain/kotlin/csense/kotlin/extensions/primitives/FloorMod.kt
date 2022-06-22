package csense.kotlin.extensions.primitives


/**
 * Floor mod handles negative numbers like they were positive
 * @receiver Int
 * @param to Int
 * @return Int
 */
public infix fun Int.floorMod(to: Int): Int = ((this % to) + to) % to

public infix fun Long.floorMod(to: Long): Long = ((this % to) + to) % to

public infix fun Float.floorMod(to: Float): Float {
    if (isNaN()) {
        return Float.NaN
    }
    return ((this % to) + to) % to
}

public infix fun Double.floorMod(to: Double): Double {
    if (isNaN()) {
        return Double.NaN
    }
    return ((this % to) + to) % to
}
