package csense.kotlin.extensions.nullabillity

import csense.kotlin.tests.assertions.*
import kotlin.test.*

class IfTest {
    @Test
    fun ifNullAction() {
        val onNull: String? = null
        assertCalled { signal ->
            onNull.ifNull(signal)
        }
        @Suppress("RedundantNullableReturnType")
        val onNotNull: String? = ""
        assertNotCalled { signal ->
            onNotNull.ifNull(signal)
        }
    }

    @Test
    fun ifNotNullAction() {
        val onNull: String? = null
        assertNotCalled { signal ->
            onNull.ifNotNull { signal() }
        }
        @Suppress("RedundantNullableReturnType")
        val onNotNull: String? = ""
        assertCalled { signal ->
            onNotNull.ifNotNull { signal() }
        }
    }

}