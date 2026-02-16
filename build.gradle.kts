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

    // Testing
    testImplementation(kotlin("test"))

    testImplementation("io.mockk:mockk:1.13.17")

    testImplementation("io.kotest:kotest-assertions-json:5.8.1")

    testImplementation("org.junit.jupiter:junit-jupiter-api:6.0.2")
    testImplementation("org.junit.platform:junit-platform-suite-api:6.0.2")
    testImplementation("org.junit.platform:junit-platform-suite-engine:6.0.2")
    testImplementation("org.junit.jupiter:junit-jupiter-params:6.0.2")
    testImplementation("org.junit.jupiter:junit-jupiter-engine:6.0.2")
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