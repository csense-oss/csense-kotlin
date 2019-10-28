@file:Suppress("NOTHING_TO_INLINE", "MissingTestFunction")

package csense.kotlin.test.assertions

inline fun IntArray.assertSize(size: Int, message: String = "") = this.size.assert(size, message)

inline fun BooleanArray.assertSize(size: Int, message: String = "") = this.size.assert(size, message)

inline fun DoubleArray.assertSize(size: Int, message: String = "") = this.size.assert(size, message)

inline fun LongArray.assertSize(size: Int, message: String = "") = this.size.assert(size, message)

inline fun ByteArray.assertSize(size: Int, message: String = "") = this.size.assert(size, message)

inline fun ShortArray.assertSize(size: Int, message: String = "") = this.size.assert(size, message)

inline fun CharArray.assertSize(size: Int, message: String = "") = this.size.assert(size, message)

inline fun FloatArray.assertSize(size: Int, message: String = "") = this.size.assert(size, message)