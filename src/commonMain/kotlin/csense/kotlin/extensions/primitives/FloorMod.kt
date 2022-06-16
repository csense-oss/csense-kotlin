package csense.kotlin.extensions.primitives


public infix fun Int.floorMod(to: Int): Int = ((this % to) + to) % to

public infix fun Long.floorMod(to: Long): Long = ((this % to) + to) % to

public infix fun Float.floorMod(to: Float): Float = ((this % to) + to) % to

public infix fun Double.floorMod(to: Double): Double = ((this % to) + to) % to
