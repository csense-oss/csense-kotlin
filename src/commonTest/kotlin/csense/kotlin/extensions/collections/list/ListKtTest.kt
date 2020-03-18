@file:Suppress("unused")

package csense.kotlin.extensions.collections.list

import csense.kotlin.tests.assertions.*
import kotlin.test.Test


class ListKtTest {
    
    @Test
    fun limitToSize() {
        listOf<String>().limitToSize(0).assertSize(0)
        listOf<String>().limitToSize(-1).assertSize(0)
        listOf<String>().limitToSize(1).assertSize(0)
        
        listOf("a").limitToSize(0).assertSize(0)
        listOf("a").limitToSize(1).assertSize(1)
        listOf("a").limitToSize(-1).assertSize(0)
        listOf("a").limitToSize(2).assertSize(1)
        
        listOf("a", "b").limitToSize(1).apply {
            assertSize(1)
            first().assert("a")
        }
        
        listOf("a", "b").limitToSize(2).apply {
            assertSize(2)
            first().assert("a")
            last().assert("b")
        }
        listOf("a", "b").limitToSize(30).assertSize(2)
    }
    
    
    @Test
    fun repeatToSize() {
        val collection: MutableList<Int> = mutableListOf()
        collection.repeatToSize(50).apply {
            size.assert(0, "repeating nothing is wrong and gives nothing")
        }
        
        collection.add(42)
        collection.repeatToSize(50).apply {
            size.assert(50)
        }
        
        collection.add(42)
        collection.repeatToSize(50).apply {
            size.assert(50)
        }
        //todo more cases here
    }
    
    class ListTSubList {
        @Test
        fun empty() {
            listOf<String>().subList(0..2).assertEmpty()
            listOf<String>().subList(0..1).assertEmpty()
            listOf<String>().subList(0..10).assertEmpty()
        }
        
        @Test
        fun single() {
            listOf("a").subList(0..2).assertEmpty()
            listOf("a").subList(0..1).apply {
                assertSize(1)
                first().assert("a")
            }
            listOf("a").subList(0..10).assertEmpty()
        }
        
        @Test
        fun multiple() {
            listOf("a", "b").subList(0..2).apply {
                assertSize(2)
                first().assert("a")
                last().assert("b")
            }
            listOf("a", "b").subList(0..1).apply {
                assertSize(1)
                first().assert("a")
            }
            listOf("a", "b").subList(1..2).apply {
                assertSize(1)
                first().assert("b")
            }
            listOf("a", "b").subList(0..10).assertEmpty()
        }
    }
    
    class ListTRepeat {
        @Test
        fun empty() {
            listOf<String>().repeat(0).assertEmpty()
            listOf<String>().repeat(1).assertEmpty()
            listOf<String>().repeat(100).assertEmpty()
        }
        
        @Test
        fun single() {
            listOf("a").repeat(0).assertEmpty()
            listOf("a").repeat(1).apply {
                assertSize(1)
                first().assert("a")
            }
            listOf("a").repeat(10).assertSize(10)
        }
        
        @Test
        fun multiple() {
            listOf("a", "1").repeat(0).assertEmpty()
            listOf("a", "1").repeat(1).apply {
                assertSize(2)
                first().assert("a")
                last().assert("1")
            }
            listOf("a", "1").repeat(10).assertSize(20)
        }
    }
    
    class ListTSubListSafe {
        @Test
        fun empty() {
            listOf<String>().subListSafe(0, 2).assertEmpty()
            listOf<String>().subListSafe(0, 1).assertEmpty()
            listOf<String>().subListSafe(0, 10).assertEmpty()
        }
        
        @Test
        fun single() {
            listOf("a").subListSafe(0, 2).assertEmpty()
            listOf("a").subListSafe(0, 1).apply {
                assertSize(1)
                first().assert("a")
            }
            listOf("a").subListSafe(0, 10).assertEmpty()
        }
        
        @Test
        fun multiple() {
            listOf("a", "b").subListSafe(0, 2).apply {
                assertSize(2)
                first().assert("a")
                last().assert("b")
            }
            listOf("a", "b").subListSafe(0, 1).apply {
                assertSize(1)
                first().assert("a")
            }
            listOf("a", "b").subListSafe(1, 2).apply {
                assertSize(1)
                first().assert("b")
            }
            listOf("a", "b").subListSafe(0, 10).assertEmpty()
        }
    }
    

    
    class ListForEachIsInstanceIntProgression {
        @Test
        fun empty() {
            val anyList = listOf<Any>()
            anyList.forEachIsInstance<String>(anyList.indices) { shouldNotBeCalled() }
            anyList.forEachIsInstance<Int>(anyList.indices) { shouldNotBeCalled() }
        }
        
        @Test
        fun single() {
            val anyList = listOf<Any>("")
            assertCalled {
                anyList.forEachIsInstance<String>(anyList.indices) { it() }
            }
            anyList.forEachIsInstance<Int>(anyList.indices) { shouldNotBeCalled() }
            assertCalled {
                anyList.forEachIsInstance<CharSequence>(anyList.indices) { it() }
            }
            assertCalled {
                anyList.forEachIsInstance<Comparable<String>>(anyList.indices) { it() }
            }
        }
        
        @Test
        fun multiple() {
            val anyList = listOf<Any>("", 42, true)
            assertCalled {
                anyList.forEachIsInstance<String>(anyList.indices) { it() }
            }
            assertCalled {
                anyList.forEachIsInstance<Int>(anyList.indices) { it() }
            }
            assertCalled {
                anyList.forEachIsInstance<Boolean>(anyList.indices) { it() }
            }
            anyList.forEachIsInstance<Double>(anyList.indices) { shouldNotBeCalled() }
            anyList.forEachIsInstance<Long>(anyList.indices) { shouldNotBeCalled() }
            
        }
        
        @Test
        fun multipleIndicies() {
            val anyList = listOf<Any>("", 42, true)
            anyList.forEachIsInstance<String>(1 until anyList.size) { shouldNotBeCalled() }
            anyList.forEachIsInstance<String>(2 until anyList.size) { shouldNotBeCalled() }
            
            anyList.forEachIsInstance<String>(1..1) { shouldNotBeCalled() }
            anyList.forEachIsInstance<String>(2..2) { shouldNotBeCalled() }
            assertCalled {
                anyList.forEachIsInstance<String>(0..0) { it() }
            }
            assertCalled {
                anyList.forEachIsInstance<String>(0..1) { it() }
            }
            
            
            anyList.forEachIsInstance<Int>(0..0) { shouldNotBeCalled() }
            assertCalled {
                anyList.forEachIsInstance<Int>(0..1) { it() }
            }
            anyList.forEachIsInstance<Int>(2..2) { shouldNotBeCalled() }
    
        }
    }
    
    class ListForEachIsInstanceAction {
        @Test
        fun empty() {
            val anyList = listOf<Any>()
            anyList.forEachIsInstance<String>(anyList.indices) { shouldNotBeCalled() }
            anyList.forEachIsInstance<String> { shouldNotBeCalled() }
        }
        
        @Test
        fun single() {
            val anyList = listOf<Any>("")
            assertCalled {
                anyList.forEachIsInstance<String>{ it() }
            }
            anyList.forEachIsInstance<Int>{ shouldNotBeCalled() }
            assertCalled {
                anyList.forEachIsInstance<CharSequence>{ it() }
            }
            assertCalled {
                anyList.forEachIsInstance<Comparable<String>>{ it() }
            }
        }
        
        @Test
        fun multiple() {
            val anyList = listOf<Any>("", 42, true)
            assertCalled {
                anyList.forEachIsInstance<String> { it() }
            }
            assertCalled {
                anyList.forEachIsInstance<Int> { it() }
            }
            assertCalled {
                anyList.forEachIsInstance<Boolean>{ it() }
            }
            anyList.forEachIsInstance<Double> { shouldNotBeCalled() }
            anyList.forEachIsInstance<Long> { shouldNotBeCalled() }
        }
    }
}