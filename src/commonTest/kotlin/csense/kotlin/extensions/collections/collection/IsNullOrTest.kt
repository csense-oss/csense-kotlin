@file:Suppress("RedundantNullableReturnType")

package csense.kotlin.extensions.collections.collection

import csense.kotlin.tests.assertions.*
import kotlin.test.*

class IsNullOrTest {
    @Test
    fun isNotNullOrEmpty() {
        val nullCol: Collection<String>? = null
        nullCol.isNotNullOrEmpty().assert(false)
        val emptyCol: Collection<String>? = listOf()
        emptyCol.isNotNullOrEmpty().assert(false)
        val singleCol: Collection<String>? = listOf("omg")
        singleCol.isNotNullOrEmpty().assert(true)
    }

}