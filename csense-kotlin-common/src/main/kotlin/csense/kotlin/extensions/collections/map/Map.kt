package csense.kotlin.extensions.collections.map

/**
 * Iterates all entries in the this map, given the current index.
 * Missing from the std lib
 */
fun <K, V> Map<K, V>.forEachIndexed(action: (Map.Entry<K, V>, Int) -> Unit) {
    var i = 0
    forEach {
        action(it, i)
        i += 1
    }
}


