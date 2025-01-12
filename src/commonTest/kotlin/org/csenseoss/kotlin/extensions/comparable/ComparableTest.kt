package org.csenseoss.kotlin.extensions.comparable

import org.csenseoss.kotlin.tests.assertions.*
import org.csenseoss.kotlin.tests.assertions.primitives.boolean.*
import kotlin.test.*

class ComparableTest {
    @Test
    fun isEqualTo() {
        val first: Comparable<String> = "test"
        first.isEqualTo("1234").assertFalse()
        first.isEqualTo("test").assertTrue()
        first.isEqualTo("tesT").assertFalse()
    }

    @Test
    fun isNotEqualTo(){
        val first: Comparable<String> = "test"
        first.isNotEqualTo("1234").assertTrue()
        first.isNotEqualTo("test").assertFalse()
        first.isNotEqualTo("tesT").assertTrue()
    }

    @Test
    fun isGreaterThan(){
        val comp: Comparable<Int> = 42
        comp.isGreaterThan(0).assertTrue()
        comp.isGreaterThan(42).assertFalse()
        comp.isGreaterThan(44).assertFalse()
    }

    @Test
    fun isGreaterThanOrEqualTo(){
        val comp: Comparable<Int> = 42
        comp.isGreaterThanOrEqualTo(0).assertTrue()
        comp.isGreaterThanOrEqualTo(42).assertTrue()
        comp.isGreaterThanOrEqualTo(44).assertFalse()
    }

    @Test
    fun isLessThan(){
        val comp: Comparable<Int> = 42
        comp.isLessThan(0).assertFalse()
        comp.isLessThan(42).assertFalse()
        comp.isLessThan(44).assertTrue()
    }
    @Test
    fun isLessThanOrEqualTo(){
        val comp: Comparable<Int> = 42
        comp.isLessThanOrEqualTo(0).assertFalse()
        comp.isLessThanOrEqualTo(42).assertTrue()
        comp.isLessThanOrEqualTo(44).assertTrue()
    }
}