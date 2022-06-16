package csense.kotlin.extensions.primitives

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

    }

    @Test
    fun floatFloorModTo() {
        TODO()
        //consider pos & neg inf + nan
    }

    @Test
    fun doubleFloorModTo() {
        TODO()
        //consider pos & neg inf + nan
    }
}