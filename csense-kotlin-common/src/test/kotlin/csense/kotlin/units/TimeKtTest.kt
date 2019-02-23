package csense.kotlin.units

import csense.kotlin.test.assertions.*
import csense.kotlin.units.TimeUnit.*
import kotlin.test.*


/**
 *
 */
internal class TimeKtTest {

    @Ignore
    @Test
    fun toSeconds() {
    }

    @Ignore
    @Test
    fun toMinutes() {
    }

    @Ignore
    @Test
    fun toHours() {
    }

    @Ignore
    @Test
    fun toDays() {
    }

    @Ignore
    @Test
    fun toNanoSeconds() {
    }

    @Ignore
    @Test
    fun toSeconds1() {
    }

    @Ignore
    @Test
    fun toMinutes1() {
    }

    @Ignore
    @Test
    fun toHours1() {
    }

    @Ignore
    @Test
    fun toDays1() {
    }

    @Ignore
    @Test
    fun toNanoSeconds1() {
    }

    @Ignore
    @Test
    fun toMinutes2() {
    }

    @Ignore
    @Test
    fun toHours2() {
    }

    @Ignore
    @Test
    fun toDays2() {
    }

    @Ignore
    @Test
    fun toNanoSeconds2() {
    }

    @Ignore
    @Test
    fun toSeconds2() {
    }

    @Ignore
    @Test
    fun toHours3() {
    }

    @Ignore
    @Test
    fun toDays3() {
    }

    @Ignore
    @Test
    fun toNanoSeconds3() {
    }

    @Ignore
    @Test
    fun toSeconds3() {
    }

    @Ignore
    @Test
    fun toMinutes3() {
    }

    @Ignore
    @Test
    fun toDays4() {
    }

    @Ignore
    @Test
    fun toNanoSeconds4() {
    }

    @Ignore
    @Test
    fun toSeconds4() {
    }

    @Ignore
    @Test
    fun toMinutes4() {
    }

    @Ignore
    @Test
    fun toHours4() {
    }

    @Ignore
    @Test
    fun delay() {
    }

//TODO merge below into the top part

    @Test
    fun getPrefix() {
        NanoSeconds(0).prefix.assert("ns")
        MilliSeconds(0).prefix.assert("ms")
        Seconds(0).prefix.assert("s")
        Minutes(0).prefix.assert("m")
        Hours(0).prefix.assert("h")
        Days(0).prefix.assert("d")
    }

    @Test
    fun toMilliSeconds() {
        NanoSeconds(0).toMilliSeconds().value.assert(0)
        MilliSeconds(0).toMilliSeconds().value.assert(0)
        Seconds(0).toMilliSeconds().value.assert(0)
        Minutes(0).toMilliSeconds().value.assert(0)
        Hours(0).toMilliSeconds().value.assert(0)
        Days(0).toMilliSeconds().value.assert(0)


        NanoSeconds(1).toMilliSeconds().value
                .assert(0,
                        "1 nanosecond is too small" +
                                " to become a meaningful milliseconds (whole number)")
        MilliSeconds(1).toMilliSeconds().value.assert(1)
        Seconds(1).toMilliSeconds().value.assert(1000)
        Minutes(1).toMilliSeconds().value.assert(1000 * 60)
        Hours(1).toMilliSeconds().value.assert(1000 * 60 * 60)
        Days(1).toMilliSeconds().value.assert(1000 * 60 * 60 * 24)
        NanoSeconds(1_000_000).toMilliSeconds().value.assert(1)

    }

    @Test
    fun testToString() {
        NanoSeconds(0).toString().assert("0 ns")
        MilliSeconds(0).toString().assert("0 ms")
        Seconds(0).toString().assert("0 s")
        Minutes(0).toString().assert("0 m")
        Hours(0).toString().assert("0 h")
        Days(0).toString().assert("0 d")
    }

    @Test
    fun getValue() {
        NanoSeconds(0).value.assert(0)
        MilliSeconds(0).value.assert(0)
        Seconds(0).value.assert(0)
        Minutes(0).value.assert(0)
        Hours(0).value.assert(0)
        Days(0).value.assert(0)
    }

}