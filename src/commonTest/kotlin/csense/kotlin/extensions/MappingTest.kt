@file:Suppress("unused")

package csense.kotlin.extensions

import csense.kotlin.tests.assertions.*
import kotlin.test.Test

class MappingTest {

    @Test
    fun mapOptional() {
        val strNull: String? = null
        strNull.mapOptional(42, 0).assert(0, "should map to ifNull")
        val strNotNull: String? = "NotNull"
        strNotNull.mapOptional(42, 0).assert(42, "should map to ifNotNull")
    }

    @Test
    fun mapLazyOptional() {
        val strNull: String? = null
        assertCalled {
            strNull.mapLazyOptional(
                    ifNotNull = { failTest() },
                    ifNull = { it() }
            )
        }
        val strNotNull: String? = "NotNull"
        assertCalled {
            strNotNull.mapLazyOptional(
                    ifNotNull = { it() },
                    ifNull = { failTest() }
            )
        }
    }

    @Test
    fun map() {
        true.map(42, 0).assert(42)
        false.map(42, 0).assert(0)
    }

    @Test
    fun mapLazy() {
        true.mapLazy({ 42 }, { failTest() }).assert(42)
        false.mapLazy({ failTest() }, { 42 }).assert(42)
    }

    class MapToSet {

        @Test
        fun testEmpty() {
            val lst = listOf<String>()
            lst.mapToSet { it }.assertEmpty()
        }

        @Test
        fun testDuplicates() {
            (0 until 10).mapToSet { it.rem(2) }.apply {
                assertSize(2, "since we either get 0 or 1, there will only be 2 elements")
                assertContainsAll(0, 1)
            }
        }

        @Test
        fun testNonDuplicates() {
            (0 until 10).mapToSet { "$it" }.apply {
                assertSize(10, "should map each element (and since non is duplicated)")
                for (i in 0 until 10) {
                    this.contains("$i").assertTrue()
                }
            }
        }
    }
}