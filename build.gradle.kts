import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

plugins {
    kotlin("jvm") version "2.0.0"
}

group = "org.gorych"
version = "1.0.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.fasterxml.jackson.core:jackson-databind:2.21.0")
    implementation("tools.jackson.module:jackson-module-kotlin:3.0.4")

    testImplementation(kotlin("test"))
}

tasks.register<Zip>("zipContent") {
    from("build.gradle.kts")

    into("src/main") {
        from("src/main")
    }

    val timestamp = ZonedDateTime
        .now()
        .format(DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss"))
    archiveFileName.set("fairy-tales-quiz-$timestamp.zip")

    destinationDirectory.set(layout.buildDirectory.dir("out"))
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}