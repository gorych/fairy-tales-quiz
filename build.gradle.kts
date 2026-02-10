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

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}