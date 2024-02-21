// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "8.2.1" apply false
    id("org.jetbrains.kotlin.android") version "1.9.0" apply false
    id("com.android.library") version "8.2.1" apply false
    kotlin("jvm") version "1.9.22" apply false
    id("com.google.dagger.hilt.android") version "2.46" apply false
}



buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath(kotlin("gradle-plugin", version = "1.9.22"))
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.9.22")
    }
}