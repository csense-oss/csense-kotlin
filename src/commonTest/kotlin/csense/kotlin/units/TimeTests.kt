@file:Suppress("NOTHING_TO_INLINE")

package csense.kotlin.units

import csense.kotlin.coroutines.*
import csense.kotlin.tests.assertions.*
import kotlin.test.Test
import kotlin.time.*


class TimeTests {

    @Test
    fun nanoSecondsToSeconds() {
        TimeUnit.NanoSeconds(0).toSeconds().assert(0)
        TimeUnit.NanoSeconds(1_000_000_000).toSeconds().assert(1)
        TimeUnit.NanoSeconds(10_000_000_000).toSeconds().assert(10)
        TimeUnit.NanoSeconds(100_000_000_000).toSeconds().assert(100)
    }

    @Test
    fun nanoSecondsToMinutes() {
        TimeUnit.NanoSeconds(0).toMinutes().assert(0)
        TimeUnit.NanoSeconds(10_000_000_000).toMinutes().assert(0)
        TimeUnit.NanoSeconds(100_000_000_000).toMinutes().assert(1)
        TimeUnit.NanoSeconds(120_000_000_000).toMinutes().assert(2)
    }


    @Test
    fun nanoSecondsToHours() {
        TimeUnit.NanoSeconds(0).toHours().assert(0)
        TimeUnit.NanoSeconds(120_000_000_000).toHours().assert(0)
        TimeUnit.NanoSeconds(60_000_000_000 * 60).toHours().assert(1)
        TimeUnit.NanoSeconds(60_000_000_000 * 60 * 8).toHours().assert(8)
        TimeUnit.NanoSeconds(60_000_000_000 * 60 * 24).toHours().assert(24)
    }

    @Test
    fun nanoSecondsToDays() {
        TimeUnit.NanoSeconds(120_000_000_000).toDays().assert(0)
        TimeUnit.NanoSeconds(60_000_000_000).toDays().assert(0)
        TimeUnit.NanoSeconds(60_000_000_000 * 60 * 24).toDays().assert(1)
        TimeUnit.NanoSeconds(60_000_000_000 * 60 * 24 * 7).toDays().assert(7)
    }


    @Test
    fun milliSecondsToNanoSeconds() {
        TimeUnit.MilliSeconds(1).toNanoSeconds().assert(1_000_000)
        TimeUnit.MilliSeconds(0).toNanoSeconds().assert(0)
        TimeUnit.MilliSeconds(1000).toNanoSeconds().assert(1_000_000_000)
    }

    @Test
    fun milliSecondsToSeconds() {
        TimeUnit.MilliSeconds(0).toSeconds().assert(0)
        TimeUnit.MilliSeconds(500).toSeconds().assert(0)
        TimeUnit.MilliSeconds(1000).toSeconds().assert(1)
        TimeUnit.MilliSeconds(60_000).toSeconds().assert(60)
    }

    @Test
    fun milliSecondsToMinutes() {
        TimeUnit.MilliSeconds(0).toMinutes().assert(0)
        TimeUnit.MilliSeconds(5_000).toMinutes().assert(0)
        TimeUnit.MilliSeconds(60_000).toMinutes().assert(1)
        TimeUnit.MilliSeconds(60_000 * 5).toMinutes().assert(5)
    }

    @Test
    fun milliSecondsToHours() {
        TimeUnit.MilliSeconds(0).toHours().assert(0)
        TimeUnit.MilliSeconds(60_000).toHours().assert(0)
        TimeUnit.MilliSeconds(60_000 * 60).toHours().assert(1)
        TimeUnit.MilliSeconds(60_000 * 60 * 10).toHours().assert(10)
    }

    @Test
    fun milliSecondsToDays() {
        TimeUnit.MilliSeconds(0).toDays().assert(0)
        TimeUnit.MilliSeconds(60_000 * 60 * 24).toDays().assert(1)
        TimeUnit.MilliSeconds(60_000 * 60 * 24 * 7).toDays().assert(7)
    }

    @Test
    fun secondsToNanoSeconds() {
        TimeUnit.Seconds(0).toNanoSeconds().assert(0)
        TimeUnit.Seconds(1).toNanoSeconds().assert(1_000_000_000)
        TimeUnit.Seconds(60).toNanoSeconds().assert(60_000_000_000)
    }

    @Test
    fun secondsToMinutes() {
        TimeUnit.Seconds(0).toMinutes().assert(0)
        TimeUnit.Seconds(30).toMinutes().assert(0)
        TimeUnit.Seconds(60).toMinutes().assert(1)
        TimeUnit.Seconds(121).toMinutes().assert(2)
    }

    @Test
    fun secondsToHours() {
        TimeUnit.Seconds(60).toHours().assert(0)
        TimeUnit.Seconds(60 * 60).toHours().assert(1)
        TimeUnit.Seconds(60 * 60 + 60).toHours().assert(1)
        TimeUnit.Seconds(60 * 60 * 500).toHours().assert(500)
    }

    @Test
    fun secondsToDays() {
        TimeUnit.Seconds(0).toDays().assert(0)
        TimeUnit.Seconds(60 * 60 + 60).toDays().assert(0)
        TimeUnit.Seconds(60 * 60 * 24).toDays().assert(1)
        TimeUnit.Seconds(60 * 60 * 30).toDays().assert(1)
        TimeUnit.Seconds(60 * 60 * 48).toDays().assert(2)
        TimeUnit.Seconds(60 * 60 * 24 * 7).toDays().assert(7)
    }

    @Test
    fun minutesToNanoSeconds() {
        TimeUnit.Minutes(0).toNanoSeconds().assert(0)
        TimeUnit.Minutes(1).toNanoSeconds().assert(60_000_000_000)
        TimeUnit.Minutes(2).toNanoSeconds().assert(120_000_000_000)
        TimeUnit.Minutes(60).toNanoSeconds().assert(3_600_000_000_000)
    }

    @Test
    fun minutesToSeconds() {
        TimeUnit.Minutes(0).toSeconds().assert(0)
        TimeUnit.Minutes(1).toSeconds().assert(60)
        TimeUnit.Minutes(2).toSeconds().assert(120)
        TimeUnit.Minutes(60).toSeconds().assert(60 * 60)
    }

    @Test
    fun minutesToHours() {
        TimeUnit.Minutes(0).toHours().assert(0)
        TimeUnit.Minutes(30).toHours().assert(0)
        TimeUnit.Minutes(60).toHours().assert(1)
        TimeUnit.Minutes(96).toHours().assert(1)
        TimeUnit.Minutes(120).toHours().assert(2)
        TimeUnit.Minutes(600).toHours().assert(10)
    }

    @Test
    fun minutesToDays() {
        TimeUnit.Minutes(0).toDays().assert(0)
        TimeUnit.Minutes(50).toDays().assert(0)
        TimeUnit.Minutes(60 * 23).toDays().assert(0)
        TimeUnit.Minutes(60 * 24).toDays().assert(1)
        TimeUnit.Minutes(60 * 25).toDays().assert(1)
        TimeUnit.Minutes(60 * 24 * 2).toDays().assert(2)
        TimeUnit.Minutes(60 * 24 * 7).toDays().assert(7)
    }

    @Test
    fun hoursToNanoSeconds() {
        TimeUnit.Hours(0).toNanoSeconds().assert(0)
        TimeUnit.Hours(1).toNanoSeconds().assert(3_600_000_000_000)
        TimeUnit.Hours(24).toNanoSeconds().assert(86_400_000_000_000)
    }

    @Test
    fun hoursToSeconds() {
        TimeUnit.Hours(0).toSeconds().assert(0)
        TimeUnit.Hours(1).toSeconds().assert(3600)
        TimeUnit.Hours(10).toSeconds().assert(3600 * 10)
        TimeUnit.Hours(11).toSeconds().assert(3600 * 11)
    }

    @Test
    fun hoursToMinutes() {
        TimeUnit.Hours(0).toMinutes().assert(0)
        TimeUnit.Hours(9).toMinutes().assert(9 * 60)
        TimeUnit.Hours(5).toMinutes().assert(5 * 60)
    }

    @Test
    fun hoursToDays() {
        TimeUnit.Hours(0).toDays().assert(0)
        TimeUnit.Hours(5).toDays().assert(0)
        TimeUnit.Hours(23).toDays().assert(0)
        TimeUnit.Hours(24).toDays().assert(1)
        TimeUnit.Hours(25).toDays().assert(1)
        TimeUnit.Hours(48).toDays().assert(2)
    }

    @Test
    fun daysToNanoSeconds() {
        TimeUnit.Days(0).toNanoSeconds().assert(0)
        TimeUnit.Days(1).toNanoSeconds().assert(86_400_000_000_000)
    }

    @Test
    fun daysToSeconds() {
        TimeUnit.Days(0).toSeconds().assert(0)
        TimeUnit.Days(1).toSeconds().assert(3600 * 24)
        TimeUnit.Days(6).toSeconds().assert(6 * 3600 * 24)
    }

    @Test
    fun daysToMinutes() {
        TimeUnit.Days(0).toMinutes().assert(0)
        TimeUnit.Days(1).toMinutes().assert(60 * 24)
        TimeUnit.Days(3).toMinutes().assert(60 * 24 * 3)
    }

    @Test
    fun daysToHours() {
        TimeUnit.Days(0).toHours().assert(0)
        TimeUnit.Days(1).toHours().assert(24)
        TimeUnit.Days(3).toHours().assert(72)
    }

    @OptIn(ExperimentalTime::class)
    @Test
    fun timeUnitDelay() = runBlockingTest {
        val time: Duration = measureTime {
            TimeUnit.MilliSeconds(10).delay()
        }
        println("time = $time")
        time.inMilliseconds.assertLargerOrEqualTo(10.0)
    }
}

inline fun TimeUnit.assert(otherValue: Long, message: String = "") = value.assert(otherValue, message)

class NanoSecondsTest {
    @Test
    fun toMilliSeconds() {
        TimeUnit.NanoSeconds(0).toMilliSeconds().assert(0)
        TimeUnit.NanoSeconds(1_000_000).toMilliSeconds().assert(1)
        TimeUnit.NanoSeconds(1_000_000_000).toMilliSeconds().assert(1000)
        TimeUnit.NanoSeconds(1_000_000_001).toMilliSeconds().assert(1000)
        TimeUnit.NanoSeconds(1_000_100_001).toMilliSeconds().assert(1000)
    }

    @Test
    fun plus() {
        (TimeUnit.NanoSeconds(0) + TimeUnit.NanoSeconds(0)).assert(0)
        (TimeUnit.NanoSeconds(1) + TimeUnit.NanoSeconds(0)).assert(1)
        (TimeUnit.NanoSeconds(0) + TimeUnit.NanoSeconds(1)).assert(1)
        (TimeUnit.NanoSeconds(2) + TimeUnit.NanoSeconds(5)).assert(7)
        (TimeUnit.NanoSeconds(5) + TimeUnit.NanoSeconds(2)).assert(7)
    }

    @Test
    fun minus() {
        (TimeUnit.NanoSeconds(0) - TimeUnit.NanoSeconds(0)).assert(0)
        (TimeUnit.NanoSeconds(1) - TimeUnit.NanoSeconds(0)).assert(1)
        (TimeUnit.NanoSeconds(0) - TimeUnit.NanoSeconds(1)).assert(-1)
        (TimeUnit.NanoSeconds(2) - TimeUnit.NanoSeconds(5)).assert(-3)
        (TimeUnit.NanoSeconds(5) - TimeUnit.NanoSeconds(2)).assert(3)

    }

    @Test
    fun times() {
        (TimeUnit.NanoSeconds(0) * TimeUnit.NanoSeconds(1)).assert(0)
        (TimeUnit.NanoSeconds(1) * TimeUnit.NanoSeconds(10)).assert(10)
        (TimeUnit.NanoSeconds(10) * TimeUnit.NanoSeconds(10)).assert(100)
        (TimeUnit.NanoSeconds(-10) * TimeUnit.NanoSeconds(10)).assert(-100)
        (TimeUnit.NanoSeconds(-10) * TimeUnit.NanoSeconds(-10)).assert(100)
    }

    @Test
    fun div() {
        TimeUnit.NanoSeconds(0).div(TimeUnit.NanoSeconds(1)).assert(0)
        TimeUnit.NanoSeconds(1).div(TimeUnit.NanoSeconds(10)).assert(0)
        TimeUnit.NanoSeconds(10).div(TimeUnit.NanoSeconds(10)).assert(1)
        TimeUnit.NanoSeconds(-10).div(TimeUnit.NanoSeconds(10)).assert(-1)
        TimeUnit.NanoSeconds(-10).div(TimeUnit.NanoSeconds(-10)).assert(1)
    }
}

class MilliSecondsTest {

    @Test
    fun toMilliSeconds() {
        TimeUnit.MilliSeconds(100).toMilliSeconds().assert(100)
        TimeUnit.MilliSeconds(1).toMilliSeconds().assert(1)
        TimeUnit.MilliSeconds(0).toMilliSeconds().assert(0)
        TimeUnit.MilliSeconds(-1).toMilliSeconds().assert(-1)
        TimeUnit.MilliSeconds(-500).toMilliSeconds().assert(-500)
    }

    @Test
    fun plus() {
        TimeUnit.MilliSeconds(50).plus(TimeUnit.MilliSeconds(60)).assert(110)
        TimeUnit.MilliSeconds(0).plus(TimeUnit.MilliSeconds(60)).assert(60)
        TimeUnit.MilliSeconds(50).plus(TimeUnit.MilliSeconds(-150)).assert(-100)
    }

    @Test
    fun minus() {
        TimeUnit.MilliSeconds(50).minus(TimeUnit.MilliSeconds(60)).assert(-10)
        TimeUnit.MilliSeconds(0).minus(TimeUnit.MilliSeconds(60)).assert(-60)
        TimeUnit.MilliSeconds(250).minus(TimeUnit.MilliSeconds(150)).assert(100)
    }

    @Test
    fun times() {
        TimeUnit.MilliSeconds(50).times(TimeUnit.MilliSeconds(2)).assert(100)
        TimeUnit.MilliSeconds(0).times(TimeUnit.MilliSeconds(60)).assert(0)
        TimeUnit.MilliSeconds(-1).times(TimeUnit.MilliSeconds(-150)).assert(150)
    }

    @Test
    fun div() {
        TimeUnit.MilliSeconds(10).div(TimeUnit.MilliSeconds(2)).assert(5)
        TimeUnit.MilliSeconds(0).div(TimeUnit.MilliSeconds(60)).assert(0)
        TimeUnit.MilliSeconds(150).div(TimeUnit.MilliSeconds(-50)).assert(-3)
    }
}

class SecondsTest {
    @Test
    fun toMilliSeconds() {
        TimeUnit.Seconds(100).toMilliSeconds().assert(100_000)
        TimeUnit.Seconds(1).toMilliSeconds().assert(1_000)
        TimeUnit.Seconds(0).toMilliSeconds().assert(0)
        TimeUnit.Seconds(-1).toMilliSeconds().assert(-1_000)
        TimeUnit.Seconds(-500).toMilliSeconds().assert(-500_000)
    }

    @Test
    fun plus() {
        TimeUnit.Seconds(50).plus(TimeUnit.Seconds(60)).assert(110)
        TimeUnit.Seconds(0).plus(TimeUnit.Seconds(60)).assert(60)
        TimeUnit.Seconds(50).plus(TimeUnit.Seconds(-150)).assert(-100)
    }

    @Test
    fun minus() {
        TimeUnit.Seconds(50).minus(TimeUnit.Seconds(60)).assert(-10)
        TimeUnit.Seconds(0).minus(TimeUnit.Seconds(60)).assert(-60)
        TimeUnit.Seconds(250).minus(TimeUnit.Seconds(150)).assert(100)
    }

    @Test
    fun times() {
        TimeUnit.Seconds(50).times(TimeUnit.Seconds(2)).assert(100)
        TimeUnit.Seconds(0).times(TimeUnit.Seconds(60)).assert(0)
        TimeUnit.Seconds(-1).times(TimeUnit.Seconds(-150)).assert(150)
    }

    @Test
    fun div() {
        TimeUnit.Seconds(10).div(TimeUnit.Seconds(2)).assert(5)
        TimeUnit.Seconds(0).div(TimeUnit.Seconds(60)).assert(0)
        TimeUnit.Seconds(150).div(TimeUnit.Seconds(-50)).assert(-3)
    }
}

class MinutesTest {
    @Test
    fun toMilliSeconds() {
        TimeUnit.Minutes(10).toMilliSeconds().assert(600_000)
        TimeUnit.Minutes(1).toMilliSeconds().assert(60_000)
        TimeUnit.Minutes(0).toMilliSeconds().assert(0)
        TimeUnit.Minutes(-1).toMilliSeconds().assert(-60_000)
        TimeUnit.Minutes(2).toMilliSeconds().assert(120_000)
    }

    @Test
    fun plus() {
        TimeUnit.Minutes(50).plus(TimeUnit.Minutes(60)).assert(110)
        TimeUnit.Minutes(0).plus(TimeUnit.Minutes(60)).assert(60)
        TimeUnit.Minutes(50).plus(TimeUnit.Minutes(-150)).assert(-100)
    }

    @Test
    fun minus() {
        TimeUnit.Minutes(50).minus(TimeUnit.Minutes(60)).assert(-10)
        TimeUnit.Minutes(0).minus(TimeUnit.Minutes(60)).assert(-60)
        TimeUnit.Minutes(250).minus(TimeUnit.Minutes(150)).assert(100)
    }

    @Test
    fun times() {
        TimeUnit.Minutes(50).times(TimeUnit.Minutes(2)).assert(100)
        TimeUnit.Minutes(0).times(TimeUnit.Minutes(60)).assert(0)
        TimeUnit.Minutes(-1).times(TimeUnit.Minutes(-150)).assert(150)
    }

    @Test
    fun div() {
        TimeUnit.Minutes(10).div(TimeUnit.Minutes(2)).assert(5)
        TimeUnit.Minutes(0).div(TimeUnit.Minutes(60)).assert(0)
        TimeUnit.Minutes(150).div(TimeUnit.Minutes(-50)).assert(-3)
    }
}

class HoursTest {
    @Test
    fun toMilliSeconds() {
        TimeUnit.Hours(100).toMilliSeconds().assert(360_000_000)
        TimeUnit.Hours(1).toMilliSeconds().assert(3_600_000)
        TimeUnit.Hours(0).toMilliSeconds().assert(0)
        TimeUnit.Hours(-1).toMilliSeconds().assert(-3_600_000)
    }

    @Test
    fun plus() {
        TimeUnit.Hours(50).plus(TimeUnit.Hours(60)).assert(110)
        TimeUnit.Hours(0).plus(TimeUnit.Hours(60)).assert(60)
        TimeUnit.Hours(50).plus(TimeUnit.Hours(-150)).assert(-100)
    }

    @Test
    fun minus() {
        TimeUnit.Hours(50).minus(TimeUnit.Hours(60)).assert(-10)
        TimeUnit.Hours(0).minus(TimeUnit.Hours(60)).assert(-60)
        TimeUnit.Hours(250).minus(TimeUnit.Hours(150)).assert(100)
    }

    @Test
    fun times() {
        TimeUnit.Hours(50).times(TimeUnit.Hours(2)).assert(100)
        TimeUnit.Hours(0).times(TimeUnit.Hours(60)).assert(0)
        TimeUnit.Hours(-1).times(TimeUnit.Hours(-150)).assert(150)
    }

    @Test
    fun div() {
        TimeUnit.Hours(10).div(TimeUnit.Hours(2)).assert(5)
        TimeUnit.Hours(0).div(TimeUnit.Hours(60)).assert(0)
        TimeUnit.Hours(150).div(TimeUnit.Hours(-50)).assert(-3)
    }
}


class DaysTest {
    @Test
    fun toMilliSeconds() {
        TimeUnit.Days(1).toMilliSeconds().assert(86_400_000)
        TimeUnit.Days(0).toMilliSeconds().assert(0)
        TimeUnit.Days(-1).toMilliSeconds().assert(-86_400_000)
    }

    @Test
    fun plus() {
        TimeUnit.Days(50).plus(TimeUnit.Days(60)).assert(110)
        TimeUnit.Days(0).plus(TimeUnit.Days(60)).assert(60)
        TimeUnit.Days(50).plus(TimeUnit.Days(-150)).assert(-100)
    }

    @Test
    fun minus() {
        TimeUnit.Days(50).minus(TimeUnit.Days(60)).assert(-10)
        TimeUnit.Days(0).minus(TimeUnit.Days(60)).assert(-60)
        TimeUnit.Days(250).minus(TimeUnit.Days(150)).assert(100)
    }

    @Test
    fun times() {
        TimeUnit.Days(50).times(TimeUnit.Days(2)).assert(100)
        TimeUnit.Days(0).times(TimeUnit.Days(60)).assert(0)
        TimeUnit.Days(-1).times(TimeUnit.Days(-150)).assert(150)
    }

    @Test
    fun div() {
        TimeUnit.Days(10).div(TimeUnit.Days(2)).assert(5)
        TimeUnit.Days(0).div(TimeUnit.Days(60)).assert(0)
        TimeUnit.Days(150).div(TimeUnit.Days(-50)).assert(-3)
    }
}

class TimeUnitTest {
    @Test
    fun testToString() {
        TimeUnit.NanoSeconds(10).toString().assert("10 ns")
        TimeUnit.MilliSeconds(10).toString().assert("10 ms")
        TimeUnit.Seconds(10).toString().assert("10 s")
        TimeUnit.Minutes(10).toString().assert("10 m")
        TimeUnit.Hours(10).toString().assert("10 h")
        TimeUnit.Days(10).toString().assert("10 d")

    }
}