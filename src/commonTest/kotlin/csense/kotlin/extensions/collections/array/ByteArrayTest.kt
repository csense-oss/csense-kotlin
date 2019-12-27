package csense.kotlin.extensions.collections.array

import csense.kotlin.tests.assertions.assert
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
}