@file:Suppress("unused", "NOTHING_TO_INLINE")

package csense.kotlin.algorithms

//TODO "inline" when feature available. see
enum class Comparing {
    LargerThan,
    LessThan,
    Equal
}
// TODO evaluate this concept; it kinda leans into the enum and or "inline sealed" case.
//inline class Comparison(private val int: Int) {
//
//    val isLargerThan
//        get() = int > 0
//    val isEqual
//        get() = int == 0
//    val isLessThan
//        get() = int < 0
//
//    fun getComparing(): Comparing {
//        return when {
//            isLessThan -> Comparing.LessThan
//            isEqual -> Comparing.Equal
//            else -> {
//                Comparing.LargerThan
//            }
//        }
//    }
//}