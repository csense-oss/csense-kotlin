package csense.kotlin.extensions.stringBuilder

import csense.kotlin.tests.assertions.*
import kotlin.test.*

class SetTest {
    @Test
    fun setContent() {
        val builder = StringBuilder()
        builder.set("")
        builder.toString().assert("")
        builder.set("1234")
        builder.toString().assert("1234")
        builder.set("qwerty")
        builder.toString().assert("qwerty")
    }


    @Test
    fun setCharArray() {
        val builder = StringBuilder()
        builder.set(charArrayOf())
        builder.toString().assert("")
        builder.set(charArrayOf('1', '2', '3'))
        builder.toString().assert("123")
        builder.set(charArrayOf('q', 'w', 'e'))
        builder.toString().assert("qwe")
    }
}