import org.jetbrains.kotlin.gradle.plugin.extraProperties

// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "8.2.2" apply false
    id("org.jetbrains.kotlin.android") version "1.9.0" apply false
    id("com.google.dagger.hilt.android") version "2.44" apply false
    id ("org.jmailen.kotlinter") version "3.11.0" apply false
}

buildscript {
    extra.apply {
        set("hiltVersion", "2.44")
        set("hiltJetpackVersion", "1.0.0")
    }
}