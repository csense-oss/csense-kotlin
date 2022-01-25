@file:Suppress("unused", "NOTHING_TO_INLINE", "MemberVisibilityCanBePrivate")

package csense.kotlin.units

import csense.kotlin.annotations.numbers.*
import csense.kotlin.units.TimeUnit.*
import kotlinx.coroutines.*

@Deprecated("Use kotlin Duration api instead")
/**
 * Created by Kasper Tvede
 * Purpose:
 * To provide a way more safe, and simple version of TimeUnit (from java)
 *
 *
 * example of time units and the relation:
 * https://en.wikipedia.org/wiki/Unit_of_time#/media/File:Time_units.png
 *
 * @property value [Long] the value of this timeunit
 */
public sealed class TimeUnit(@LongLimit(from = 0) public val value: Long) {

    /**
     * a "value unit" [String]
     * computed lazy to avoid "expensive" strings in memory and iff used very frequently then the penalty goes towards
     *  0 for "re"computing this string.
     */
    private val internalString: String by lazy {
        "$value $prefix"
    }

    /**
     * What kind of "Unit" (seconds => "s", ect)
     */
    public abstract val prefix: String

    /**
     * Converts this time unit to a [MilliSeconds] instance
     * @return [MilliSeconds]
     */
    public abstract fun toMilliSeconds(): MilliSeconds

    /**
     * Converts this time unit to the [String] "value Unit"
     * @return [String]
     */
    override fun toString(): String = internalString


    /**
     * A measure of nanoseconds
     * @constructor
     */
    public class NanoSeconds(@LongLimit(from = 0) nanoSeconds: Long) : TimeUnit(nanoSeconds) {

        override val prefix: String = "ns"

        override fun toMilliSeconds(): MilliSeconds =
            MilliSeconds(value / milliSecondsToNanoSecondsMultiplier)

        public operator fun plus(other: NanoSeconds): NanoSeconds {
            return NanoSeconds(this.value + other.value)
        }

        public operator fun minus(other: NanoSeconds): NanoSeconds {
            return NanoSeconds(this.value - other.value)
        }

        public operator fun times(other: NanoSeconds): NanoSeconds {
            return NanoSeconds(this.value * other.value)
        }

        public operator fun div(other: NanoSeconds): NanoSeconds {
            return NanoSeconds(this.value / other.value)
        }

    }

    /**
     * A measure of milliseconds
     * @constructor
     */
    public class MilliSeconds(@LongLimit(from = 0) milliseconds: Long) : TimeUnit(milliseconds) {

        override val prefix: String = "ms"

        override fun toMilliSeconds(): MilliSeconds = this

        public operator fun plus(other: MilliSeconds): MilliSeconds {
            return MilliSeconds(this.value + other.value)
        }

        public operator fun minus(other: MilliSeconds): MilliSeconds {
            return MilliSeconds(this.value - other.value)
        }

        public operator fun times(other: MilliSeconds): MilliSeconds {
            return MilliSeconds(this.value * other.value)
        }

        public operator fun div(other: MilliSeconds): MilliSeconds {
            return MilliSeconds(this.value / other.value)
        }
    }

    /**
     * A measure of seconds
     * @constructor
     */
    public class Seconds(@LongLimit(from = 0) seconds: Long) : TimeUnit(seconds) {
        override val prefix: String = "s"

        override fun toMilliSeconds(): MilliSeconds =
            MilliSeconds(value * secondsToMillisecondsMultiplier)

        public operator fun plus(other: Seconds): Seconds {
            return Seconds(this.value + other.value)
        }

        public operator fun minus(other: Seconds): Seconds {
            return Seconds(this.value - other.value)
        }

        public operator fun times(other: Seconds): Seconds {
            return Seconds(this.value * other.value)
        }

        public operator fun div(other: Seconds): Seconds {
            return Seconds(this.value / other.value)
        }
    }

    /**
     * A measure of minutes
     * ( 60 minutes in a second).
     * @constructor
     */
    public class Minutes(@LongLimit(from = 0) minutes: Long) : TimeUnit(minutes) {

        override val prefix: String = "m"

        override fun toMilliSeconds(): MilliSeconds {
            return MilliSeconds(
                value *
                        (minutesToSecondsMultiplier *
                                secondsToMillisecondsMultiplier)
            )
        }

        public operator fun plus(other: Minutes): Minutes {
            return Minutes(this.value + other.value)
        }

        public operator fun minus(other: Minutes): Minutes {
            return Minutes(this.value - other.value)
        }

        public operator fun times(other: Minutes): Minutes {
            return Minutes(this.value * other.value)
        }

        public operator fun div(other: Minutes): Minutes {
            return Minutes(this.value / other.value)
        }
    }

    /**
     * A measurement of hours
     * The classic 60 minutes => 1 hour
     * @constructor
     */
    public class Hours(@LongLimit(from = 0) hours: Long) : TimeUnit(hours) {

        override val prefix: String = "h"

        override fun toMilliSeconds(): MilliSeconds {
            return MilliSeconds(
                value *
                        (hoursToMinutesMultiplier *
                                minutesToSecondsMultiplier *
                                secondsToMillisecondsMultiplier)
            )
        }

        public operator fun plus(other: Hours): Hours {
            return Hours(this.value + other.value)
        }

        public operator fun minus(other: Hours): Hours {
            return Hours(this.value - other.value)
        }

        public operator fun times(other: Hours): Hours {
            return Hours(this.value * other.value)
        }

        public operator fun div(other: Hours): Hours {
            return Hours(this.value / other.value)
        }
    }

    /**
     * A measurement of days
     * in terms of 24 hours.
     * @property prefix [String]
     * @constructor
     */
    public class Days(@LongLimit(from = 0) days: Long) : TimeUnit(days) {

        override val prefix: String = "d"

        override fun toMilliSeconds(): MilliSeconds {
            return MilliSeconds(
                value *
                        (daysToHoursMultiplier *
                                hoursToMinutesMultiplier *
                                minutesToSecondsMultiplier *
                                secondsToMillisecondsMultiplier)
            )
        }

        public operator fun plus(other: Days): Days {
            return Days(this.value + other.value)
        }

        public operator fun minus(other: Days): Days {
            return Days(this.value - other.value)
        }

        public operator fun times(other: Days): Days {
            return Days(this.value * other.value)
        }

        public operator fun div(other: Days): Days {
            return Days(this.value / other.value)
        }
    }

}


//region conversion values
/**
 * The scaling factor from [Days] to [Hours]
 */
private val TimeUnit.daysToHoursMultiplier: Long
    get() = 24


/**
 * The scaling factor from [Hours] to [Minutes]
 */
private val TimeUnit.hoursToMinutesMultiplier: Long
    get() = 60

/**
 * The scaling factor from [Minutes] to [Seconds]
 */
private val TimeUnit.minutesToSecondsMultiplier: Long
    get() = 60

/**
 * The scaling factor from [Seconds] to  [MilliSeconds]
 */
private val TimeUnit.secondsToMillisecondsMultiplier: Long
    get() = 1_000

/**
 * The scaling factor from [MilliSeconds] to [NanoSeconds]
 */
private val TimeUnit.milliSecondsToNanoSecondsMultiplier: Long
    get() = 1_000_000

//endregion

//region all conversions from nanoseconds
/**
 * Converts nanoseconds to seconds
 * @receiver [NanoSeconds]
 * @return [Seconds]
 */
public fun NanoSeconds.toSeconds(): Seconds =
    toMilliSeconds().toSeconds()

/**
 * Converts nanoseconds to Minutes
 * @receiver [NanoSeconds]
 * @return [Minutes]
 */
public fun NanoSeconds.toMinutes(): Minutes =
    toSeconds().toMinutes()

/**
 * Converts nanoseconds to hours
 * @receiver [NanoSeconds]
 * @return [Hours]
 */
public fun NanoSeconds.toHours(): Hours =
    toMinutes().toHours()

/**
 * Converts nanoseconds to days
 * @receiver [NanoSeconds]
 * @return [Days]
 */
public fun NanoSeconds.toDays(): Days =
    toHours().toDays()

//endregion


//region all conversions from milliseconds
/**
 * Converts Milliseconds to nanoseconds
 * @receiver [MilliSeconds]
 * @return [NanoSeconds]
 */
public fun MilliSeconds.toNanoSeconds(): NanoSeconds =
    NanoSeconds(value * milliSecondsToNanoSecondsMultiplier)

/**
 * Converts Milliseconds to Seconds
 * @receiver [MilliSeconds]
 * @return [Seconds]
 */
public fun MilliSeconds.toSeconds(): Seconds =
    Seconds(value / secondsToMillisecondsMultiplier)

/**
 * Converts Milliseconds to minutes
 * @receiver [MilliSeconds]
 * @return [Minutes]
 */
public fun MilliSeconds.toMinutes(): Minutes =
    toSeconds().toMinutes()

/**
 * Converts Milliseconds to hours
 * @receiver [MilliSeconds]
 * @return [Hours]
 */
public fun MilliSeconds.toHours(): Hours =
    toMinutes().toHours()

/**
 * Converts Milliseconds to days
 * @receiver [MilliSeconds]
 * @return [Days]
 */
public fun MilliSeconds.toDays(): Days =
    toHours().toDays()


//endregion

//region all conversions from seconds

/**
 * Converts Seconds to nanoseconds
 * @receiver [Seconds]
 * @return [NanoSeconds]
 */
public fun Seconds.toNanoSeconds(): NanoSeconds =
    toMilliSeconds().toNanoSeconds()

/**
 * Converts Seconds to minutes
 * @receiver [Seconds]
 * @return [Minutes]
 */
public fun Seconds.toMinutes(): Minutes =
    Minutes(value / minutesToSecondsMultiplier)

/**
 * Converts Seconds to hours
 * @receiver [Seconds]
 * @return [Hours]
 */
public fun Seconds.toHours(): Hours =
    toMinutes().toHours()

/**
 * Converts Seconds to days
 * @receiver [Seconds]
 * @return [Days]
 */
public fun Seconds.toDays(): Days =
    toHours().toDays()


//endregion

//region all conversions from minutes
/**
 * Converts minutes to nanoseconds
 * @receiver [Minutes]
 * @return [NanoSeconds]
 */
public fun Minutes.toNanoSeconds(): NanoSeconds =
    toMilliSeconds().toNanoSeconds()

/**
 * Converts minutes to seconds
 * @receiver [Minutes]
 * @return [Seconds]
 */
public fun Minutes.toSeconds(): Seconds =
    Seconds(value * minutesToSecondsMultiplier)

/**
 * Converts minutes to hours
 * @receiver [Minutes]
 * @return [Hours]
 */
public fun Minutes.toHours(): Hours =
    Hours(value / hoursToMinutesMultiplier)

/**
 * Converts minutes to days
 * @receiver [Minutes]
 * @return [Days]
 */
public fun Minutes.toDays(): Days =
    toHours().toDays()


//endregion

//region all conversions from hours
/**
 * Converts hours to nanoseconds
 * @receiver [Hours]
 * @return [NanoSeconds]
 */
public fun Hours.toNanoSeconds(): NanoSeconds =
    toMilliSeconds().toNanoSeconds()

/**
 * Converts hours to seconds
 * @receiver [Hours]
 * @return [Seconds]
 */
public fun Hours.toSeconds(): Seconds =
    toMinutes().toSeconds()

/**
 * Converts hours to minutes
 * @receiver [Hours]
 * @return [Minutes]
 */
public fun Hours.toMinutes(): Minutes =
    Minutes(value * hoursToMinutesMultiplier)

/**
 * Converts hours to days
 * @receiver [Hours]
 * @return [Days]
 */
public fun Hours.toDays(): Days =
    Days(value / daysToHoursMultiplier)

//endregion

//region all conversions from days
/**
 * Converts days to nanoseconds
 * @receiver [Days]
 * @return [NanoSeconds]
 */
public fun Days.toNanoSeconds(): NanoSeconds =
    toMilliSeconds().toNanoSeconds()

/**
 * Converts days to seconds
 * @receiver [Days]
 * @return [Seconds]
 */
public fun Days.toSeconds(): Seconds =
    toMinutes().toSeconds()

/**
 * Converts days to minutes
 * @receiver [Days]
 * @return [Minutes]
 */
public fun Days.toMinutes(): Minutes =
    toHours().toMinutes()

/**
 * Converts days to hours
 * @receiver [Days]
 * @return [Hours]
 */
public fun Days.toHours(): Hours =
    Hours(value * daysToHoursMultiplier)


//endregion


/**
 * Sleeps (coroutine) this time unit
 */
public suspend inline fun TimeUnit.delay() {
    delay(this.toMilliSeconds().value)
}
