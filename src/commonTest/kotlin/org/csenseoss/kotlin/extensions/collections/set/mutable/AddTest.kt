package org.csenseoss.kotlin.extensions.collections.set.mutable

import org.csenseoss.kotlin.tests.assertions.*
import org.csenseoss.kotlin.tests.assertions.collections.iterable.*
import org.csenseoss.kotlin.tests.assertions.general.*
import org.csenseoss.kotlin.tests.assertions.primitives.boolean.*
import kotlin.test.*

class AddTest {


    @Test
    fun empty(): Unit = assertCalled { shouldBeCalled: () -> Unit ->
        val set: MutableSet<String> = mutableSetOf()
        val didAdd: Boolean = set.addIfMissingAnd("item") { shouldBeCalled() }
        didAdd.assertTrue()
        set.assert("item")
    }


    @Test
    fun singleMissing() = assertCalled { shouldBeCalled: () -> Unit ->
        val set: MutableSet<String> = mutableSetOf("test")
        val didAdd: Boolean = set.addIfMissingAnd("item") { shouldBeCalled() }
        didAdd.assertTrue()
        set.assert("test", "item")
    }

    @Test
    fun singleAlreadyThere() {
        val set: MutableSet<String> = mutableSetOf("test")
        val didAdd: Boolean = set.addIfMissingAnd("test") { shouldNotBeCalled() }
        didAdd.assertFalse("already presented")
        set.assert("test")
    }


    @Test
    fun multipleAlreadyThere() {
        val set: MutableSet<String> = mutableSetOf("1", "2")
        val didAdd: Boolean = set.addIfMissingAnd("1") { shouldNotBeCalled() }
        didAdd.assertFalse("already presented")
        set.assert("1", "2")
    }

    @Test
    fun multipleMissing() = assertCalled { shouldBeCalled: () -> Unit ->
        val set: MutableSet<String> = mutableSetOf("1", "2")
        val didAdd: Boolean = set.addIfMissingAnd("3") { shouldBeCalled() }
        didAdd.assertTrue()
        set.assert("1", "2", "3")
    }

}