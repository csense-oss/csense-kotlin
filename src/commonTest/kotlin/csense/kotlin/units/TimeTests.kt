@file:Suppress("NOTHING_TO_INLINE", "DEPRECATION")
@file:OptIn(ExperimentalCoroutinesApi::class)

package csense.kotlin.units

import csense.kotlin.tests.assertions.*
import kotlinx.coroutines.*
import kotlinx.coroutines.test.*
import kotlin.test.*
import kotlin.time.*


class TimeTests {

    @Test
    fun nanoSecondsToSeconds() {
        TimeUnit.NanoSeconds(0L).toSeconds().assert(0L)
        TimeUnit.NanoSeconds(1_000_000_000L).toSeconds().assert(1L)
        TimeUnit.NanoSeconds(10_000_000_000L).toSeconds().assert(10L)
        TimeUnit.NanoSeconds(100_000_000_000L).toSeconds().assert(100L)
    }

    @Test
    fun nanoSecondsToMinutes() {
        TimeUnit.NanoSeconds(0L).toMinutes().assert(0L)
        TimeUnit.NanoSeconds(10_000_000_000L).toMinutes().assert(0L)
        TimeUnit.NanoSeconds(100_000_000_000L).toMinutes().assert(1L)
        TimeUnit.NanoSeconds(120_000_000_000L).toMinutes().assert(2L)
    }


    @Test
    fun nanoSecondsToHours() {
        TimeUnit.NanoSeconds(0L).toHours().assert(0L)
        TimeUnit.NanoSeconds(120_000_000_000L).toHours().assert(0L)
        TimeUnit.NanoSeconds(60_000_000_000L * 60L).toHours().assert(1L)
        TimeUnit.NanoSeconds(60_000_000_000L * 60L * 8L).toHours().assert(8L)
        TimeUnit.NanoSeconds(60_000_000_000L * 60L * 24L).toHours().assert(24L)
    }

    @Test
    fun nanoSecondsToDays() {
        TimeUnit.NanoSeconds(120_000_000_000L).toDays().assert(0L)
        TimeUnit.NanoSeconds(60_000_000_000L).toDays().assert(0L)
        TimeUnit.NanoSeconds(60_000_000_000L * 60L * 24L).toDays().assert(1L)
        TimeUnit.NanoSeconds(60_000_000_000L * 60L * 24L * 7L).toDays().assert(7L)
    }


    @Test
    fun milliSecondsToNanoSeconds() {
        TimeUnit.MilliSeconds(1L).toNanoSeconds().assert(1_000_000L)
        TimeUnit.MilliSeconds(0L).toNanoSeconds().assert(0L)
        TimeUnit.MilliSeconds(1000L).toNanoSeconds().assert(1_000_000_000L)
    }

    @Test
    fun milliSecondsToSeconds() {
        TimeUnit.MilliSeconds(0L).toSeconds().assert(0L)
        TimeUnit.MilliSeconds(500L).toSeconds().assert(0L)
        TimeUnit.MilliSeconds(1000L).toSeconds().assert(1L)
        TimeUnit.MilliSeconds(60_000L).toSeconds().assert(60L)
    }

    @Test
    fun milliSecondsToMinutes() {
        TimeUnit.MilliSeconds(0L).toMinutes().assert(0L)
        TimeUnit.MilliSeconds(5_000L).toMinutes().assert(0L)
        TimeUnit.MilliSeconds(60_000L).toMinutes().assert(1L)
        TimeUnit.MilliSeconds(60_000L * 5L).toMinutes().assert(5L)
    }

    @Test
    fun milliSecondsToHours() {
        TimeUnit.MilliSeconds(0L).toHours().assert(0L)
        TimeUnit.MilliSeconds(60_000).toHours().assert(0L)
        TimeUnit.MilliSeconds(60_000L * 60L).toHours().assert(1L)
        TimeUnit.MilliSeconds(60_000L * 60L * 10L).toHours().assert(10L)
    }

    @Test
    fun milliSecondsToDays() {
        TimeUnit.MilliSeconds(0L).toDays().assert(0L)
        TimeUnit.MilliSeconds(60_000L * 60L * 24L).toDays().assert(1L)
        TimeUnit.MilliSeconds(60_000L * 60L * 24L * 7L).toDays().assert(7L)
    }

    @Test
    fun secondsToNanoSeconds() {
        TimeUnit.Seconds(0L).toNanoSeconds().assert(0L)
        TimeUnit.Seconds(1L).toNanoSeconds().assert(1_000_000_000L)
        TimeUnit.Seconds(60L).toNanoSeconds().assert(60_000_000_000L)
    }

    @Test
    fun secondsToMinutes() {
        TimeUnit.Seconds(0L).toMinutes().assert(0L)
        TimeUnit.Seconds(30L).toMinutes().assert(0L)
        TimeUnit.Seconds(60L).toMinutes().assert(1L)
        TimeUnit.Seconds(121L).toMinutes().assert(2L)
    }

    @Test
    fun secondsToHours() {
        TimeUnit.Seconds(60L).toHours().assert(0L)
        TimeUnit.Seconds(60L * 60L).toHours().assert(1L)
        TimeUnit.Seconds(60L * 60L + 60L).toHours().assert(1L)
        TimeUnit.Seconds(60L * 60L * 500L).toHours().assert(500L)
    }

    @Test
    fun secondsToDays() {
        TimeUnit.Seconds(0L).toDays().assert(0L)
        TimeUnit.Seconds(60L * 60L + 60L).toDays().assert(0L)
        TimeUnit.Seconds(60L * 60L * 24L).toDays().assert(1L)
        TimeUnit.Seconds(60L * 60L * 30L).toDays().assert(1L)
        TimeUnit.Seconds(60L * 60L * 48L).toDays().assert(2L)
        TimeUnit.Seconds(60L * 60L * 24L * 7L).toDays().assert(7L)
    }

    @Test
    fun minutesToNanoSeconds() {
        TimeUnit.Minutes(0L).toNanoSeconds().assert(0L)
        TimeUnit.Minutes(1L).toNanoSeconds().assert(60_000_000_000L)
        TimeUnit.Minutes(2L).toNanoSeconds().assert(120_000_000_000L)
        TimeUnit.Minutes(60L).toNanoSeconds().assert(3_600_000_000_000L)
    }

    @Test
    fun minutesToSeconds() {
        TimeUnit.Minutes(0L).toSeconds().assert(0L)
        TimeUnit.Minutes(1L).toSeconds().assert(60L)
        TimeUnit.Minutes(2L).toSeconds().assert(120L)
        TimeUnit.Minutes(60L).toSeconds().assert(60L * 60L)
    }

    @Test
    fun minutesToHours() {
        TimeUnit.Minutes(0L).toHours().assert(0L)
        TimeUnit.Minutes(30L).toHours().assert(0L)
        TimeUnit.Minutes(60L).toHours().assert(1L)
        TimeUnit.Minutes(96L).toHours().assert(1L)
        TimeUnit.Minutes(120L).toHours().assert(2L)
        TimeUnit.Minutes(600L).toHours().assert(10L)
    }

    @Test
    fun minutesToDays() {
        TimeUnit.Minutes(0L).toDays().assert(0L)
        TimeUnit.Minutes(50).toDays().assert(0L)
        TimeUnit.Minutes(60L * 23L).toDays().assert(0L)
        TimeUnit.Minutes(60L * 24L).toDays().assert(1L)
        TimeUnit.Minutes(60L * 25L).toDays().assert(1L)
        TimeUnit.Minutes(60L * 24L * 2L).toDays().assert(2L)
        TimeUnit.Minutes(60L * 24L * 7L).toDays().assert(7L)
    }

    @Test
    fun hoursToNanoSeconds() {
        TimeUnit.Hours(0L).toNanoSeconds().assert(0)
        TimeUnit.Hours(1L).toNanoSeconds().assert(3_600_000_000_000)
        TimeUnit.Hours(24L).toNanoSeconds().assert(86_400_000_000_000)
    }

    @Test
    fun hoursToSeconds() {
        TimeUnit.Hours(0L).toSeconds().assert(0L)
        TimeUnit.Hours(1L).toSeconds().assert(3600L)
        TimeUnit.Hours(10L).toSeconds().assert(3600L * 10L)
        TimeUnit.Hours(11L).toSeconds().assert(3600L * 11L)
    }

    @Test
    fun hoursToMinutes() {
        TimeUnit.Hours(0L).toMinutes().assert(0L)
        TimeUnit.Hours(9L).toMinutes().assert(9L * 60L)
        TimeUnit.Hours(5L).toMinutes().assert(5L * 60L)
    }

    @Test
    fun hoursToDays() {
        TimeUnit.Hours(0L).toDays().assert(0L)
        TimeUnit.Hours(5L).toDays().assert(0L)
        TimeUnit.Hours(23L).toDays().assert(0L)
        TimeUnit.Hours(24L).toDays().assert(1L)
        TimeUnit.Hours(25L).toDays().assert(1L)
        TimeUnit.Hours(48L).toDays().assert(2L)
    }

    @Test
    fun daysToNanoSeconds() {
        TimeUnit.Days(0L).toNanoSeconds().assert(0L)
        TimeUnit.Days(1L).toNanoSeconds().assert(86_400_000_000_000L)
    }

    @Test
    fun daysToSeconds() {
        TimeUnit.Days(0L).toSeconds().assert(0L)
        TimeUnit.Days(1L).toSeconds().assert(3600L * 24L)
        TimeUnit.Days(6L).toSeconds().assert(6L * 3600L * 24L)
    }

    @Test
    fun daysToMinutes() {
        TimeUnit.Days(0L).toMinutes().assert(0L)
        TimeUnit.Days(1L).toMinutes().assert(60L * 24L)
        TimeUnit.Days(3L).toMinutes().assert(60L * 24L * 3L)
    }

    @Test
    fun daysToHours() {
        TimeUnit.Days(0L).toHours().assert(0L)
        TimeUnit.Days(1L).toHours().assert(24L)
        TimeUnit.Days(3L).toHours().assert(72L)
    }

    @Test
    fun timeUnitDelay() = runTest {
        TimeUnit.MilliSeconds(10).delay()
        currentTime.assertLargerOrEqualTo(10)
    }
}

inline fun TimeUnit.assert(otherValue: Long, message: String = "") = value.assert(otherValue, message)

class NanoSecondsTest {
    @Test
    fun toMilliSeconds() {
        TimeUnit.NanoSeconds(0L).toMilliSeconds().assert(0L)
        TimeUnit.NanoSeconds(1_000_000L).toMilliSeconds().assert(1L)
        TimeUnit.NanoSeconds(1_000_000_000L).toMilliSeconds().assert(1000L)
        TimeUnit.NanoSeconds(1_000_000_001L).toMilliSeconds().assert(1000L)
        TimeUnit.NanoSeconds(1_000_100_001L).toMilliSeconds().assert(1000L)
    }

    @Test
    fun plus() {
        (TimeUnit.NanoSeconds(0L) + TimeUnit.NanoSeconds(0L)).assert(0L)
        (TimeUnit.NanoSeconds(1L) + TimeUnit.NanoSeconds(0L)).assert(1L)
        (TimeUnit.NanoSeconds(0L) + TimeUnit.NanoSeconds(1L)).assert(1L)
        (TimeUnit.NanoSeconds(2L) + TimeUnit.NanoSeconds(5L)).assert(7L)
        (TimeUnit.NanoSeconds(5L) + TimeUnit.NanoSeconds(2L)).assert(7L)
    }

    @Test
    fun minus() {
        (TimeUnit.NanoSeconds(0) - TimeUnit.NanoSeconds(0L)).assert(0L)
        (TimeUnit.NanoSeconds(1) - TimeUnit.NanoSeconds(0L)).assert(1L)
        (TimeUnit.NanoSeconds(0) - TimeUnit.NanoSeconds(1L)).assert(-1L)
        (TimeUnit.NanoSeconds(2) - TimeUnit.NanoSeconds(5L)).assert(-3L)
        (TimeUnit.NanoSeconds(5) - TimeUnit.NanoSeconds(2L)).assert(3L)

    }

    @Test
    fun times() {
        (TimeUnit.NanoSeconds(0L) * TimeUnit.NanoSeconds(1L)).assert(0L)
        (TimeUnit.NanoSeconds(1L) * TimeUnit.NanoSeconds(10L)).assert(10L)
        (TimeUnit.NanoSeconds(10L) * TimeUnit.NanoSeconds(10L)).assert(100L)
        (TimeUnit.NanoSeconds(-10L) * TimeUnit.NanoSeconds(10L)).assert(-100L)
        (TimeUnit.NanoSeconds(-10L) * TimeUnit.NanoSeconds(-10L)).assert(100L)
    }

    @Test
    fun div() {
        TimeUnit.NanoSeconds(0L).div(TimeUnit.NanoSeconds(1L)).assert(0L)
        TimeUnit.NanoSeconds(1L).div(TimeUnit.NanoSeconds(10L)).assert(0L)
        TimeUnit.NanoSeconds(10L).div(TimeUnit.NanoSeconds(10L)).assert(1L)
        TimeUnit.NanoSeconds(-10L).div(TimeUnit.NanoSeconds(10L)).assert(-1L)
        TimeUnit.NanoSeconds(-10L).div(TimeUnit.NanoSeconds(-10L)).assert(1L)
    }
}

class MilliSecondsTest {

    @Test
    fun toMilliSeconds() {
        TimeUnit.MilliSeconds(100L).toMilliSeconds().assert(100L)
        TimeUnit.MilliSeconds(1L).toMilliSeconds().assert(1L)
        TimeUnit.MilliSeconds(0L).toMilliSeconds().assert(0L)
        TimeUnit.MilliSeconds(-1L).toMilliSeconds().assert(-1L)
        TimeUnit.MilliSeconds(-500L).toMilliSeconds().assert(-500L)
    }

    @Test
    fun plus() {
        TimeUnit.MilliSeconds(50L).plus(TimeUnit.MilliSeconds(60L)).assert(110L)
        TimeUnit.MilliSeconds(0L).plus(TimeUnit.MilliSeconds(60L)).assert(60L)
        TimeUnit.MilliSeconds(50L).plus(TimeUnit.MilliSeconds(-150L)).assert(-100L)
    }

    @Test
    fun minus() {
        TimeUnit.MilliSeconds(50L).minus(TimeUnit.MilliSeconds(60L)).assert(-10L)
        TimeUnit.MilliSeconds(0L).minus(TimeUnit.MilliSeconds(60L)).assert(-60L)
        TimeUnit.MilliSeconds(250L).minus(TimeUnit.MilliSeconds(150L)).assert(100L)
    }

    @Test
    fun times() {
        TimeUnit.MilliSeconds(50L).times(TimeUnit.MilliSeconds(2L)).assert(100L)
        TimeUnit.MilliSeconds(0L).times(TimeUnit.MilliSeconds(60L)).assert(0L)
        TimeUnit.MilliSeconds(-1L).times(TimeUnit.MilliSeconds(-150L)).assert(150L)
    }

    @Test
    fun div() {
        TimeUnit.MilliSeconds(10L).div(TimeUnit.MilliSeconds(2L)).assert(5L)
        TimeUnit.MilliSeconds(0L).div(TimeUnit.MilliSeconds(60L)).assert(0L)
        TimeUnit.MilliSeconds(150L).div(TimeUnit.MilliSeconds(-50L)).assert(-3L)
    }
}

class SecondsTest {
    @Test
    fun toMilliSeconds() {
        TimeUnit.Seconds(100L).toMilliSeconds().assert(100_000L)
        TimeUnit.Seconds(1L).toMilliSeconds().assert(1_000L)
        TimeUnit.Seconds(0L).toMilliSeconds().assert(0L)
        TimeUnit.Seconds(-1L).toMilliSeconds().assert(-1_000L)
        TimeUnit.Seconds(-500L).toMilliSeconds().assert(-500_000L)
    }

    @Test
    fun plus() {
        TimeUnit.Seconds(50L).plus(TimeUnit.Seconds(60L)).assert(110L)
        TimeUnit.Seconds(0L).plus(TimeUnit.Seconds(60L)).assert(60L)
        TimeUnit.Seconds(50L).plus(TimeUnit.Seconds(-150L)).assert(-100L)
    }

    @Test
    fun minus() {
        TimeUnit.Seconds(50L).minus(TimeUnit.Seconds(60L)).assert(-10L)
        TimeUnit.Seconds(0L).minus(TimeUnit.Seconds(60L)).assert(-60L)
        TimeUnit.Seconds(250L).minus(TimeUnit.Seconds(150L)).assert(100L)
    }

    @Test
    fun times() {
        TimeUnit.Seconds(50L).times(TimeUnit.Seconds(2L)).assert(100L)
        TimeUnit.Seconds(0L).times(TimeUnit.Seconds(60L)).assert(0L)
        TimeUnit.Seconds(-1L).times(TimeUnit.Seconds(-150L)).assert(150L)
    }

    @Test
    fun div() {
        TimeUnit.Seconds(10L).div(TimeUnit.Seconds(2L)).assert(5L)
        TimeUnit.Seconds(0L).div(TimeUnit.Seconds(60L)).assert(0L)
        TimeUnit.Seconds(150L).div(TimeUnit.Seconds(-50L)).assert(-3L)
    }
}

class MinutesTest {
    @Test
    fun toMilliSeconds() {
        TimeUnit.Minutes(10L).toMilliSeconds().assert(600_000L)
        TimeUnit.Minutes(1L).toMilliSeconds().assert(60_000L)
        TimeUnit.Minutes(0L).toMilliSeconds().assert(0L)
        TimeUnit.Minutes(-1L).toMilliSeconds().assert(-60_000L)
        TimeUnit.Minutes(2L).toMilliSeconds().assert(120_000L)
    }

    @Test
    fun plus() {
        TimeUnit.Minutes(50L).plus(TimeUnit.Minutes(60L)).assert(110L)
        TimeUnit.Minutes(0L).plus(TimeUnit.Minutes(60L)).assert(60L)
        TimeUnit.Minutes(50L).plus(TimeUnit.Minutes(-150L)).assert(-100L)
    }

    @Test
    fun minus() {
        TimeUnit.Minutes(50L).minus(TimeUnit.Minutes(60L)).assert(-10L)
        TimeUnit.Minutes(0L).minus(TimeUnit.Minutes(60L)).assert(-60L)
        TimeUnit.Minutes(250L).minus(TimeUnit.Minutes(150L)).assert(100L)
    }

    @Test
    fun times() {
        TimeUnit.Minutes(50L).times(TimeUnit.Minutes(2L)).assert(100L)
        TimeUnit.Minutes(0L).times(TimeUnit.Minutes(60L)).assert(0L)
        TimeUnit.Minutes(-1L).times(TimeUnit.Minutes(-150L)).assert(150L)
    }

    @Test
    fun div() {
        TimeUnit.Minutes(10L).div(TimeUnit.Minutes(2L)).assert(5L)
        TimeUnit.Minutes(0L).div(TimeUnit.Minutes(60L)).assert(0L)
        TimeUnit.Minutes(150L).div(TimeUnit.Minutes(-50L)).assert(-3L)
    }
}

class HoursTest {
    @Test
    fun toMilliSeconds() {
        TimeUnit.Hours(100L).toMilliSeconds().assert(360_000_000L)
        TimeUnit.Hours(1L).toMilliSeconds().assert(3_600_000L)
        TimeUnit.Hours(0L).toMilliSeconds().assert(0L)
        TimeUnit.Hours(-1L).toMilliSeconds().assert(-3_600_000L)
    }

    @Test
    fun plus() {
        TimeUnit.Hours(50L).plus(TimeUnit.Hours(60L)).assert(110L)
        TimeUnit.Hours(0L).plus(TimeUnit.Hours(60L)).assert(60L)
        TimeUnit.Hours(50L).plus(TimeUnit.Hours(-150L)).assert(-100L)
    }

    @Test
    fun minus() {
        TimeUnit.Hours(50L).minus(TimeUnit.Hours(60L)).assert(-10L)
        TimeUnit.Hours(0L).minus(TimeUnit.Hours(60L)).assert(-60L)
        TimeUnit.Hours(250L).minus(TimeUnit.Hours(150L)).assert(100L)
    }

    @Test
    fun times() {
        TimeUnit.Hours(50L).times(TimeUnit.Hours(2L)).assert(100L)
        TimeUnit.Hours(0L).times(TimeUnit.Hours(60L)).assert(0L)
        TimeUnit.Hours(-1L).times(TimeUnit.Hours(-150L)).assert(150L)
    }

    @Test
    fun div() {
        TimeUnit.Hours(10L).div(TimeUnit.Hours(2L)).assert(5L)
        TimeUnit.Hours(0L).div(TimeUnit.Hours(60L)).assert(0L)
        TimeUnit.Hours(150L).div(TimeUnit.Hours(-50L)).assert(-3L)
    }
}


class DaysTest {
    @Test
    fun toMilliSeconds() {
        TimeUnit.Days(1L).toMilliSeconds().assert(86_400_000L)
        TimeUnit.Days(0L).toMilliSeconds().assert(0L)
        TimeUnit.Days(-1L).toMilliSeconds().assert(-86_400_000L)
    }

    @Test
    fun plus() {
        TimeUnit.Days(50L).plus(TimeUnit.Days(60L)).assert(110L)
        TimeUnit.Days(0L).plus(TimeUnit.Days(60L)).assert(60L)
        TimeUnit.Days(50L).plus(TimeUnit.Days(-150L)).assert(-100L)
    }

    @Test
    fun minus() {
        TimeUnit.Days(50L).minus(TimeUnit.Days(60L)).assert(-10L)
        TimeUnit.Days(0L).minus(TimeUnit.Days(60L)).assert(-60L)
        TimeUnit.Days(250L).minus(TimeUnit.Days(150L)).assert(100L)
    }

    @Test
    fun times() {
        TimeUnit.Days(50L).times(TimeUnit.Days(2L)).assert(100L)
        TimeUnit.Days(0L).times(TimeUnit.Days(60L)).assert(0L)
        TimeUnit.Days(-1L).times(TimeUnit.Days(-150L)).assert(150L)
    }

    @Test
    fun div() {
        TimeUnit.Days(10L).div(TimeUnit.Days(2L)).assert(5L)
        TimeUnit.Days(0L).div(TimeUnit.Days(60L)).assert(0L)
        TimeUnit.Days(150L).div(TimeUnit.Days(-50L)).assert(-3L)
    }
}

class TimeUnitTest {
    @Test
    fun toStringTest() {
        TimeUnit.NanoSeconds(10L).toString().assert("10 ns")
        TimeUnit.MilliSeconds(10L).toString().assert("10 ms")
        TimeUnit.Seconds(10L).toString().assert("10 s")
        TimeUnit.Minutes(10L).toString().assert("10 m")
        TimeUnit.Hours(10L).toString().assert("10 h")
        TimeUnit.Days(10L).toString().assert("10 d")

    }
}