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
    
    class SetEContainsAny {
        @Test
        fun emptySet() {
            setOf<String>().containsAny(listOf()).assertFalse()
            setOf<String>().containsAny(listOf("1")).assertFalse()
            setOf<String>().containsAny(listOf("1", "2")).assertFalse()
        }
        
        @Test
        fun emptyOther() {
            setOf("1", "2").containsAny(listOf()).assertFalse()
            setOf("1").containsAny(listOf()).assertFalse()
            setOf("1", "2", "3").containsAny(listOf()).assertFalse()
        }
        
        @Test
        fun singleItemInBoth() {
            setOf("1").containsAny(listOf("1")).assertTrue()
            setOf("1").containsAny(listOf("2")).assertFalse()
            setOf("3").containsAny(listOf("2")).assertFalse()
        }
        
        @Test
        fun multipleHaveItems() {
            
            setOf("1", "4").containsAny(listOf("1")).assertTrue()
            setOf("1", "4").containsAny(listOf("4")).assertTrue()
            setOf("1", "4").containsAny(listOf("5")).assertFalse()
            
            setOf("1", "2").containsAny(listOf("2", "1")).assertTrue()
            setOf("4", "6").containsAny(listOf("2", "1")).assertFalse()
            
            setOf("3", "9").containsAny(listOf("2", "1", "3")).assertTrue()
        }
        
        // since sets are optimized we want to assert that it still works (performance is for benchmark tests)
        @Test
        fun sampleForSets() {
            setOf<String>().containsAny(setOf()).assertFalse()
            setOf("1").containsAny(setOf()).assertFalse()
            setOf<String>().containsAny(setOf("1")).assertFalse()
            setOf<String>("1").containsAny(setOf("1")).assertTrue()
            setOf<String>("1").containsAny(setOf("2")).assertFalse()
            
            setOf<String>("2").containsAny(setOf("1", "3")).assertFalse()
            setOf<String>("2").containsAny(setOf("1", "2")).assertTrue()
            
            setOf<String>("2", "3").containsAny(setOf("1")).assertFalse()
            setOf<String>("2", "3").containsAny(setOf("2")).assertTrue()
            setOf<String>("2", "3").containsAny(setOf("3")).assertTrue()
            setOf<String>("2", "3").containsAny(setOf("4")).assertFalse()
            setOf<String>("2", "3").containsAny(setOf("4", "2")).assertTrue()
        }
    }
    
    class SetEDoesNotContainAny {
        @Test
        fun empty() {
            //TODO test empty condition here.
        }
        
        @Test
        fun single() {
            //TODO test single element condition here.
        }
        
        @Test
        fun multiple() {
            //TODO test multiple element condition here.
        }
    }
    
    
}