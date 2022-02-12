package csense.kotlin.extensions.java.io

import csense.kotlin.tests.assertions.*
import org.junit.jupiter.api.*
import java.io.*

class InputStreamTest {

    class ReadOrNullOnEnd {
        @Test
        fun empty() {
            val byteInput = ByteArrayInputStream(byteArrayOf())
            byteInput.readOrNullOnEnd().assertNull("no content")
        }

        @Test
        fun single() {
            val byteInput = ByteArrayInputStream(byteArrayOf(42))
            byteInput.readOrNullOnEnd().assert(42)
            byteInput.readOrNullOnEnd().assertNull("end should have been reached now")
        }

        @Test
        fun multiple() {
            val byteInput = ByteArrayInputStream(byteArrayOf(42, 11, 99))
            byteInput.readOrNullOnEnd().assert(42)
            byteInput.readOrNullOnEnd().assert(11)
            byteInput.readOrNullOnEnd().assert(99)
            byteInput.readOrNullOnEnd().assertNull("end should have been reached now")
        }

    }

    class ReadToEnd {
        @Test
        fun empty() {
            val byteInput = InputStreamDecorator(ByteArrayInputStream(byteArrayOf()))
            byteInput.readToEnd()
            byteInput.readCalledCounter.assert(1, message = "would be called size +1")
        }

        @Test
        fun single() {
            val byteInput = InputStreamDecorator(ByteArrayInputStream(byteArrayOf(42)))
            byteInput.readToEnd()
            byteInput.readCalledCounter.assert(2, message = "would be called size +1")
        }

        @Test
        fun multiple() {
            val byteInput = InputStreamDecorator(ByteArrayInputStream(byteArrayOf(42, 11, 99)))
            byteInput.readToEnd()
            byteInput.readCalledCounter.assert(4, message = "would be called size +1")
        }
    }

    class InputStreamDecorator(
        private val other: InputStream
    ) : InputStream() {
        var readCalledCounter: Int = 0
            private set


        override fun read(): Int {
            readCalledCounter += 1
            return other.read()
        }
    }

}