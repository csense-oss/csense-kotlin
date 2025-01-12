package org.csenseoss.kotlin.extensions.references

import org.csenseoss.kotlin.*
import org.csenseoss.kotlin.tests.assertions.*
import org.csenseoss.kotlin.tests.assertions.comparable.*
import org.csenseoss.kotlin.tests.assertions.general.*
import org.junit.jupiter.api.*
import java.lang.ref.*
import java.util.*

class WeakReferenceTest {

    @Test
    fun weakReference() {
        val str = "test"
        val reference: WeakReference<String> = str.weakReference()
        reference.get().assert("test")
    }

    @Test
    fun weakReferenceTUse() {
        val str = "test"
        val ref: WeakReference<String> = str.weakReference()
        assertCalled { shouldBeCalled: () -> Unit ->
            ref.use {
                this.assert("test")
                shouldBeCalled()
            }
        }
    }

    @Test
    fun weakReferenceEmptyFunctionUseInvoke() {
        assertCalled { shouldBeCalled: () -> Unit ->
            val emptyRef: EmptyFunction = { shouldBeCalled() }
            emptyRef.weakReference().useInvoke()
        }
    }

    @Test
    fun weakReferenceFunction0RRUseInvoke() {
        assertCalled { shouldBeCalled: () -> Unit ->
            val emptyRef: Function0R<String> = { shouldBeCalled(); "result" }
            emptyRef.weakReference().useInvoke()
        }
    }

    @Test
    fun weakReferenceTUseOr() {
        val str = "test"
        val ref: WeakReference<String> = str.weakReference()
        assertCalled { shouldBeCalled: () -> Unit ->
            ref.useOr(ifAvailable = {
                this.assert("test")
                shouldBeCalled()
            }, ifNotAvailable = {
                shouldNotBeCalled()
            })
        }
    }

    @Test
    fun weakReferenceWeakReference() {
        val nullable: Optional<String>? = null
        val ref: WeakReference<Optional<String>>? = nullable.weakReference()
        ref.assertNull()

        val nonNull: Optional<String> = Optional.of("")
        nonNull.weakReference().assertNotNull()
    }
}