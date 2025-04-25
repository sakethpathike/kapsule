plugins {
    alias(libs.plugins.multiplatform)
    alias(libs.plugins.android.library)
    id("convention.publication")
}

group = "sakethh.kapsule"
version = "0.0.1"

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
            implementation("org.jetbrains.kotlinx:kotlinx-html:$kotlinxHtmlVersion")
        }
        jvmMain.dependencies {
            implementation("org.jetbrains.kotlinx:kotlinx-html-jvm:$kotlinxHtmlVersion")
        }

        jsMain.dependencies {
            implementation("org.jetbrains.kotlinx:kotlinx-html-js:$kotlinxHtmlVersion")
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
    namespace = "sakethh.kapsule"
    compileSdk = 35

    defaultConfig {
        minSdk = 21
    }
}
