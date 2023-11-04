package csense.kotlin.extensions.general

import csense.kotlin.tests.assertions.*
import kotlin.test.*

class UseOrTest {

    @Test
    fun tUseOr() {
        var optStringCounter = 0
        val optString: String? = null
        optString.useOr({ failTest("null is not a string") }, { optStringCounter += 1 })
        optStringCounter.assert(1, "should run the ifNull Callback")

        var stringCounter = 0

        @Suppress("RedundantNullableReturnType")
        val stringValue: String? = "magic test"
        stringValue.useOr({ stringCounter += length }, { failTest("magic test is not null") })
        stringCounter.assert(stringValue?.length ?: 0, "should get the right string back.")
    }

}