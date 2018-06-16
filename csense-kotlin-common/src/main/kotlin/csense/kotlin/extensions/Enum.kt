package csense.kotlin.extensions


inline fun <reified T : kotlin.Enum<T>> enumFromOrNull(name: String?): T? {
    return enumValues<T>().find { it.name == name }
}

inline fun <reified T : kotlin.Enum<T>> enumFromOr(name: String?, orElse: T): T {
    return enumFromOrNull<T>(name) ?: orElse
}
