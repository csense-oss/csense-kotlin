package csense.kotlin.extensions.reflections

import csense.kotlin.tests.assertions.*
import org.junit.jupiter.api.Test

class ReflectionTest {

    @Test
    fun fieldGetAs() {
        val clz = TestClz()

        val field = TestClz::class.java.getField(TestClz::field.name)

        field.getAs<String>(clz).assert("field")

        field.getAs<Int>(clz).assertNull()
    }
}

class TestClz {
    @JvmField
    val field: String = "field"
}