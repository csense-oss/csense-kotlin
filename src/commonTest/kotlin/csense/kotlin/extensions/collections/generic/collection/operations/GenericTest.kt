@file:Suppress("unused")

package csense.kotlin.extensions.collections.generic.collection.operations

import csense.kotlin.extensions.collections.generic.collection.*
import csense.kotlin.extensions.primitives.int.*
import csense.kotlin.tests.assertions.*
import kotlin.test.*

class GenericTest {

    class GenericForEach {
        @Test
        fun empty() {
            GenericCollections.forEach(
                length = 0,
                retriever = { shouldNotBeCalled() },
                startIndex = 0,
                onEach = { shouldNotBeCalled() }
            )
        }

        @Test
        fun badIndexes() {
            GenericCollections.forEach(
                length = 1,
                retriever = { shouldNotBeCalled() },
                startIndex = 42,
                onEach = { shouldNotBeCalled() }
            )
        }

        @Test
        fun single() = assertCalled(times = 2) { shouldBeCalled: () -> Unit ->
            GenericCollections.forEach(
                length = 1,
                retriever = {
                    it.assert(0)
                    shouldBeCalled()
                    "output"
                },
                startIndex = 0,
                onEach = {
                    it.assert("output")
                    shouldBeCalled()
                })
        }

        @Test
        fun singleWeirdIndex() = assertCalled(times = 2) { shouldBeCalled: () -> Unit ->
            GenericCollections.forEach(
                length = 1,
                retriever = {
                    it.assert(0)
                    shouldBeCalled()
                    "output2"

                },
                startIndex = 0,
                onEach = {
                    it.assert("output2")
                    shouldBeCalled()
                })
        }

        @Test
        fun startIndexShouldBeRespected() = assertCalled(times = 2) { shouldBeCalled: () -> Unit ->
            GenericCollections.forEach(
                length = 3,
                retriever = {
                    it.assert(2, "start index should be respected")
                    shouldBeCalled()
                    "output"
                },
                startIndex = 2,
                onEach = {
                    shouldBeCalled()
                })
        }


        @Test
        fun multipleMixed() = assertCalled(times = 6) { shouldBeCalled: () -> Unit ->
            var retrieverIndex = 0
            GenericCollections.forEach(
                length = 3,
                retriever = {
                    it.assert(retrieverIndex)
                    retrieverIndex += 1
                    shouldBeCalled()
                    "$it"
                },
                startIndex = 0,
                onEach = {
                    val currentRealIndex = retrieverIndex - 1
                    it.assert("$currentRealIndex")
                    shouldBeCalled()
                })
        }
    }


    class GenericMap {
        @Test
        fun empty() {
            GenericCollections.map(
                length = 0,
                retriever = { shouldNotBeCalled() },
                startIndex = 0,
                mapper = { shouldNotBeCalled() }
            ).assertEmpty()
        }

        @Test
        fun badIndexes() {
            GenericCollections.map(
                length = 1,
                retriever = { shouldNotBeCalled() },
                startIndex = 42,
                mapper = { shouldNotBeCalled() }
            ).assertEmpty()
        }

        @Test
        fun single() = assertCalled(times = 2) { shouldBeCalled: () -> Unit ->
            val lst = GenericCollections.map(
                length = 1,
                retriever = {
                    it.assert(0)
                    shouldBeCalled()
                    "output"
                },
                startIndex = 0,
                mapper = {
                    it.assert("output")
                    shouldBeCalled()
                    22
                })
            lst.assertSingle(22)
        }

        @Test
        fun singleWeirdIndex() = assertCalled(times = 2) { shouldBeCalled: () -> Unit ->
            val lst = GenericCollections.map(
                length = 1,
                retriever = {
                    it.assert(0)
                    shouldBeCalled()
                    "output2"
                },
                startIndex = 0,
                mapper = {
                    it.assert("output2")
                    shouldBeCalled()
                    33
                })
            lst.assertSingle(33)
        }

        @Test
        fun startIndexShouldBeRespected() = assertCalled(times = 2) { shouldBeCalled: () -> Unit ->
            GenericCollections.map(
                length = 3,
                retriever = {
                    it.assert(2, "start index should be respected")
                    shouldBeCalled()
                    "output"
                },
                startIndex = 2,
                mapper = {
                    shouldBeCalled()
                    false
                })
        }

        @Test
        fun multipleMixed() = assertCalled(times = 6) { shouldBeCalled: () -> Unit ->
            var retrieverIndex = 0
            val lst = GenericCollections.map(
                length = 3,
                retriever = {
                    it.assert(retrieverIndex)
                    retrieverIndex += 1
                    shouldBeCalled()
                    "$it"
                },
                startIndex = 0,
                mapper = {
                    val currentRealIndex = retrieverIndex - 1
                    it.assert("$currentRealIndex")
                    shouldBeCalled()
                    it.toInt()
                })
            lst.assertSize(3)
            lst[0].assert(0)
            lst[1].assert(1)
            lst[2].assert(2)
        }
    }


    class GenericFilter {
        @Test
        fun empty() {
            GenericCollections.filter(
                length = 0,
                retriever = { shouldNotBeCalled() },
                startIndex = 0,
                predicate = { shouldNotBeCalled() }
            )
        }

        @Test
        fun badIndexes() {
            GenericCollections.filter(
                length = 1,
                retriever = { shouldNotBeCalled() },
                startIndex = 42,
                predicate = { shouldNotBeCalled() }
            ).assertEmpty()
        }

        @Test
        fun singleExclude() = assertCalled(times = 2) { shouldBeCalled: () -> Unit ->
            val lst = GenericCollections.filter(
                length = 1,
                retriever = {
                    it.assert(0)
                    shouldBeCalled()
                    "output"
                },
                startIndex = 0,
                predicate = {
                    it.assert("output")
                    shouldBeCalled()
                    false
                })
            lst.assertEmpty()
        }

        @Test
        fun singleInclude() = assertCalled(times = 2) { shouldBeCalled: () -> Unit ->
            val lst = GenericCollections.filter(
                length = 1,
                retriever = {
                    it.assert(0)
                    shouldBeCalled()
                    "output"
                },
                startIndex = 0,
                predicate = {
                    it.assert("output")
                    shouldBeCalled()
                    true
                })
            lst.assertSingle("output")
        }

        @Test
        fun multipleMixed() = assertCalled(times = 6) { shouldBeCalled: () -> Unit ->
            var retrieverIndex = 0
            val lst = GenericCollections.filter(
                length = 3,
                retriever = {
                    it.assert(retrieverIndex)
                    retrieverIndex += 1
                    shouldBeCalled()
                    "$it"
                },
                startIndex = 0,
                predicate = {
                    val currentRealIndex = retrieverIndex - 1
                    it.assert("$currentRealIndex")
                    shouldBeCalled()
                    currentRealIndex.isEven
                })
            lst.assertSize(2)
            lst.first().assert("0")
            lst.last().assert("2")
        }

        @Test
        fun startIndexShouldBeRespected() = assertCalled(times = 2) { shouldBeCalled: () -> Unit ->
            GenericCollections.filter(
                length = 3,
                retriever = {
                    it.assert(2, "start index should be respected")
                    shouldBeCalled()
                    "output"
                },
                startIndex = 2,
                predicate = {
                    shouldBeCalled()
                    false
                })
        }

    }


    class GenericMapEachWith {

        @Test
        fun empty() {
            GenericCollections.mapEachWith("Result", 0, { shouldNotBeCalled() }, 0, {
                shouldNotBeCalled()
            }).assert("Result")

            GenericCollections.mapEachWith("Result", 0, { shouldNotBeCalled() }, 1, {
                shouldNotBeCalled()
            }).assert("Result")

            GenericCollections.mapEachWith("Result", 1, { shouldNotBeCalled() }, 1, {
                shouldNotBeCalled()
            }).assert("Result")
        }

        @Test
        fun single() = assertCalled(times = 2) { shouldBeCalled: () -> Unit ->
            val startIndex = 0
            val length = startIndex + 1

            GenericCollections.mapEachWith(
                result = "Result",
                length = length,
                retriever = {
                    it.assert(startIndex)
                    shouldBeCalled()
                    42
                },
                startIndex = startIndex,
                append = {
                    this.assert("Result")
                    it.assert(42)
                    shouldBeCalled()
                }).assert("Result")
        }

        @Test
        fun singleWithStartIndex() = assertCalled(times = 2) { shouldBeCalled: () -> Unit ->
            val startIndex = 1
            val length = startIndex + 1

            GenericCollections.mapEachWith(
                result = "Result",
                length = length,
                retriever = {
                    it.assert(startIndex)
                    shouldBeCalled()
                    42
                },
                startIndex = startIndex,
                append = {
                    this.assert("Result")
                    it.assert(42)
                    shouldBeCalled()
                }).assert("Result")
        }

        @Test
        fun multiple() = assertCalled(times = 4) { shouldBeCalled: () -> Unit ->
            val startIndex = 0
            val length = startIndex + 2

            val indexesCalled = GenericCollections.mapEachWith(
                result = mutableListOf<Int>(),
                length = length,
                retriever = {
                    shouldBeCalled()
                    it
                },
                startIndex = startIndex,
                append = {
                    shouldBeCalled()
                    add(it)
                })

            indexesCalled.assertSize(2)
            indexesCalled.assertContainsInOrder(0, 1)
        }

    }

    class GenericTraverseWhileNotNull {

        @Test
        fun singleLevel() = assertCalled { shouldBeCalled: () -> Unit ->
            val start = GenericTestTreeStructure("id", parent = null)
            GenericCollections.traverseWhileNotNull(
                start = start,
                processCurrentLevel = {
                    it.id.assert("id")
                    shouldBeCalled()
                },
                getNextLevel = { it.parent }
            )
        }


        @Test
        fun multipleLevels() = assertCalled(times = 2) { shouldBeCalled: () -> Unit ->
            val idCallOrder = mutableListOf<String>()
            val start = GenericTestTreeStructure(
                id = "child",
                parent = GenericTestTreeStructure(
                    id = "parent",
                    parent = null
                )
            )
            GenericCollections.traverseWhileNotNull(
                start = start,
                processCurrentLevel = {
                    idCallOrder += it.id
                    shouldBeCalled()
                },
                getNextLevel = { it.parent }
            )
            idCallOrder.assertSize(2)
            idCallOrder.assertContainsInOrder("child", "parent")
        }

    }

    class GenericTraverseWhileNotNullAndNoCycles {

        @Test
        fun singleLevel() = assertCalled { shouldBeCalled: () -> Unit ->
            val start = GenericTestTreeStructure("id", parent = null)
            GenericCollections.traverseWhileNotNullAndNoCycles(
                start = start,
                processCurrentLevel = {
                    it.id.assert("id")
                    shouldBeCalled()
                },
                getNextLevel = { it.parent }
            )
        }


        @Test
        fun multipleLevels() = assertCalled(times = 2) { shouldBeCalled: () -> Unit ->
            val idCallOrder = mutableListOf<String>()
            val start = GenericTestTreeStructure(
                id = "child",
                parent = GenericTestTreeStructure(
                    id = "parent",
                    parent = null
                )
            )
            GenericCollections.traverseWhileNotNullAndNoCycles(
                start = start,
                processCurrentLevel = {
                    idCallOrder += it.id
                    shouldBeCalled()
                },
                getNextLevel = { it.parent }
            )
            idCallOrder.assertSize(2)
            idCallOrder.assertContainsInOrder("child", "parent")
        }

        @Test
        fun shouldStopAtCycle() = assertCalled(times = 2) { shouldBeCalled: () -> Unit ->
            val root = GenericTestTreeStructure(
                id = "root",
                parent = null
            )
            root.parent = root
            val start = GenericTestTreeStructure(
                id = "child",
                parent = root
            )

            val idCallOrder = mutableListOf<String>()
            GenericCollections.traverseWhileNotNullAndNoCycles(
                start = start,
                processCurrentLevel = {
                    idCallOrder += it.id
                    shouldBeCalled()
                },
                getNextLevel = { it.parent }
            )
            idCallOrder.assertSize(2)
            idCallOrder.assertContainsInOrder("child", "root")
        }
    }
}

class GenericTestTreeStructure(
    var id: String,
    var parent: GenericTestTreeStructure?,
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as GenericTestTreeStructure

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }
}
