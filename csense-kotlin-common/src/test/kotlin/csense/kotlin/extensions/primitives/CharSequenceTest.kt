package csense.kotlin.extensions.primitives

import csense.kotlin.test.assertions.*
import kotlin.test.*

class CharSequenceTest {

    @Test
    fun isNotNullOrBlank() {
        val nullStr: String? = null
        nullStr.isNotNullOrBlank().assert(false)
        "".isNotNullOrBlank().assert(false)
        " ".isNotNullOrBlank().assert(false)
        "test".isNotNullOrBlank().assert(true)
    }

    @Test
    fun isNotNullOrEmpty() {
        val nullStr: String? = null
        nullStr.isNotNullOrEmpty()
        "".isNotNullOrEmpty().assert(false)
        " ".isNotNullOrEmpty().assert(true)
        "test".isNotNullOrEmpty().assert(true)
    }
}