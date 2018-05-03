plugins {
    id("com.android.application") version "3.0.1"
    kotlin("android") version "1.2.41"
}

android {
    buildToolsVersion("27.0.1")
    compileSdkVersion(27)

    defaultConfig {
        minSdkVersion(15)
        targetSdkVersion(27)

        applicationId = "com.example.kotlingradle"
        versionCode = 1
        versionName = "1.0"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles("proguard-rules.pro")
        }
    }
}

dependencies {
    implementation("com.android.support:appcompat-v7:27.0.1")
    implementation("com.android.support.constraint:constraint-layout:1.0.2")
    implementation(kotlin("stdlib", "1.2.41"))
}

repositories {
    jcenter()
    google()
}
