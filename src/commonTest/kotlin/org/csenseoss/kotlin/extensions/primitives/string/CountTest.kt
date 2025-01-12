package org.csenseoss.kotlin.extensions.primitives.string

import org.csenseoss.kotlin.tests.assertions.comparable.*
import kotlin.test.*

class CountTest {
    @Test
    fun empty() {
        "".count('a').assert(0)
        "".count(' ').assert(0)
    }
    @Test
    fun nonMatching(){
        "b".count('a').assert(0)
        " ".count('a').assert(0)
    }
    @Test
    fun matching(){
        "a".count('a').assert(1)
        "ab".count('a').assert(1)
        "abc".count('a').assert(1)
        "abca".count('a').assert(2)
        "aAba".count('a').assert(2, message = "is case sensitive by default")
    }

    @Test
    fun respectsCasing(){
        "A".count('a', ignoreCase = true).assert(1)
        "Ab".count('a', ignoreCase = true).assert(1)
        "Abc".count('a', ignoreCase = true).assert(1)
        "Abca".count('a', ignoreCase = true).assert(2)
        "aAba".count('a', ignoreCase = true).assert(3)
    }
}