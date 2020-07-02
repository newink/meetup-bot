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
    maven(url = "https://dl.bintray.com/kotlin/kotlin-dev/")
}

dependencies {
    exposed("0.24.1").forEach {
        implementation(it)
    }
    implementation("com.h2database:h2:1.4.200")
    implementation("com.tinder.statemachine:statemachine:0.2.0")
    implementation("org.koin:koin-core:2.1.5")
    implementation("io.github.kotlin-telegram-bot.kotlin-telegram-bot:telegram:5.0.0")
    implementation("org.apache.logging.log4j:log4j-slf4j-impl:2.13.3")
    implementation("com.zaxxer:HikariCP:3.4.5")
    implementation("org.freemarker:freemarker:2.3.30")

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
    archiveName = "${project.name}.${archiveExtension.get()}"
    manifest {
        attributes["Main-Class"] = "dev.ivnv.meetup.MainKt"
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
