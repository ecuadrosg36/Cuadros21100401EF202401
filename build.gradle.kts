plugins {
    // Define los alias para los plugins que se utilizarán en el módulo
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.jetbrains.kotlin.android) apply false
}

buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:8.0.0")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.8.0")
        classpath("com.google.gms:google-services:4.4.2")  // Asegúrate de incluir esta línea
    }
}

tasks.register<Delete>("clean") {
    delete(layout.buildDirectory)
}
