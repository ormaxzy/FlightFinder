pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
        maven { url = uri("https://jitpack.io") }
    }
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
        maven { url = uri("https://jitpack.io") }
    }
}

rootProject.name = "FlightFinder"
include(":app")
include(":core")
include(":feature-main")
include(":feature-search")
include(":feature-tickets")
include(":data")
include(":domain")
