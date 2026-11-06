plugins {
    kotlin("jvm") version "2.4.20"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    testImplementation("io.mockk:mockk:1.14.4")
}

kotlin {
    jvmToolchain(25)
}

tasks.test {
    useJUnitPlatform()
}