package csense.kotlin.patterns.lists

import csense.kotlin.classes.iterator.*
import csense.kotlin.general.*

public sealed interface Lists<out T> : List<T> {
    public object Empty : Lists<Nothing> {
        override val size: Int = 0
        override fun contains(element: Nothing): Boolean = false

        override fun containsAll(elements: Collection<Nothing>): Boolean = false

        override fun get(index: Int): Nothing = unexpected()

        override fun indexOf(element: Nothing): Int = notFoundIndex

        override fun isEmpty(): Boolean = true

        override fun iterator(): Iterator<Nothing> = EmptyIterator

        override fun lastIndexOf(element: Nothing): Int = notFoundIndex

        override fun listIterator(): ListIterator<Nothing> = EmptyListIterator

        override fun listIterator(index: Int): ListIterator<Nothing> = EmptyListIterator

        override fun subList(fromIndex: Int, toIndex: Int): List<Nothing> = unexpected()

    }

    public class Content<T>(
        public val firstElement: T,
        private val allElements: List<T>
    ) : Lists<T> {
        override fun isEmpty(): Boolean = false

        override val size: Int =
            allElements.size

        override fun get(index: Int): T =
            allElements[index]

        override fun iterator(): Iterator<T> =
            allElements.iterator()

        override fun listIterator(): ListIterator<T> =
            allElements.listIterator()

        override fun listIterator(index: Int): ListIterator<T> =
            allElements.listIterator(index)

        override fun subList(fromIndex: Int, toIndex: Int): List<T> =
            allElements.subList(fromIndex = fromIndex, toIndex = toIndex)

        override fun lastIndexOf(element: T): Int =
            allElements.lastIndexOf(element)

        override fun indexOf(element: T): Int =
            allElements.indexOf(element)

        override fun containsAll(elements: Collection<T>): Boolean =
            allElements.containsAll(elements)

        override fun contains(element: T): Boolean =
            allElements.contains(element)
    }

    public companion object {
        public const val notFoundIndex: Int = -1
    }
}

