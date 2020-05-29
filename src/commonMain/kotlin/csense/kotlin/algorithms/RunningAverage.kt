@file:Suppress("unused", "NOTHING_TO_INLINE", "MemberVisibilityCanBePrivate")

package csense.kotlin.algorithms

import csense.kotlin.annotations.numbers.IntLimit
import csense.kotlin.annotations.numbers.LongLimit

/**
 *
 * The base class for handling calculating the average, with the ability to add more values to it
 * /akk the running average.
 *
 */
@Deprecated("Will be removed in 0.40, as it will be placed in either datastructures / algorithm module", level = DeprecationLevel.WARNING)
abstract class RunningAverageAbstract<T : Number> {

    /**
     *
     */
    @LongLimit(from = 0)
    private var numberCount: Long = 0

    /**
     *
     */
    private var aggregatedValue: T
    /**
     * The neutral element
     */
    protected abstract val zero: T

    /**
     *
     * @param first T
     * @param second T
     * @return T
     */
    protected abstract fun addValues(first: T, second: T): T

    init {
        @Suppress("LeakingThis")
        aggregatedValue = zero
    }

    /**
     *
     * @param newValue T
     */
    fun addValue(newValue: T) {
        aggregatedValue = addValues(aggregatedValue, newValue)
        numberCount += 1
    }

    /**
     *
     */
    val average: Double
        get() = aggregatedValue.toDouble() / numberCount

    /**
     *
     */
    fun reset() {
        aggregatedValue = zero
        numberCount = 0
    }
}


/**
 *
 */
@Deprecated("Will be removed in 0.40, as it will be placed in either datastructures / algorithm module", level = DeprecationLevel.WARNING)
open class RunningAverageInt : RunningAverageAbstract<Int>() {
    override fun addValues(first: Int, second: Int): Int = first + second
    override val zero: Int
        get() = 0
}

/**
 *
 */
@Deprecated("Will be removed in 0.40, as it will be placed in either datastructures / algorithm module", level = DeprecationLevel.WARNING)
open class RunningAverageDouble : RunningAverageAbstract<Double>() {
    override fun addValues(first: Double, second: Double): Double = first + second
    override val zero: Double
        get() = 0.0
}

/**
 *
 */
@Deprecated("Will be removed in 0.40, as it will be placed in either datastructures / algorithm module", level = DeprecationLevel.WARNING)
open class RunningAverageFloat : RunningAverageAbstract<Float>() {
    override fun addValues(first: Float, second: Float): Float = first + second
    override val zero: Float
        get() = 0f
}

/**
 *
 * @param T : Number
 * @property cappedNumberOfValues Int
 * @property valuesSet Int
 * @property currentIndex Int
 * @property average Double
 * @constructor
 */
@Deprecated("Will be removed in 0.40, as it will be placed in either datastructures / algorithm module", level = DeprecationLevel.WARNING)
abstract class RunningAverageCappedAbstract<T : Number>(
        @IntLimit(from = 0) private val cappedNumberOfValues: Int
) {

    /**
     *
     */
    abstract fun clearValues()

    /**
     *
     * @param item T
     * @param index Int
     */
    abstract fun setValue(item: T, index: Int)

    /**
     *
     * @param toTakeCount Int
     * @return Iterable<T>
     */
    abstract fun takeValues(toTakeCount: Int): Iterable<T>


    /**
     * How many values we have set of the available elements in the array
     */
    @IntLimit(from = 0)
    private var valuesSet = 0
    /**
     * Since the array acts as a ring buffer.
     */
    @IntLimit(from = 0)
    private var currentIndex = 0

    /**
     *
     * @param newValue T
     */
    fun addValue(newValue: T) {
        valuesSet = minOf(valuesSet + 1, cappedNumberOfValues)
        setValue(newValue, currentIndex)
        currentIndex = (currentIndex + 1).rem(cappedNumberOfValues)
    }

    /**
     *
     */
    val average: Double
        get() = takeValues(valuesSet).sumByDouble(Number::toDouble) / valuesSet

    /**
     *
     */
    fun reset() {
        valuesSet = 0
        currentIndex = 0
        clearValues()
    }

}

/**
 *
 * @property values FloatArray
 * @constructor
 */
@Deprecated("Will be removed in 0.40, as it will be placed in either datastructures / algorithm module", level = DeprecationLevel.WARNING)
open class RunningAverageFloatCapped(cappedValuesToAverage: Int) : RunningAverageCappedAbstract<Float>(cappedValuesToAverage) {

    private val values = FloatArray(cappedValuesToAverage)

    override fun takeValues(toTakeCount: Int) = values.take(toTakeCount)

    override fun setValue(item: Float, index: Int) {
        values[index] = item
    }

    override fun clearValues() = values.fill(0f)
}

/**
 *
 * @property values IntArray
 * @constructor
 */
@Deprecated("Will be removed in 0.40, as it will be placed in either datastructures / algorithm module", level = DeprecationLevel.WARNING)
open class RunningAverageIntCapped(cappedValuesToAverage: Int) : RunningAverageCappedAbstract<Int>(cappedValuesToAverage) {

    private val values = IntArray(cappedValuesToAverage)

    override fun clearValues() = values.fill(0)

    override fun setValue(item: Int, index: Int) {
        values[index] = item
    }

    override fun takeValues(toTakeCount: Int): Iterable<Int> = values.take(toTakeCount)
}

/**
 *
 * @property values DoubleArray
 * @constructor
 */
@Deprecated("Will be removed in 0.40, as it will be placed in either datastructures / algorithm module", level = DeprecationLevel.WARNING)
open class RunningAverageDoubleCapped(cappedValuesToAverage: Int) : RunningAverageCappedAbstract<Double>(cappedValuesToAverage) {

    private val values = DoubleArray(cappedValuesToAverage)

    override fun clearValues() = values.fill(0.0)


    override fun setValue(item: Double, index: Int) {
        values[index] = item
    }


    override fun takeValues(toTakeCount: Int): Iterable<Double> =
            values.take(toTakeCount)

}
