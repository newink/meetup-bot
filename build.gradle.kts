buildscript {
    repositories {
        jcenter()
    }
    dependencies {
        classpath("org.koin:koin-gradle-plugin:2.1.5")
    }
}

plugins {
    java
    kotlin("jvm") version "1.3.72"
}

group = "dev.ivnv"
version = "1.0"

repositories {
    mavenCentral()
    jcenter()
    maven(url = "https://jitpack.io")
}

dependencies {
    exposed("0.24.1").forEach {
        implementation(it)
    }
    implementation("com.h2database:h2:1.4.200")
    implementation("com.tinder.statemachine:statemachine:0.2.0")
    implementation("org.koin:koin-core:2.1.5")
    implementation("io.github.kotlin-telegram-bot.kotlin-telegram-bot:telegram:5.0.0")
    implementation("org.slf4j:slf4j-simple:1.7.30")
    implementation("com.zaxxer:HikariCP:3.4.5")

    implementation(kotlin("stdlib-jdk8"))
    testImplementation("junit", "junit", "4.12")
}

fun exposed(version: String): Array<String> {
    return arrayOf(
        "org.jetbrains.exposed:exposed-core:$version",
        "org.jetbrains.exposed:exposed-dao:$version",
        "org.jetbrains.exposed:exposed-jdbc:$version"
    )
}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_1_8
}

val fatJar = task("fatJar", type = Jar::class) {
    baseName = "${project.name}-fat"
    // manifest Main-Class attribute is optional.
    // (Used only to provide default main class for executable jar)
    manifest {
        attributes["Main-Class"] = "dev.ivnv.meetup.Main" // fully qualified class name of default main class
    }
    from(configurations.runtimeClasspath.get().map { if (it.isDirectory) it else zipTree(it) })
    with(tasks.jar.get() as CopySpec)
}

tasks {
    compileKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
    compileTestKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
    "build" {
        dependsOn(fatJar)
    }
}
