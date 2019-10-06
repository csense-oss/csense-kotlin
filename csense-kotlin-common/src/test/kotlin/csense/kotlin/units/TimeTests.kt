package csense.kotlin.units

import csense.kotlin.test.assertions.*
import kotlin.test.*


class TimeTests {

//    @Test
//    fun toSeconds() {
//
//    }
//
//    @Test
//    fun toMinutes() {
//    }
//
//    @Test
//    fun toHours() {
//    }
//
//    @Test
//    fun toDays() {
//    }
//
//    @Test
//    fun toNanoSeconds() {
//    }
//
//    @Test
//    fun testToSeconds() {
//    }
//
//    @Test
//    fun testToMinutes() {
//    }
//
//    @Test
//    fun testToHours() {
//    }
//
//    @Test
//    fun testToDays() {
//    }
//
//    @Test
//    fun testToNanoSeconds() {
//    }
//
//    @Test
//    fun testToMinutes1() {
//    }
//
//    @Test
//    fun testToHours1() {
//    }
//
//    @Test
//    fun testToDays1() {
//    }
//
//    @Test
//    fun testToNanoSeconds1() {
//    }
//
//    @Test
//    fun testToSeconds1() {
//    }
//
//    @Test
//    fun testToHours2() {
//    }
//
//    @Test
//    fun testToDays2() {
//    }
//
//    @Test
//    fun testToNanoSeconds2() {
//    }
//
//    @Test
//    fun testToSeconds2() {
//    }
//
//    @Test
//    fun testToMinutes2() {
//    }
//
//    @Test
//    fun testToDays3() {
//    }
//
//    @Test
//    fun testToNanoSeconds3() {
//    }
//
//    @Test
//    fun testToSeconds3() {
//    }
//
//    @Test
//    fun testToMinutes3() {
//    }
//
//    @Test
//    fun testToHours3() {
//    }
//
//    @Test
//    fun delay() {
//
//    }


    @Test
    fun toMilliSeconds() {
        TimeUnit.NanoSeconds(0).toMilliSeconds().value.assert(0)
        TimeUnit.MilliSeconds(0).toMilliSeconds().value.assert(0)
        TimeUnit.Seconds(0).toMilliSeconds().value.assert(0)
        TimeUnit.Minutes(0).toMilliSeconds().value.assert(0)
        TimeUnit.Hours(0).toMilliSeconds().value.assert(0)
        TimeUnit.Days(0).toMilliSeconds().value.assert(0)


        TimeUnit.NanoSeconds(1).toMilliSeconds().value
                .assert(0,
                        "1 nanosecond is too small" +
                                " to become a meaningful milliseconds (whole number)")
        TimeUnit.MilliSeconds(1).toMilliSeconds().value.assert(1)
        TimeUnit.Seconds(1).toMilliSeconds().value.assert(1000)
        TimeUnit.Minutes(1).toMilliSeconds().value.assert(1000 * 60)
        TimeUnit.Hours(1).toMilliSeconds().value.assert(1000 * 60 * 60)
        TimeUnit.Days(1).toMilliSeconds().value.assert(1000 * 60 * 60 * 24)
        TimeUnit.NanoSeconds(1_000_000).toMilliSeconds().value.assert(1)

    }

    @Test
    fun getPrefix() {
        TimeUnit.NanoSeconds(0).prefix.assert("ns")
        TimeUnit.MilliSeconds(0).prefix.assert("ms")
        TimeUnit.Seconds(0).prefix.assert("s")
        TimeUnit.Minutes(0).prefix.assert("m")
        TimeUnit.Hours(0).prefix.assert("h")
        TimeUnit.Days(0).prefix.assert("d")
    }


    @Test
    fun testToString() {
        TimeUnit.NanoSeconds(0).toString().assert("0 ns")
        TimeUnit.MilliSeconds(0).toString().assert("0 ms")
        TimeUnit.Seconds(0).toString().assert("0 s")
        TimeUnit.Minutes(0).toString().assert("0 m")
        TimeUnit.Hours(0).toString().assert("0 h")
        TimeUnit.Days(0).toString().assert("0 d")
    }

    @Test
    fun getValue() {
        TimeUnit.NanoSeconds(0).value.assert(0)
        TimeUnit.MilliSeconds(0).value.assert(0)
        TimeUnit.Seconds(0).value.assert(0)
        TimeUnit.Minutes(0).value.assert(0)
        TimeUnit.Hours(0).value.assert(0)
        TimeUnit.Days(0).value.assert(0)
    }


}