plugins {
    `java-library`
    id("geyser.build-logic")
    id("io.freefair.lombok") version "6.3.0" apply false
}

allprojects {
    group = "org.geysermc"
    version = "3.0.0-SNAPSHOT"
    description = "Shared API between Geyser and Floodgate."

    tasks.withType<JavaCompile> {
        options.encoding = "UTF-8"
    }
}

val api: Project = projects.api.dependencyProject

subprojects {
    apply {
        plugin("java-library")
        plugin("io.freefair.lombok")
        plugin("geyser.build-logic")
    }

    plugins.apply("geyser.api-conventions")
}