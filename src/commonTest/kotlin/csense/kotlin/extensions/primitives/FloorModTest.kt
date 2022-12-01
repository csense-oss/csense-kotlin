package csense.kotlin.extensions.primitives

import csense.kotlin.extensions.primitives.operations.*
import csense.kotlin.tests.assertions.*
import kotlin.test.*

class FloorModTest {

    @Test
    fun intFloorModTo() {

        0.floorMod(1).assert(0)
        1.floorMod(1).assert(0)

        0.floorMod(2).assert(0)
        1.floorMod(2).assert(1)
        2.floorMod(2).assert(0)

        4.floorMod(-3).assert(-2)
        (-4).floorMod(3).assert(2)
        (-4).floorMod(-3).assert(-1)

        (-1).floorMod(20).assert(19)

        //Int.MAX_VALUE = 2147483647
        //Int.MIN_VALUE = -2147483648
        Int.MAX_VALUE.floorMod(1).assert(0)
        Int.MIN_VALUE.floorMod(1).assert(0)
        Int.MAX_VALUE.floorMod(2).assert(1)
        Int.MIN_VALUE.floorMod(2).assert(0)

    }

    @Test
    fun longFloorModTo() {

        0L.floorMod(1L).assert(0L)
        1L.floorMod(1L).assert(0L)

        0L.floorMod(2L).assert(0L)
        1L.floorMod(2L).assert(1L)
        2L.floorMod(2L).assert(0L)

        4L.floorMod(-3L).assert(-2L)
        (-4L).floorMod(3L).assert(2L)
        (-4L).floorMod(-3L).assert(-1L)

        (-1L).floorMod(20L).assert(19L)

        //long.MIN_VALUE = -9,223,372,036,854,775,808
        //long.MAX_VALUE = 9,223,372,036,854,775,807
        Long.MAX_VALUE.floorMod(1).assert(0)
        Long.MIN_VALUE.floorMod(1).assert(0)
        Long.MAX_VALUE.floorMod(2).assert(1)
        Long.MIN_VALUE.floorMod(2).assert(0)

    }

    @Test
    fun floatFloorModTo() {

        0f.floorMod(1f).assert(0f)
        1f.floorMod(1f).assert(0f)

        0f.floorMod(2f).assert(0f)
        1f.floorMod(2f).assert(1f)
        2f.floorMod(2f).assert(0f)

        4f.floorMod(-3f).assert(-2f)
        (-4f).floorMod(3f).assert(2f)
        (-4f).floorMod(-3f).assert(-1f)

        (-1f).floorMod(20f).assert(19f)

        //Float.MIN_VALUE = 1.4E-45
        //Float.MAX_VALUE = 3.4028235E38
        Float.MAX_VALUE.floorMod(1f).assert(0f)
        Float.MIN_VALUE.floorMod(1f).assert(0f)

        Float.NaN.floorMod(1F).assert(Float.NaN)
        Float.NaN.floorMod(Float.NaN).assert(Float.NaN)


        Float.POSITIVE_INFINITY.floorMod(1f).assert(Float.NaN)
        Float.NEGATIVE_INFINITY.floorMod(1f).assert(Float.NaN)

    }

    @Test
    fun doubleFloorModTo() {
        0.0.floorMod(1.0).assert(0.0)
        1.0.floorMod(1.0).assert(0.0)

        0.0.floorMod(2.0).assert(0.0)
        1.0.floorMod(2.0).assert(1.0)
        2.0.floorMod(2.0).assert(0.0)

        4.0.floorMod(-3.0).assert(-2.0)
        (-4.0).floorMod(3.0).assert(2.0)
        (-4.0).floorMod(-3.0).assert(-1.0)

        (-1.0).floorMod(20.0).assert(19.0)

        //Double.MIN_VALUE = 4.9E-324
        //Double.MAX_VALUE = 1.7976931348623157E308
        Double.MAX_VALUE.floorMod(1.0).assert(0.0)
        Double.MIN_VALUE.floorMod(1.0).assert(0.0)

        Double.NaN.floorMod(1.0).assert(Double.NaN)
        Double.NaN.floorMod(Double.NaN).assert(Double.NaN)


        Double.POSITIVE_INFINITY.floorMod(1.0).assert(Double.NaN)
        Double.NEGATIVE_INFINITY.floorMod(1.0).assert(Double.NaN)
    }
}