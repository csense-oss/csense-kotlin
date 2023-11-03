package csense.kotlin.extensions.ranges.progressions

public fun Int.downToExcluding(
    to: Int
): IntProgression = this.downTo(to.inc())

public fun Int.lengthTo(endInclusive: Int): IntProgression {
    return this.dec().downTo(endInclusive)
}