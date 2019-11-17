@file:Suppress("NOTHING_TO_INLINE")

package csense.kotlin.units

import csense.kotlin.tests.assertions.assert
import kotlin.test.Test


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
}