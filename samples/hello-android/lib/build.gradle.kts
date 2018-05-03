plugins {
    id("com.android.library") version "3.1.0"
}

android {
    compileSdkVersion(27)

    defaultConfig {
        minSdkVersion(15)

        targetSdkVersion(27)
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
}

repositories {
    jcenter()
    google()
}
