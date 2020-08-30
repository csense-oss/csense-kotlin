//package csense.kotlin.extensions.primitives
//
//import kotlinx.benchmark.*
//
//
//@State(Scope.Benchmark)
//@BenchmarkMode(Mode.AverageTime)
//@OutputTimeUnit(BenchmarkTimeUnit.MILLISECONDS)
//@Warmup(iterations = 2)
//@Measurement(iterations = 30, time = 500, timeUnit = BenchmarkTimeUnit.MILLISECONDS)
//open class StringPerformanceTests {
//
//
//    @Benchmark
//    open fun containsAnyOldImplSingle(): Boolean {
//        return testString.containsAnyOld("ultricies massa")
//    }
//
//    @Benchmark
//    open fun containsAnyOldImplManyNotFound(): Boolean {
//        return testString.containsAnyOld(
//                "1234",
//                "123456",
//                "ewqioopiewq",
//                "12879",
//                "21390",
//                "asdaaaa",
//                "asddsaads"
//        )
//    }
//
//    @Benchmark
//    open fun containsAnyOldImplManyNotFoundOnly1FoundLast(): Boolean {
//        return testString.containsAnyOld(
//                "1234",
//                "123456",
//                "ewqioopiewq",
//                "12879",
//                "21390",
//                "asdaaaa",
//                "asddsaads",
//                "ultricies massa"
//        )
//    }
//
//    @Benchmark
//    open fun containsAnyOldImplManyNotFoundOnly1FoundMiddle(): Boolean {
//        return testString.containsAnyOld(
//                "1234",
//                "123456",
//                "ewqioopiewq",
//                "12879",
//                "21390",
//                "asdaaaa",
//                "asddsaads",
//                "Nulla molestie"
//        )
//    }
//    @Benchmark
//    open fun containsAnyOldImplManyNotFoundOnly1FoundFirst(): Boolean {
//        return testString.containsAnyOld(
//                "1234",
//                "123456",
//                "ewqioopiewq",
//                "12879",
//                "21390",
//                "asdaaaa",
//                "asddsaads",
//                "Lorem ipsum"
//        )
//    }
//
//    //old impl
//    inline fun String.containsAnyOld(
//            vararg strings: String,
//            ignoreCase: Boolean = false
//    ): Boolean = strings.any {
//        this.contains(it, ignoreCase)
//    }
//}
//
//val testString = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse sagittis mi ut tempus pharetra. Donec quam felis, fringilla a lectus eu, volutpat hendrerit lectus. Aenean blandit magna nec eros feugiat, sit amet volutpat eros porttitor. Nulla tempor at dui sed varius. Mauris sed lacinia orci, non rhoncus enim. Sed id nunc viverra, egestas magna a, dignissim mauris. Curabitur aliquet est pulvinar lacus mattis finibus. Fusce quis elementum massa, eget placerat elit. Proin vel placerat nibh. Sed suscipit nisl tincidunt massa scelerisque, lobortis facilisis orci fermentum. Maecenas eu commodo metus. Nullam libero est, lobortis vel rhoncus in, mollis eget mauris. Vivamus ornare orci id sagittis lacinia. Etiam eu placerat mauris. Morbi mattis tortor sed nisl tincidunt, sollicitudin dapibus turpis imperdiet. Fusce finibus cursus fermentum.\n".repeat(50) +
//        "\n".repeat(50) +
//        "Donec purus tortor, interdum ac lectus sed, ultrices feugiat lectus. Aliquam mollis interdum eros, et blandit tortor commodo ut. Nam hendrerit, velit sagittis bibendum aliquam, ex lectus rutrum metus, nec mattis urna tellus et nisi. Sed condimentum non orci pharetra volutpat. Nulla tortor nulla, placerat eu eros ullamcorper, ullamcorper mollis arcu. In suscipit, elit et lobortis porttitor, mi tortor aliquam tellus, a rhoncus diam arcu id sem. Nunc semper metus sit amet quam scelerisque ultricies. Nullam tempor, mi nec fermentum dignissim, mi dolor varius neque, nec aliquet tellus purus tincidunt lorem. Praesent tempor nisi quis ipsum fermentum, nec tincidunt sapien gravida. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia curae;\n".repeat(50) +
//        "\n".repeat(50) +
//        "Aliquam gravida finibus pulvinar. Fusce pretium eros vel diam vehicula ullamcorper. Suspendisse potenti. Nulla varius eget ligula eget euismod. Orci varius natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Nunc et nunc ultrices, ultrices eros at, euismod lacus. Aenean justo ipsum, ultrices vel ipsum vehicula, sollicitudin eleifend lectus. Vivamus id turpis vitae neque lobortis imperdiet. Fusce lorem magna, vulputate quis dignissim eget, tincidunt sed tellus.\n".repeat(50) +
//        "\n".repeat(50) +
//        "Integer a nibh at elit congue mollis. Donec velit nibh, finibus ut felis nec, mattis laoreet est. Mauris in elit ut metus ullamcorper dapibus. Quisque vestibulum lorem libero, ac accumsan sem placerat eu. Suspendisse hendrerit faucibus nisi facilisis rutrum. Donec turpis dui, faucibus ac bibendum et, ultrices lobortis nunc. Maecenas id sem ipsum. Curabitur condimentum a ex vitae porttitor. Curabitur in dapibus purus. Nulla eget posuere libero. Donec egestas tortor eu tristique bibendum.\n".repeat(50) +
//        "\n".repeat(50) +
//        "Phasellus finibus eu neque non feugiat. Mauris ullamcorper enim nec augue consequat dapibus. Aenean vestibulum ex sed felis dapibus faucibus. In lobortis eros sit amet quam porta lacinia sit amet vel velit. In nec ultrices mi, ut aliquam libero. Pellentesque interdum velit ac pretium maximus. Phasellus laoreet odio ac risus sollicitudin, gravida fermentum lectus mattis. Nam pulvinar sollicitudin lorem, consectetur gravida libero maximus nec. Curabitur sagittis accumsan sem ac rhoncus. Proin congue tempor metus, eu lacinia eros consequat eu. Nam vestibulum felis a erat imperdiet, nec imperdiet tellus pretium. In hac habitasse platea dictumst. In pellentesque ornare finibus. Fusce tempus turpis eu pulvinar scelerisque.\n".repeat(50) +
//        "\n".repeat(50) +
//        "Proin aliquet faucibus sagittis. Nullam vel hendrerit velit. Morbi rutrum tortor a odio blandit ultrices. Suspendisse potenti. Pellentesque et diam ac orci dignissim elementum quis facilisis metus. Donec suscipit non orci quis tempor. Nunc leo eros, ornare eu ipsum quis, rhoncus ultricies eros. Nunc interdum risus vel elit pretium, vel volutpat odio mollis.\n".repeat(50) +
//        "\n".repeat(50) +
//        "Nulla molestie metus at est consequat, ac facilisis turpis hendrerit. Suspendisse accumsan ipsum vitae lorem feugiat, nec dictum est scelerisque. Integer commodo sem ut lorem feugiat, eget cursus lacus gravida. Sed dapibus, urna eget tempus viverra, dui ligula pharetra libero, vitae vulputate nibh ante eu orci. Vestibulum quis dui a est auctor tempor. In malesuada ligula justo. In eget ante id velit imperdiet maximus. Proin facilisis euismod congue. Quisque feugiat risus a magna lacinia, nec consequat dolor tincidunt. Proin suscipit, risus aliquet euismod ultricies, ex ante hendrerit dui, vitae dapibus nisi justo sit amet neque. In euismod neque sit amet convallis egestas. Donec id cursus erat, vitae pulvinar urna. Pellentesque efficitur nunc eget eros vestibulum gravida.\n".repeat(50) +
//        "\n".repeat(50) +
//        "Nunc egestas purus vitae felis lobortis, ut volutpat libero malesuada. Vestibulum interdum mi purus, et maximus velit molestie nec. Donec imperdiet mi diam, quis porttitor urna interdum nec. Curabitur facilisis ex consectetur mi mollis porta. Integer dictum, ante vitae ullamcorper dignissim, est felis viverra enim, vitae ullamcorper odio neque ac augue. Maecenas vitae mauris eu purus iaculis congue. Pellentesque at pellentesque enim. Duis vitae nibh in sem tempor feugiat in id metus. Fusce viverra porta ex id sollicitudin. Aenean sollicitudin erat consequat lectus ornare iaculis. Nulla at pharetra felis. Ut sit amet arcu ac tellus auctor pulvinar.\n".repeat(50) +
//        "\n".repeat(50) +
//        "Proin metus ante, dapibus eget bibendum quis, egestas eget quam. Fusce rhoncus varius ante, eget accumsan metus vehicula at. Phasellus consequat turpis quam, nec consectetur nulla gravida sit amet. Phasellus hendrerit nisi quis lectus sodales efficitur. Fusce tristique dui dui, ac congue ex tincidunt semper. Suspendisse bibendum molestie vulputate. Fusce lacinia magna nibh, at molestie orci aliquet nec. Pellentesque purus urna, ornare ac iaculis nec, tristique vel ipsum. Pellentesque dignissim nisl vitae ex sollicitudin, a scelerisque dui facilisis. Duis vel blandit libero, et tincidunt tortor. Phasellus sapien lacus, volutpat id risus euismod, tincidunt gravida diam. Pellentesque a hendrerit lorem, a vulputate elit. In hac habitasse platea dictumst. Curabitur laoreet nulla ut ligula tristique, ut porttitor tellus fermentum. Curabitur tristique dolor eu metus condimentum, sed auctor lacus dignissim. Proin dictum risus vel dapibus suscipit.\n".repeat(50) +
//        "\n".repeat(50) +
//        "Fusce vel tortor tincidunt, vehicula sem et, consequat tellus. Curabitur faucibus mauris vel libero commodo pretium. Sed tincidunt at neque vel cursus. Aliquam pretium odio purus, eget sagittis quam suscipit eu. Vestibulum iaculis suscipit tincidunt. Praesent mauris diam, porta id tellus sit amet, euismod dapibus odio. Sed eget tincidunt sem, quis tempus arcu. Phasellus a interdum turpis. Nullam sollicitudin turpis placerat dui faucibus, malesuada pellentesque est congue. Integer pellentesque aliquet magna suscipit laoreet. Proin aliquam eros nisi, eget volutpat ligula facilisis eu. Aenean fringilla hendrerit bibendum. Sed placerat justo malesuada maximus facilisis. Vivamus iaculis quis lectus sed feugiat. Duis ipsum nunc, maximus at tincidunt quis, fermentum vitae nulla.\n".repeat(50) +
//        "\n".repeat(50) +
//        "Vivamus ante arcu, iaculis sed tortor in, sagittis lacinia erat. Duis in mi velit. Proin sed velit urna. Morbi convallis ex a enim efficitur laoreet. Maecenas in mauris sed felis dignissim pretium. Nam id ultricies massa.".repeat(50)
//