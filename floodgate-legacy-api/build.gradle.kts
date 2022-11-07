dependencies {
    api(projects.api)
    api(libs.cumulus)
    api(libs.events) {
        exclude(group = "com.google.guava", module = "guava")
        exclude(group = "org.lanternpowered", module = "lmbda")
    }
    compileOnly("io.netty", "netty-transport", "4.1.80.Final")
}
