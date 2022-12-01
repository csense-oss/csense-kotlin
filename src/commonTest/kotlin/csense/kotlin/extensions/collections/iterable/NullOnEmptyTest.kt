package csense.kotlin.extensions.collections.iterable

import csense.kotlin.tests.assertions.*
import kotlin.test.*

class NullOnEmptyTest {
    @Test
    fun empty() {
        listOf<String>()
            .nullOnEmpty()
            .assertNull("as the name suggest, should be null on empty")
    }

    @Test
    fun single() {
        listOf("a")
            .nullOnEmpty()
            .assertNotNullApply("should not be null and have a single element that is 'a'") {
                assertSize(1)
                first().assert("a")
            }
    }

    @Test
    fun multiple() {
        listOf("1", "b", "3")
            .nullOnEmpty()
            .assertNotNullApply("should not be null and have a 3 elements") {
                assertSize(3)
                this.elementAt(0).assert("1")
                this.elementAt(1).assert("b")
                this.elementAt(2).assert("3")
            }
    }
}