pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()

        // Add the following
        maven {
            url = uri("https://jitpack.io")
        }
    }
}







rootProject.name = "Cine Mate"
include(":app")
 