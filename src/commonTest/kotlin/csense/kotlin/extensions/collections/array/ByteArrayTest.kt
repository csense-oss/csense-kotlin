package csense.kotlin.extensions.collections.array

import csense.kotlin.extensions.*
import csense.kotlin.tests.assertions.*
import kotlin.test.Test

class ByteArrayTest {

    @Test
    fun byteArrayToHexString() {
        byteArrayOf().toHexString().assert("")
        byteArrayOf().toHexString(
            appendHexPrefix = true,
            shouldBeUppercase = false
        ).assert("")

        byteArrayOf().toHexString(
            appendHexPrefix = true,
            shouldBeUppercase = true
        ).assert("")

        byteArrayOf(0x0).toHexString(
            appendHexPrefix = true,
            shouldBeUppercase = false
        ).assert("0x00")

        byteArrayOf(0x0).toHexString(
            appendHexPrefix = true,
            shouldBeUppercase = true
        ).assert("0x00")

        byteArrayOf(0x0a).toHexString(
            appendHexPrefix = true,
            shouldBeUppercase = false
        ).assert("0x0a")

        byteArrayOf(0x0a).toHexString(
            appendHexPrefix = true,
            shouldBeUppercase = true
        ).assert("0x0A")

        byteArrayOf(0x0a).toHexString(
            appendHexPrefix = false,
            shouldBeUppercase = true
        ).assert("0A")

        byteArrayOf(0x0a).toHexString(
            appendHexPrefix = false,
            shouldBeUppercase = false
        ).assert("0a")

        byteArrayOf(0x0a, 0x0f, 0xb, 0x55).toHexString(
            appendHexPrefix = true,
            shouldBeUppercase = false
        ).assert("0x0a0f0b55")

        byteArrayOf(0x0a, 0x0f, 0xb, 0x55).toHexString(
            appendHexPrefix = true,
            shouldBeUppercase = true
        ).assert("0x0A0F0B55")
    }


    class ByteArrayForEachBackwards {
        @Test
        fun empty() {
            byteArrayOf().forEachBackwards { shouldNotBeCalled() }
        }

        @Test
        fun single() = assertCalled(times = 1) { shouldBeCalled ->
            byteArrayOf(42).forEachBackwards {
                it.assert(42)
                shouldBeCalled()
            }
        }

        @Test
        fun multiple() = assertCalled(times = 2) { shouldBeCalled ->
            var toggle = false
            byteArrayOf(42, 11).forEachBackwards {
                it.assert(toggle.map(42, 11))
                toggle = true
                shouldBeCalled()
            }
        }

    }

    class ByteArrayForEachDiscard {
        @Test
        fun empty() {
            byteArrayOf().forEachDiscard { shouldNotBeCalled() }
        }

        @Test
        fun single() = assertCalled(times = 1) { shouldBeCalled ->
            byteArrayOf(42).forEachDiscard {
                it.assert(42)
                shouldBeCalled()
                return@forEachDiscard ""
            }
        }

        @Test
        fun multiple() = assertCalled(times = 2) { shouldBeCalled ->
            var toggle = false
            byteArrayOf(42, 11).forEachDiscard {
                it.assert(toggle.map(11, 42))
                toggle = true
                shouldBeCalled()
                return@forEachDiscard ""
            }
        }

    }

}