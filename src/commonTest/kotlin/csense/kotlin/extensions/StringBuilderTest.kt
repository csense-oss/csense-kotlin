package csense.kotlin.extensions

import csense.kotlin.tests.assertions.*
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

    class StringBuilderAppendLineIfNotEmpty {
        @Test
        fun onEmpty() {
            val sb = StringBuilder()
            sb.appendLineIfNotEmpty("")
            sb.assert("") //  message:"Should not have content"
        }

        @Test
        fun onContent() {
            val sb = StringBuilder()
            sb.appendLineIfNotEmpty("test")
            sb.assert("test\n") //  message:"Should not have content"
        }

    }

    class StringBuilderAppendLineIfNotBlank {
        @Test
        fun onEmpty() {
            val sb = StringBuilder()
            sb.appendLineIfNotBlank("")
            sb.assert("") //  message:"Should not have content"
        }

        @Test
        fun onBlank() {
            val sb = StringBuilder()
            sb.appendLineIfNotBlank(" \t")
            sb.assert("") //  message:"Should not have content"
        }

        @Test
        fun onContent() {
            val sb = StringBuilder()
            sb.appendLineIfNotBlank("test")
            sb.assert("test\n") //  message:"Should not have content"
        }

    }
}