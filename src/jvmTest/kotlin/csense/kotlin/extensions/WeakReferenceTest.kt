package csense.kotlin.extensions

import csense.kotlin.*
import csense.kotlin.tests.assertions.*
import org.junit.jupiter.api.*
import java.util.*

class WeakReferenceTest {

    @Test
    fun weakReference() {
        val str = "test"
        str.weakReference().assertNotNullApply {
            this.get().assertNotNullAndEquals("test")
        }

    }

    @Test
    fun weakReferenceTUse() {
        val str = "test"
        val ref = str.weakReference()
        assertCalled { shouldBeCalled ->
            ref.use {
                this.assert("test")
                shouldBeCalled()
            }
        }
    }

    @Test
    fun weakReferenceEmptyFunctionUseInvoke() {
        assertCalled { shouldBeCalled ->
            val emptyRef: EmptyFunction = { shouldBeCalled() }
            emptyRef.weakReference().useInvoke()
        }
    }

    @Test
    fun weakReferenceFunction0RRUseInvoke() {
        assertCalled { shouldBeCalled ->
            val emptyRef: Function0R<String> = { shouldBeCalled(); "result" }
            emptyRef.weakReference().useInvoke()
        }
    }

    @Test
    fun weakReferenceTUseOr() {
        val str = "test"
        val ref = str.weakReference()
        assertCalled { shouldBeCalled ->
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
        val ref = nullable.weakReference()
        ref.assertNull()

        val nonNull: Optional<String> = Optional.of("")
        nonNull.weakReference().assertNotNull()
    }
}