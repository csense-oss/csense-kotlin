package org.csenseoss.kotlin.extensions.`class`

import org.csenseoss.kotlin.tests.assertions.general.*
import org.junit.jupiter.api.*

class ClassTest {

    @Nested
    inner class TryCast {
        @Test
        fun selfShouldSucceed(){
            val childClass: Class<Child> = Child::class.java
            childClass.tryCast(Child()).assertNotNull()
        }
        @Test
        fun notAssignable() {
            val parentClass: Class<Parent> = Parent::class.java
            parentClass.tryCast(Child()).assertNull("cannot cast child to parent")
        }

        @Test
        fun assignable() {
            val childClass: Class<Child> = Child::class.java
            childClass.tryCast(Parent()).assertNotNull("Should be able to assign a child to a parent (LSP principle)")
        }
    }
}

private open class Parent
private class Child : Parent()