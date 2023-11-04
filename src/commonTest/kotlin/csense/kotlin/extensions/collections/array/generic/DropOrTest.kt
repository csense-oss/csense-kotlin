package csense.kotlin.extensions.collections.array.generic

import csense.kotlin.tests.assertions.*
import kotlin.test.*

class DropOrTest {
    class DropOrEmpty {
        @Test
        fun empty() {
            val empty: Array<String> = arrayOf()
            empty.dropOrEmpty(dropCount = 0).assertEmpty()
        }

        @Test
        fun single() {
            val single: Array<String> = arrayOf("test")
            single.dropOrEmpty(dropCount = 0).assertSingle("test")
            single.dropOrEmpty(dropCount = 1).assertEmpty()
            single.dropOrEmpty(dropCount = 2).assertEmpty()
        }

        @Test
        fun multiple() {
            val multi: Array<String> = arrayOf("1", "2", "3")
            multi.dropOrEmpty(dropCount = 0).assertContentAndOrder(listOf("1", "2", "3"))
            multi.dropOrEmpty(dropCount = 1).assertContentAndOrder(listOf("2", "3"))
            multi.dropOrEmpty(dropCount = 2).assertContentAndOrder(listOf("3"))
            multi.dropOrEmpty(dropCount = 3).assertEmpty()
            multi.dropOrEmpty(dropCount = 4).assertEmpty()
        }
    }

    class DropOrNull {
        @Test
        fun empty() {
            val empty: Array<String> = arrayOf()
            empty.dropOrNull(dropCount = 0).assertNull()
        }

        @Test
        fun single() {
            val single: Array<String> = arrayOf("test")
            single.dropOrNull(dropCount = 0).assertSingle("test")
            single.dropOrNull(dropCount = 1).assertNull()
            single.dropOrNull(dropCount = 2).assertNull()
        }

        @Test
        fun multiple() {
            val multi: Array<String> = arrayOf("1", "2", "3")
            multi.dropOrNull(dropCount = 0)!!.assertContentAndOrder(listOf("1", "2", "3"))
            multi.dropOrNull(dropCount = 1)!!.assertContentAndOrder(listOf("2", "3"))
            multi.dropOrNull(dropCount = 2)!!.assertContentAndOrder(listOf("3"))
            multi.dropOrNull(dropCount = 3).assertNull()
            multi.dropOrNull(dropCount = 4).assertNull()
        }
    }

    class DropOrValue {
        @Test
        fun empty() {
            val empty: Array<String> = arrayOf()
            empty.dropOr(dropCount = 0, orValue = listOf("test"))
                .assertSingle("test")
        }

        @Test
        fun single() {
            val single: Array<String> = arrayOf("test")
            single.dropOr(dropCount = 0, orValue = listOf("orValue")).assertSingle("test")
            single.dropOr(dropCount = 1, orValue = listOf("orValue")).assertSingle("orValue")
            single.dropOr(dropCount = 2, orValue = listOf("orValue")).assertSingle("orValue")
        }

        @Test
        fun multiple() {
            val multi: Array<String> = arrayOf("1", "2", "3")
            multi.dropOr(dropCount = 0, orValue = listOf("orValue")).assertContentAndOrder(listOf("1", "2", "3"))
            multi.dropOr(dropCount = 1, orValue = listOf("orValue")).assertContentAndOrder(listOf("2", "3"))
            multi.dropOr(dropCount = 2, orValue = listOf("orValue")).assertContentAndOrder(listOf("3"))
            multi.dropOr(dropCount = 3, orValue = listOf("orValue")).assertSingle("orValue")
            multi.dropOr(dropCount = 4, orValue = listOf("orValue")).assertSingle("orValue")
        }
    }

    class DropOrAction {
        @Test
        fun empty(): Unit = assertCalled { shouldBeCalled: () -> Unit ->
            val empty: Array<String> = arrayOf()
            empty.dropOr(dropCount = 0, orAction = { shouldBeCalled(); listOf("test") })
                .assertSingle("test")
        }

        @Test
        fun single() {
            val single: Array<String> = arrayOf("test")
            single.dropOrNull(dropCount = 0).assertSingle("test")
            assertCalled { shouldBeCalled: () -> Unit ->
                single.dropOr(
                    dropCount = 1,
                    orAction = {
                        shouldBeCalled()
                        listOf("success")
                    }
                ).assertSingle("success")
            }
            assertCalled { shouldBeCalled: () -> Unit ->
                single.dropOr(
                    dropCount = 2,
                    orAction = {
                        shouldBeCalled()
                        listOf("success")
                    }
                ).assertSingle("success")
            }
        }

        @Test
        fun multiple() {
            val multi: Array<String> = arrayOf("1", "2", "3")
            multi.dropOr(dropCount = 0, orAction = { listOf("orValue") }).assertContentAndOrder(listOf("1", "2", "3"))
            multi.dropOr(dropCount = 1, orAction = { listOf("orValue") }).assertContentAndOrder(listOf("2", "3"))
            multi.dropOr(dropCount = 2, orAction = { listOf("orValue") }).assertContentAndOrder(listOf("3"))
            multi.dropOr(dropCount = 3, orAction = { listOf("orValue") }).assertSingle("orValue")
            multi.dropOr(dropCount = 4, orAction = { listOf("orValue") }).assertSingle("orValue")
        }
    }

}