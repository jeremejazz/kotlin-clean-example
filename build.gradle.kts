plugins {
    kotlin("jvm") version "2.2.21"
}

group = "me.jereme"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    testImplementation("org.junit.jupiter:junit-jupiter:5.10.0")
    testImplementation("org.mockito:mockito-core:5.11.0")
    testImplementation("org.mockito.kotlin:mockito-kotlin:5.2.1") // Kotlin helper for Mockito
}

kotlin {
    jvmToolchain(24)
}

tasks.test {
    useJUnitPlatform()
}