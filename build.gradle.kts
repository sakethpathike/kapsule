plugins {
    alias(libs.plugins.kotlin.jvm)
}

group = "sakethh"
version = "0.0.1"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains.kotlinx:kotlinx-html-jvm:0.12.0")
}
