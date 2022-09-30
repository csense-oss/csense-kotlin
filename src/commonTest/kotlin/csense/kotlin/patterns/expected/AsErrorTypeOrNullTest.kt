package csense.kotlin.patterns.expected

import csense.kotlin.tests.assertions.*
import kotlin.test.*

class AsErrorTypeOrNullTest {
    @Test
    fun badTypeShouldGiveNull() {
        val failed = Expected.Failed(RuntimeException())
        failed.asErrorTypeOrNull<ErrorTypeException>().assertNull()
    }

    @Test
    fun castAbleTypeShouldGiveTheType() {
        val failed = Expected.Failed(RuntimeException())
        failed.asErrorTypeOrNull<RuntimeException>().assertByEquals(failed)
        failed.asErrorTypeOrNull<RuntimeException>().assertNotNullApply {
            error.assertIs<RuntimeException>()
        }
        failed.asErrorTypeOrNull<Exception>().assertNotNullApply {
            error.assertIs<Exception>()
        }
    }
}

