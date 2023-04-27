package csense.kotlin.extensions.primitives.string

//TODO
//public fun String.isWrappedIn(){
//
//}

public fun String.isWrappedInWhitespace(): Boolean {
    return startsWithWhitespace() && endsWithWhitespace()
}