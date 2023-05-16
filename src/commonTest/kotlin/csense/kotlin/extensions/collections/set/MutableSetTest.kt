package csense.kotlin.extensions.collections.set

import csense.kotlin.extensions.collections.set.mutable.*
import csense.kotlin.tests.assertions.*
import kotlin.test.*

class MutableSetTest {

    @Test
    fun toggleExistence() {
        val col = mutableSetOf<String>()

        col.toggleExistence("asd")
        col.assertSize(1)
        col.assertContains("asd")

        col.toggleExistence("asd")
        col.assertSize(0)
        col.assertContainsNot("asd")


        col.toggleExistence("asd1")
        col.toggleExistence("asd2")
        col.assertSize(2)
        col.assertContainsAll("asd1", "asd2")
    }

    @Test
    fun setExistence() {
        val col = mutableSetOf<String>()

        col.setExistence("asd", false)
        col.assertSize(0)

        col.setExistence("asd", true)
        col.assertSize(1)
        col.assertContains("asd")

        col.setExistence("asd", true)
        col.assertSize(1)
        col.assertContains("asd")

        col.setExistence("asd", false)
        col.assertSize(0)

        col.setExistence("asd1", true)
        col.setExistence("asd2", true)
        col.assertSize(2)
        col.assertContainsAll("asd1", "asd2")

        col.setExistence("asd2", false)
        col.assertSize(1)
        col.assertContains("asd1")
    }

    class MutableSetTAddIfMissingAnd {

        @Test
        fun empty() = assertCalled { shouldBeCalled ->
            val set = mutableSetOf<String>()
            val didAdd = set.addIfMissingAnd("item") { shouldBeCalled() }
            didAdd.assertTrue()
            set.assertSingle("item")
        }


        @Test
        fun singleMissing() = assertCalled { shouldBeCalled ->
            val set = mutableSetOf("test")
            val didAdd = set.addIfMissingAnd("item") { shouldBeCalled() }
            didAdd.assertTrue()
            set.assertSize(2)
            set.assertContainsAll("test", "item")
        }

        @Test
        fun singleAlreadyThere() {
            val set = mutableSetOf("test")
            val didAdd = set.addIfMissingAnd("test") { shouldNotBeCalled() }
            didAdd.assertFalse("already presented")
            set.assertSingle("test")
        }


        @Test
        fun multipleAlreadyThere() {
            val set = mutableSetOf("1", "2")
            val didAdd = set.addIfMissingAnd("1") { shouldNotBeCalled() }
            didAdd.assertFalse("already presented")
            set.assertSize(2)
            set.assertContainsAll("1", "2")
        }

        @Test
        fun multipleMissing() = assertCalled { shouldBeCalled ->
            val set = mutableSetOf("1", "2")
            val didAdd = set.addIfMissingAnd("3") { shouldBeCalled() }
            didAdd.assertTrue()
            set.assertSize(3)
            set.assertContainsAll("1", "2", "3")
        }
    }
}