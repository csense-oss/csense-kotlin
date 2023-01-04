package csense.kotlin.extensions.primitives.string

import csense.kotlin.tests.assertions.*
import org.junit.jupiter.api.*
import java.util.*

class StringJvmTest {

    @Test
    fun string() {
        String(intArrayOf(48)).assert("0")
        String(intArrayOf(48, 49)).assert("01")
    }

    class StringTitleCaseFirstWord {

        @Test
        fun empty() {
            "".titleCaseFirstWord(Locale.getDefault()).assert("")
        }

        @Test
        fun singleLowercase() {
            "a".titleCaseFirstWord(Locale.getDefault()).assert("A")
        }

        @Test
        fun singleLowercaseSpecial() {
            "Ǆ".titleCaseFirstWord(Locale.getDefault()).assert("ǅ")
            "ǆ".titleCaseFirstWord(Locale.getDefault()).assert("ǅ")
            "hej med".titleCaseFirstWord(Locale.getDefault()).assert("Hej med")
        }
    }
}