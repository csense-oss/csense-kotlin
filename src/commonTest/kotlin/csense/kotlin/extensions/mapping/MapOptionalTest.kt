package csense.kotlin.extensions.mapping

import csense.kotlin.tests.assertions.*
import kotlin.test.*

class MapOptionalTest {
    @Test
    fun anyMapOptional() {
        val strNull: String? = null
        strNull.mapOptional(ifNotNull = 42, ifNull = 0).assert(expected = 0, message = "should map to ifNull")
        @Suppress("RedundantNullableReturnType")
        val strNotNull: String? = "NotNull"
        strNotNull.mapOptional(ifNotNull = 42, ifNull = 0).assert(expected = 42, message = "should map to ifNotNull")
    }

    @Test
    fun TMapLazyOptional() {
        val strNull: String? = null
        assertCalled { shouldBeCalled: () -> Unit ->
            strNull.mapLazyOptional(
                ifNotNull = { failTest() },
                ifNull = { shouldBeCalled() }
            )
        }
        @Suppress("RedundantNullableReturnType")
        val strNotNull: String? = "NotNull"
        assertCalled { shouldBeCalled: () -> Unit ->
            strNotNull.mapLazyOptional(
                ifNotNull = { shouldBeCalled() },
                ifNull = { failTest() }
            )
        }
    }

}