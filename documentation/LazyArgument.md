# LazyArgument

*Purpose:*
to simplify, avoid boilerplate and avoid "forgetting" to update a shared instance variable that cannot be lazy since it
takes arguments. (and also to avoid overwriting it later, since vars are not "protected".

## Simple example

**Before:**

````kotlin
class MyThing(val str: String) {
    companion object {
        private var shared: MyThing? = null

        fun getInstance(someCtorArg: String): MyThing {
            return shared ?: MyThing(someCtorArg).also { shared = it }
        }
    }
}
````

**After:**

````kotlin
class MyThing(val str: String) {
    companion object {
        private val shared = LazyArgument { it: String ->
            MyThing(it)
        }

        fun getInstance(someCtorArg: String): MyThing = shared(someCtorArg)
    }
}
````

**After(more verbose):**

````kotlin
class MyThing(val str: String) {
    companion object {
        private val shared = LazyArgument { it: String ->
            MyThing(it)
        }

        fun getInstance(someCtorArg: String): MyThing = shared.getValue(someCtorArg)
    }
}
````

Note, there are no "also"/apply (etc.) there is no vars nor nulls in the after code.

# LazyArgumentConstructor

A more generalized solution that allows you (the user) to create the lazy value however required.
(e.g. multiple arguments etc.)

**Before:**

````kotlin

class MyThing(
    val str: String,
    val value: Int,
    val isNice: Boolean
) {
    companion object {
        private var shared: MyThing? = null

        fun getInstance(first: String, otherThing: Int, thirdIsTheCharm: Boolean): MyThing {
            return shared ?: MyThing(first, otherThing, thirdIsTheCharm).also { shared = it }
        }
    }
}
````

**After:**

````kotlin
class MyThing(
    val str: String,
    val value: Int,
    val isNice: Boolean
) {
    companion object {
        private val shared = LazyArgumentConstructor<MyThing>()

        fun getInstance(first: String, otherThing: Int, thirdIsTheCharm: Boolean): MyThing {
            return shared {
                MyThing(first, otherThing, thirdIsTheCharm)
            }
        }
    }
}
````

**After(more verbose):**

````kotlin
class MyThing(
    val str: String,
    val value: Int,
    val isNice: Boolean
) {
    companion object {
        private val shared = LazyArgumentConstructor<MyThing>()

        fun getInstance(first: String, otherThing: Int, thirdIsTheCharm: Boolean): MyThing {
            return shared.getValue {
                MyThing(first, otherThing, thirdIsTheCharm)
            }
        }
    }
}
````

## lazyArgument function(s)

a more "natural way" to declare a lazy argument (like lazy) would be to use the lazyArgument function, that is
overloaded to take various amounts of arguments.
e.g:

```kotlin
class MyThing(
    val str: String,
    val value: Int,
    val isNice: Boolean
) {
    companion object {
        private val shared = lazyArgument { first: String, second: Int, third: Boolean ->
            MyThing(first, second, third)
        }

        fun getInstance(first: String, otherThing: Int, thirdIsTheCharm: Boolean): MyThing {
            return shared(first, otherThing, thirdIsTheCharm)
        }
    }
}
```

(Nb as of writing it is only overloaded to a max of 4 arguments)