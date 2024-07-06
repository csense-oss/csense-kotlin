package org.csenseoss.kotlin.extensions.java.io.inputstream

import java.io.*
import java.nio.charset.Charset

public fun InputStream.readText(charset: Charset = Charsets.UTF_8): String {
    return bufferedReader(charset = charset).use { it: BufferedReader ->
        it.readText()
    }
}