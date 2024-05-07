package org.csenseoss.kotlin.extensions.collections.array.typed.char


public fun CharArray.toByteArray(): ByteArray {
    return ByteArray(this.size) { index ->
        this[index].code.toByte()
    }
}