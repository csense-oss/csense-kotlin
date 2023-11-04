package csense.kotlin.extensions.collections.set.mutable

import csense.kotlin.tests.assertions.*
import kotlin.test.*

class AddTest {


    @Test
    fun empty(): Unit = assertCalled { shouldBeCalled: () -> Unit ->
        val set: MutableSet<String> = mutableSetOf()
        val didAdd: Boolean = set.addIfMissingAnd("item") { shouldBeCalled() }
        didAdd.assertTrue()
        set.assertSingle("item")
    }


    @Test
    fun singleMissing() = assertCalled { shouldBeCalled: () -> Unit ->
        val set: MutableSet<String> = mutableSetOf("test")
        val didAdd: Boolean = set.addIfMissingAnd("item") { shouldBeCalled() }
        didAdd.assertTrue()
        set.assertSize(2)
        set.assertContainsAll("test", "item")
    }

    @Test
    fun singleAlreadyThere() {
        val set: MutableSet<String> = mutableSetOf("test")
        val didAdd: Boolean = set.addIfMissingAnd("test") { shouldNotBeCalled() }
        didAdd.assertFalse("already presented")
        set.assertSingle("test")
    }


    @Test
    fun multipleAlreadyThere() {
        val set: MutableSet<String> = mutableSetOf("1", "2")
        val didAdd: Boolean = set.addIfMissingAnd("1") { shouldNotBeCalled() }
        didAdd.assertFalse("already presented")
        set.assertSize(2)
        set.assertContainsAll("1", "2")
    }

    @Test
    fun multipleMissing() = assertCalled { shouldBeCalled: () -> Unit ->
        val set: MutableSet<String> = mutableSetOf("1", "2")
        val didAdd: Boolean = set.addIfMissingAnd("3") { shouldBeCalled() }
        didAdd.assertTrue()
        set.assertSize(3)
        set.assertContainsAll("1", "2", "3")
    }

}