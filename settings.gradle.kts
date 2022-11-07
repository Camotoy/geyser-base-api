enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        mavenLocal()
        // Floodgate, Cumulus etc.
        maven("https://repo.opencollab.dev/main")
    }
}

pluginManagement {
    repositories {
        gradlePluginPortal()
        maven("https://repo.opencollab.dev/maven-snapshots")
    }
    includeBuild("build-logic")
}

rootProject.name = "geyser-base-api"

include(":api")
include(":floodgate-legacy-api")