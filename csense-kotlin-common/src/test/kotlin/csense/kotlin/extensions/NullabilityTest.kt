package csense.kotlin.extensions

import csense.kotlin.test.assertions.*
import kotlin.test.*


class NullabilityTest {

    @Test
    fun ifNull() {
        val onNull: String? = null
        assertCalled { signal ->
            onNull.ifNull(signal)
        }
        val onNotNull: String? = ""
        assertNotCalled { signal ->
            onNotNull.ifNull(signal)
        }
    }

    @Test
    fun ifNotNull() {
        val onNull: String? = null
        assertNotCalled { signal ->
            onNull.ifNotNull { signal() }
        }
        val onNotNull: String? = ""
        assertCalled { signal ->
            onNotNull.ifNotNull { signal() }
        }
    }

    @Test
    fun isNull() {
        val onNull: String? = null
        onNull.isNull.assertTrue()
        val onNotNull: String? = ""
        onNotNull.isNull.assertFalse()
    }

    @Test
    fun isNotNull() {
        val onNull: String? = null
        onNull.isNotNull.assertFalse()
        val onNotNull: String? = ""
        onNotNull.isNotNull.assertTrue()
    }
}