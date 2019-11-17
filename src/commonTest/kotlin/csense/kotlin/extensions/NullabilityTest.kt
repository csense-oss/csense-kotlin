package csense.kotlin.extensions

import csense.kotlin.tests.assertions.assertCalled
import csense.kotlin.tests.assertions.assertFalse
import csense.kotlin.tests.assertions.assertNotCalled
import csense.kotlin.tests.assertions.assertTrue
import kotlin.test.Test


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
    fun tIsNull() {
        val onNull: String? = null
        onNull.isNull.assertTrue()
        val onNotNull: String? = ""
        onNotNull.isNull.assertFalse()
    }

    @Test
    fun tIsNotNull() {
        val onNull: String? = null
        onNull.isNotNull.assertFalse()
        val onNotNull: String? = ""
        onNotNull.isNotNull.assertTrue()
    }
}