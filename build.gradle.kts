// File: build.gradle.kts (DI FOLDER UTAMA PROYEK)

plugins {
    // Daftarkan plugin Aplikasi Android dengan versinya, jangan diaplikasikan di sini
    id("com.android.application") version "8.4.2" apply false

    // Daftarkan plugin Kotlin Android dengan versinya, jangan diaplikasikan di sini
    id("org.jetbrains.kotlin.android") version "1.9.23" apply false

    id("androidx.navigation.safeargs.kotlin") version "2.9.0" apply false
}