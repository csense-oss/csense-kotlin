package csense.kotlin.extensions.collections

import csense.kotlin.extensions.collections.set.setExistence
import csense.kotlin.extensions.collections.set.toggleExistence
import csense.kotlin.tests.assertions.assert
import csense.kotlin.tests.assertions.assertEmpty
import csense.kotlin.tests.assertions.assertSize
import kotlin.test.Test

class SetTest {

    @Test
    fun toggleExistence() {

        val set = mutableSetOf<Int>()
        set.assertEmpty()
        set.toggleExistence(42)
        set.assertSize(1)

        set.first().assert(42)

        set.toggleExistence(42)
        set.assertEmpty()


    }

    @Test
    fun setExistence() {

        val set = mutableSetOf<Int>()
        set.assertEmpty()
        set.setExistence(42, true)
        set.assertSize(1)
        set.setExistence(1, false)

        set.first().assert(42)

        set.assertSize(1)
        set.setExistence(42, false)
        set.assertEmpty()

    }
}