# LazyArgument

purpose:
to simplify, avoid boilerplate and avoid "forgetting" to update a shared instance variable that cannot be lazy since it
takes arguments

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
        private val shared = LazyArgument<MyThing>()

        fun getInstance(someCtorArg: String): MyThing =
            shared { MyThing(someCtorArg) }

    }
}

````

**After(more verbose):**

````kotlin

class MyThing(val str: String) {
    companion object {
        private val shared = LazyArgument<MyThing>()

        fun getInstance(someCtorArg: String): MyThing =
            shared.getValue { MyThing(someCtorArg) }

    }
}
````

Note, there are no "also"/apply (etc.) there is no vars nor nulls in the after code.