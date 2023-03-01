plugins {
    id("org.jetbrains.kotlin.jvm")
    kotlin("plugin.serialization") version "1.8.10"
}

dependencies {
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.5.0")
}
