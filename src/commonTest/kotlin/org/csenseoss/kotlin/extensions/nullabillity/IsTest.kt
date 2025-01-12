@file:Suppress("unused")

package org.csenseoss.kotlin.extensions.nullabillity

import org.csenseoss.kotlin.tests.assertions.*
import org.csenseoss.kotlin.tests.assertions.primitives.boolean.*
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