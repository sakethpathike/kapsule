import com.vanniktech.maven.publish.SonatypeHost

plugins {
    alias(libs.plugins.multiplatform)
    alias(libs.plugins.android.library)
    id("com.vanniktech.maven.publish") version "0.31.0"
}

group = "io.github.sakethpathike"
version = "0.0.2"

kotlin {
    jvmToolchain(17)

    androidTarget { publishLibraryVariants("release") }
    jvm()
    js { browser() }
    wasmJs { browser() }
    iosX64()
    iosArm64()
    iosSimulatorArm64()
    macosX64()
    macosArm64()
    linuxX64()
    mingwX64()

    sourceSets {
        val kotlinxHtmlVersion = "0.12.0"
        commonMain.dependencies {
            api("org.jetbrains.kotlinx:kotlinx-html:$kotlinxHtmlVersion")
        }
        jvmMain.dependencies {
            api("org.jetbrains.kotlinx:kotlinx-html-jvm:$kotlinxHtmlVersion")
        }

        jsMain.dependencies {
            api("org.jetbrains.kotlinx:kotlinx-html-js:$kotlinxHtmlVersion")
        }
    }

    //https://kotlinlang.org/docs/native-objc-interop.html#export-of-kdoc-comments-to-generated-objective-c-headers
    targets.withType<org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget> {
        compilations["main"].compileTaskProvider.configure {
            compilerOptions {
                freeCompilerArgs.add("-Xexport-kdoc")
            }
        }
    }

}

android {
    namespace = "io.github.sakethpathike.kapsule"
    compileSdk = 35

    defaultConfig {
        minSdk = 21
    }
}

mavenPublishing {
    publishToMavenCentral(SonatypeHost.CENTRAL_PORTAL)

    signAllPublications()

    coordinates(group.toString(), "kapsule", version.toString())

    pom {
        name = "kapsule"
        description = " kapsule is a Kotlin Multiplatform library, wrapping kotlinx.html with Jetpack Compose-style modifiers and layout semantics to simplify static HTML generation."
        inceptionYear = "2025"
        url = "https://github.com/sakethpathike/kapsule"
        licenses {
            license {
                name = "The Apache License, Version 2.0"
                url = "https://www.apache.org/licenses/LICENSE-2.0.txt"
                distribution = "https://www.apache.org/licenses/LICENSE-2.0.txt"
            }
        }
        developers {
            developer {
                id = "sakethpathike"
                name = "Saketh Pathike"
                url = "https://github.com/sakethpathike/"
            }
        }
        scm {
            url = "https://github.com/sakethpathike/kapsule/"
            connection = "scm:git:git://github.com/sakethpathike/kapsule.git"
            developerConnection = "scm:git:ssh://git@github.com/sakethpathike/kapsule.git"
        }
    }
}
