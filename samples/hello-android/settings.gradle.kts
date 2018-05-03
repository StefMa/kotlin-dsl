pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
    }
    resolutionStrategy {
        eachPlugin {
            if (requested.id.id.startsWith("com.android.")) {
                useModule("com.android.tools.build:gradle:${requested.version}")
            }
        }
    }
}

rootProject.buildFileName = "build.gradle.kts"
include("app", "lib")
