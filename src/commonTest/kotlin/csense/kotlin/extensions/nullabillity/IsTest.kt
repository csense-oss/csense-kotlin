@file:Suppress("unused")

package csense.kotlin.extensions.nullabillity

import csense.kotlin.tests.assertions.*
import kotlin.test.*


class IsTest {
    class TIsNull {

        @Test
        fun property() {
            val onNull: String? = null
            onNull.isNull.assertTrue()
            @Suppress("RedundantNullableReturnType")
            val onNotNull: String? = ""
            onNotNull.isNull.assertFalse()
        }

        @Test
        fun method() {
            val onNull: String? = null
            onNull.isNull().assertTrue()
            @Suppress("RedundantNullableReturnType")
            val onNotNull: String? = ""
            onNotNull.isNull().assertFalse()
        }
    }

    class TIsNotNull {

        @Test
        fun property() {
            val onNull: String? = null
            onNull.isNotNull.assertFalse()
            @Suppress("RedundantNullableReturnType")
            val onNotNull: String? = ""
            onNotNull.isNotNull.assertTrue()
        }

        @Test
        fun method() {
            val onNull: String? = null
            onNull.isNotNull().assertFalse()
            @Suppress("RedundantNullableReturnType")
            val onNotNull: String? = ""
            onNotNull.isNotNull().assertTrue()
        }
    }

}