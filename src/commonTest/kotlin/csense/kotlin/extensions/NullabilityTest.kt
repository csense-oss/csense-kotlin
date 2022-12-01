package csense.kotlin.extensions

import csense.kotlin.extensions.nullabillity.*
import csense.kotlin.tests.assertions.*
import kotlin.test.*


class NullabilityTest {

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

    class TIsNull{
        
        @Test
        fun property(){
            val onNull: String? = null
            onNull.isNull.assertTrue()
            @Suppress("RedundantNullableReturnType")
            val onNotNull: String? = ""
            onNotNull.isNull.assertFalse()
        }
        
        @Test
        fun method(){
            val onNull: String? = null
            onNull.isNull().assertTrue()
            @Suppress("RedundantNullableReturnType")
            val onNotNull: String? = ""
            onNotNull.isNull().assertFalse()
        }
    }
    
    class TIsNotNull{
        
        @Test
        fun property(){
            val onNull: String? = null
            onNull.isNotNull.assertFalse()
            @Suppress("RedundantNullableReturnType")
            val onNotNull: String? = ""
            onNotNull.isNotNull.assertTrue()
        }
        
        @Test
        fun method(){
            val onNull: String? = null
            onNull.isNotNull().assertFalse()
            @Suppress("RedundantNullableReturnType")
            val onNotNull: String? = ""
            onNotNull.isNotNull().assertTrue()
        }
    }

}