import org.jetbrains.kotlin.gradle.dsl.*

//library settings - https://github.com/csense-oss/csense-kotlin
val csenseVersionName = "0.1.0-Local"
val csenseGroupId = "org.csenseoss.kotlin"
val csenseArtifactId = "csense-kotlin"


plugins {
    alias(libs.plugins.maven.publish)
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.kotlinx.kover)
    alias(libs.plugins.dependencycheck)
    alias(libs.plugins.gradle.maven.publish)
}

repositories {
    mavenCentral()
    mavenLocal()
}

kotlin {
    withSourcesJar(true)
    explicitApi = ExplicitApiMode.Strict
    jvmToolchain {
        languageVersion.set(JavaLanguageVersion.of(libs.versions.compileJavaVersion.get()))
    }

    jvm {
        testRuns["test"].executionTask.configure {
            useJUnitPlatform()
        }
    }


    js {
        browser {
            testTask {
                useKarma {
                    useChrome()
                }
            }
        }
        nodejs()
    }

    //Native targets, based on https://kotlinlang.org/docs/native-target-support.html
    //- tier 1
//    macosArm64()
//    iosSimulatorArm64()
//    iosX64()

    //- tier 2
//    linuxX64()
//    linuxArm64()
//    watchosSimulatorArm64()
//    watchosArm32()
//    watchosArm64()
//    tvosSimulatorArm64()
//    tvosArm64()
//    iosArm64()

    //- tier 3
//    androidNativeArm32()
//    androidNativeArm64()
//    androidNativeX86()
//    androidNativeX64()
//    mingwX64()
//    androidNativeX64()
//    watchosDeviceArm64()
    //end native targets
    sourceSets {
        commonMain {
            dependencies {
                implementation(libs.kotlinx.coroutines.core)
                implementation(libs.csenseoss.kotlin.annotations)
            }
        }
        commonTest {
            dependencies {
                implementation(libs.kotlinx.coroutines.test)
                implementation(libs.csenseoss.kotlin.tests)

                implementation(kotlin("test"))
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
            }
        }
        jvmTest {
            dependencies {
                implementation("org.jetbrains.kotlin:kotlin-test-junit5")
            }
        }

        configureEach {
            languageSettings {
                optIn("kotlin.contracts.ExperimentalContracts")
                optIn("kotlin.experimental.ExperimentalTypeInference")
                optIn("kotlinx.coroutines.ExperimentalCoroutinesApi")
                progressiveMode = true //https://kotlinlang.org/docs/whatsnew13.html#progressive-mode
            }
        }
    }
}

//dependencyCheck {
//    val allConfigsAsList = mutableListOf(configurations.getNames())
//    val nonTestConfigurations = allConfigsAsList.filter {
//        !it.contains("Test") && (
//                it.startsWith("js") ||
//                        it.startsWith("jvm") ||
//                        it.startsWith("jvm") ||
//                        it.startsWith("common")
//        )
//    }
//
//    analyzers {
//        assemblyEnabled = false
//    }
//    scanConfigurations = nonTestConfigurations
//    skipTestGroups = true
//    failOnError = true
//}

kover {
    reports {
        filters {
            excludes {
                annotatedBy.add("org.csenseoss.kotlin.annotations.KoverIgnore")
            }
        }
    }
}


project.group = csenseGroupId
project.version = csenseVersionName

val csenseUserOrg = "csense-oss"

val csenseLicense = "MIT"
val csenseLicenseUrl = "https://mit-license.org/"

val csenseDeveloperAlias = "tvede-dk"
val csenseDeveloperName = "Kasper Tvede"
val csenseDeveloperEmail = "csenseoss@tvedesys.dk"

val csenseWebsite = "https://github.com/csense-oss/csense-kotlin"
val csenseGit = "https://github.com/csense-oss/csense-kotlin.git"
val csenseArtifactDescription =
    "aim of this project is to add further extensions, features to the std lib and \"very common\" operations."


mavenPublishing {
    coordinates(csenseGroupId, csenseArtifactId, csenseVersionName)
    pom {
        name = csenseArtifactId
        description = csenseArtifactDescription
        url = csenseWebsite
        licenses {
            license {
                name = csenseLicense
                url = csenseLicenseUrl
            }
        }
        developers {
            developer {
                id = csenseDeveloperAlias
                name = csenseDeveloperName
                email = csenseDeveloperEmail
            }
        }
        organization {
            name = csenseUserOrg
        }
        issueManagement {
            system.set("Github")
            url.set("https://github.com/csense-oss/csense-kotlin/issues")
        }
        scm {
            connection.set("https://github.com/csense-oss/csense-kotlin.git")
            url.set("https://github.com/csense-oss/csense-kotlin")
        }
    }
}