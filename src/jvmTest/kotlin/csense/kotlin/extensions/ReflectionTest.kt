package csense.kotlin.extensions

import csense.kotlin.tests.assertions.*
import org.junit.*

class ReflectionTest {

    @Test
    fun fieldGetAs() {
        val clz = TestClz()

        val field = TestClz::class.java.getField(TestClz::field.name)

        field.getAs<String>(clz).assertNotNullAndEquals("field")

        field.getAs<Int>(clz).assertNull()
    }
}

class TestClz {
    @JvmField
    val field: String = "field"
}