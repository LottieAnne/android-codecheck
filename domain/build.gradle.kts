plugins {
    id("org.jetbrains.kotlin.jvm")
    kotlin("plugin.serialization") version "1.8.10"
    kotlin("kapt")
}

dependencies {
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.5.0")
    implementation("com.google.dagger:hilt-core:2.44")
    kapt("com.google.dagger:hilt-compiler:2.44")
}
