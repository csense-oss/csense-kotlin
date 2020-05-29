package csense.kotlin.extensions.collections.set

import csense.kotlin.tests.assertions.*
import kotlin.test.*

class SetTest {
    
    //to make IDEA work
    @Test
    fun emptyTest() {
    
    }
    
    class SetTDoesNotContain {
        @Test
        fun empty() {
            val map = setOf<String>()
            map.doesNotContain("1").assertTrue()
        }
        
        @Test
        fun single() {
            val map = setOf("1234")
            map.doesNotContain("1").assertTrue()
            map.doesNotContain("1234").assertFalse()
            map.doesNotContain("12345").assertTrue()
        }
        
        
        @Test
        fun multiple() {
            val map = setOf(123, 444)
            map.doesNotContain(1).assertTrue()
            map.doesNotContain(2).assertTrue()
            map.doesNotContain(123).assertFalse()
            map.doesNotContain(444).assertFalse()
            map.doesNotContain(555).assertTrue()
        }
    }
    
    class SetTSymmetricDifference {
        //region lhs / receiver
        @Test
        fun emptyFirst() {
            //empty to empty
            val empty = setOf<String>().symmetricDifference(setOf())
            empty.first.assertEmpty()
            empty.second.assertEmpty()
            
            val single = setOf<String>().symmetricDifference(setOf("1"))
            single.first.assertEmpty()
            single.second.assertContainsAll("1")
            
            val multiple = setOf<String>().symmetricDifference(setOf("1", "a"))
            
            multiple.first.assertEmpty()
            multiple.second.assertContainsAll("1", "a")
        }
        
        @Test
        fun singleFirst() {
            val empty = setOf<String>("b").symmetricDifference(setOf())
            empty.first.assertContainsAll("b")
            empty.second.assertEmpty()
            
            val single = setOf<String>("b").symmetricDifference(setOf("1"))
            single.first.assertContainsAll("b")
            single.second.assertContainsAll("1")
            
            val multipleWithCollision = setOf<String>("b").symmetricDifference(setOf("1", "a", "b"))
            
            multipleWithCollision.first.assertEmpty()
            multipleWithCollision.second.assertContainsAll("1", "a")
            
            val multipleWithNoCollision = setOf<String>("b").symmetricDifference(setOf("1", "a", "c"))
            multipleWithNoCollision.first.assertContainsAll("b")
            multipleWithNoCollision.second.assertContainsAll("1", "a", "c")
        }
        
        @Test
        fun multipleFirst() {
            val empty = setOf<String>("b", "2").symmetricDifference(setOf())
            empty.first.assertContainsAll("b", "2")
            empty.second.assertEmpty()
            
            val single = setOf<String>("b", "2").symmetricDifference(setOf("1"))
            single.first.assertContainsAll("b", "2")
            single.second.assertContainsAll("1")
            
            val singleCollision = setOf<String>("b", "2", "1").symmetricDifference(setOf("1"))
            singleCollision.first.assertContainsAll("b", "2")
            singleCollision.second.assertEmpty()
            
            val multipleWithCollision = setOf<String>("b", "2").symmetricDifference(setOf("1", "a", "b"))
            multipleWithCollision.first.assertContainsAll("2")
            multipleWithCollision.second.assertContainsAll("1", "a")
            
            val firstOnlyCollision = setOf<String>("a", "1").symmetricDifference(setOf("1", "a", "b"))
            firstOnlyCollision.first.assertEmpty()
            firstOnlyCollision.second.assertContainsAll("b")
            
            val multipleWithNoCollision = setOf<String>("b", "2").symmetricDifference(setOf("1", "a", "c"))
            multipleWithNoCollision.first.assertContainsAll("b", "2")
            multipleWithNoCollision.second.assertContainsAll("1", "a", "c")
            
        }
        //endregion
    }
}