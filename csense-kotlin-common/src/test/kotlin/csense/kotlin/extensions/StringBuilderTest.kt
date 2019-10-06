package csense.kotlin.extensions

import csense.kotlin.test.assertions.*
import kotlin.test.*

class StringBuilderTest {

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

    @Test
    fun appendContentOf() {
        val builder = StringBuilder()
        builder.appendContentOf(charArrayOf())
        builder.toString().assert("")
        builder.appendContentOf(charArrayOf('a'))
        builder.toString().assert("a")
        builder.appendContentOf(charArrayOf('b'))
        builder.toString().assert("ab")
        builder.appendContentOf(charArrayOf('1', '2'))
        builder.toString().assert("ab12")
    }

}